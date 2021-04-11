/*
 * 刘人豪 17307110112
 * 部分内容参考自课本P652-662
 */

#include "csapp.h"
#include <limits.h>

/* Recommended max cache and object sizes */
#define MAX_CACHE_SIZE 1049000
#define MAX_OBJECT_SIZE 102400

/* You won't lose style points for including this long line in your code */
static const char *user_agent_hdr = "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:10.0.3) Gecko/20120305 Firefox/10.0.3\r\n";

//缓存的结构体
struct cache {
    int flag;
    int time;
    int dataSize;
    char url[MAXLINE];
    char data[MAX_OBJECT_SIZE];
    pthread_rwlock_t rwlock;
};

struct cache *pCache;
pthread_mutex_t lock;

//参照课本P659，将URI解析为一个文件名和一个可选的CGI参数字符串
void parse_uri(char *uri, char *filename, char *cgiargs) {
    char buf[MAXLINE];
    char *p1, *p2;
    strcpy(buf, uri);

    if ((p1 = strstr(buf, "//")) == NULL) {
        p1 = buf;
    } else {
        p1 += 2;
    }
    
    if ((p2 = strstr(p1, ":")) == NULL) {
        strcpy(cgiargs, "80");
        if ((p2 = strstr(p1, "/")) == NULL) {
            p2 = buf + strlen(uri);
        }
        *p2 = '\0';
        strcpy(filename, p1);
    } else {
        *p2 = '\0';
        strcpy(filename, p1);
        p1 = p2 + 1;
        if ((p2 = strstr(p2 + 1, "/")) == NULL) {
            p2 = buf + strlen(uri);
        }
        *p2 = '\0';
        strcpy(cgiargs, p1);
    }

    strcpy(buf, uri);
    if ((p1 = strstr(buf, "//")) == NULL) {
        p1 = buf;
    } else {
        p1 += 2;
    }

    if ((p2 = strstr(p1, "/")) == NULL) {
        strcpy(uri, "/");
    } else {
        strcpy(uri, p2);
    }
    printf("url = %s\n", uri);
}

//参照课本P656，处理一个HTTP事务
void doit(int fd) {
    size_t n;
    int serverfd, ret, dataSize = 0;
    char cgiargs[MAXLINE], request[MAXLINE], buf[MAXLINE], method[MAXLINE], data[MAX_OBJECT_SIZE];
    char url[MAXLINE], version[MAXLINE], filename[MAXLINE], uri[MAXLINE];
    rio_t clientRio, serverRio;

    //读和解析请求行
    Rio_readinitb(&clientRio, fd);
    Rio_readlineb(&clientRio, buf, MAXLINE);
    sscanf(buf, "%s %s %s", method, url, version);
    if (strcasecmp(method, "GET") != 0) {
        printf("Proxy can't implement this method.\n");
        return;
    }
    strcpy(uri, url);
    parse_uri(uri, filename, cgiargs);

    //读和解析缓存
    if ((ret = readCache(pCache, url, fd)) == 1) {
        Close(fd);
        return;
    }

    //建立请求
    buildRequest(request, uri, filename, &clientRio);
    if ((serverfd = open_clientfd(filename, cgiargs)) == -1) {
        printf("Proxy can't connect to server.\n");
        return;
    }

    //写入缓存
    Rio_readinitb(&serverRio, serverfd);
    Rio_writen(serverfd, request, (size_t)strlen(request));
    while ((n = Rio_readlineb(&serverRio, buf, MAXLINE)) > 0) {
        printf("%s",buf);
        if (dataSize + n <= MAX_OBJECT_SIZE) {
            memcpy(data + dataSize, buf, n);
        }
        dataSize += n ;
        Rio_writen(fd, buf, n);
    }
    if (dataSize <= MAX_OBJECT_SIZE) {
        writeCache(pCache, url, data, dataSize);
    }
    Close(serverfd);
}

//建立请求
void buildRequest(char *request, char *uri, char *filename, rio_t *rio) {
    //读和解析请求行
    char buf[MAXLINE];
    sprintf(request, "GET %s HTTP/1.0\r\n", uri);
    Rio_readlineb(rio, buf, MAXLINE);
    while (strcmp(buf, "\r\n") != 0) {
        if (strstr(buf, "Host")) {
            sprintf(request, "%sHost: %s\r\n", request, filename);
        } else if (strstr(uri, "Connection")) {
            sprintf(request, "%sConnection: close\r\n", request);
        } else if (strstr(uri, "Proxy-Connection")) {
            sprintf(request, "%sProxy-Connection: close\r\n", request);
        } else if (strstr(buf, "User-Agent")) {
            sprintf(request, "%s%s", request, user_agent_hdr);
        } else {
            sprintf(request, "%s%s", request, buf);
        }
        Rio_readlineb(rio, buf, MAXLINE);
    }
    sprintf(request, "%s%s", request, buf);
    printf("%s", request);
}

void *threadDoit(void *args) {
    int fd = (int)args;
    pthread_detach(pthread_self());
    doit(fd);
    Close(fd);
}

//初始化缓存
struct cache* initCache() {
    int i;
    pthread_mutex_init(&lock, NULL);
    struct cache *cache = (struct cache *)malloc(sizeof(struct cache) * (MAX_CACHE_SIZE / MAX_OBJECT_SIZE));
    struct cache *p;
    
    for (i = 0, p = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, p++){
        p->flag = 0;
        strcpy(p->url, "");
        p->dataSize = 0;
        memset(p->data, 0, MAX_OBJECT_SIZE);
        p->time = INT_MAX;
        pthread_rwlock_init(&p->rwlock, NULL);
    }
    return cache;
}

//读和解析缓存
int readCache(struct cache* cache, char *url, int fd) {
    int i;
    struct cache *p;
    
    for (i = 0, p = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, p++){
        if (p->flag != 0 && strcmp(url, p->url) == 0) {
            printf("url = %s p->url = %s\n", url, p->url);
            break;
        }
    }

    if ((MAX_CACHE_SIZE / MAX_OBJECT_SIZE) == i) {
        printf("Proxy can't read cache successfully.\n");
        return 0;
    }

    pthread_rwlock_rdlock(&p->rwlock);
    if (strcmp(url,p->url) != 0) {
        pthread_rwlock_unlock(&p->rwlock);
        return 0;
    }

    pthread_mutex_lock(&lock);
    p->time = INT_MAX;
    struct cache *pt;

    for (i = 0, pt = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, pt++) {
        if (pt != p) {
            pt->time--;
        }
    }

    pthread_mutex_unlock(&lock);
    Rio_writen(fd, p->data, p->dataSize);
    pthread_rwlock_unlock(&p->rwlock);
    printf("Proxy read cache successfully.\n");
    return 1;
}

void writeCache(struct cache* cache, char *url, char *data, int dataSize) {
    //寻找空的缓存块
    int i, max = INT_MAX;
    struct cache *p,*pt;
    for (i = 0, p = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, p++) {
        if (p->flag == 0) {
            break;
        }
    }

    //寻找最后一次访问过的缓存块
    if (i == (MAX_CACHE_SIZE / MAX_OBJECT_SIZE)) {
        for (i = 0, pt = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, pt++) {
            if (pt->time <= max) {
                max = pt->time;
                p = pt;
            }
        }
    }

    pthread_mutex_lock(&lock);
    p->time = INT_MAX;
    for (i = 0, pt = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, pt++) {
        if (pt != p) {
            pt->time--;
        }
    }

    pthread_mutex_unlock(&lock);
    p->flag = 1;
    pthread_rwlock_wrlock(&p->rwlock);
    memcpy(p->url, url, MAXLINE);
    memcpy(p->data, data, dataSize);
    p->dataSize = dataSize;
    pthread_rwlock_unlock(&p->rwlock);
    printf("write Cache\n");
}

//释放缓存
void freeCache(struct cache *cache) {
    int i;
    struct cache *p;
    pthread_mutex_destroy(&lock);
    for (i = 0, p = cache; i < (MAX_CACHE_SIZE / MAX_OBJECT_SIZE); i++, p++){
        pthread_rwlock_destroy(&p->rwlock);
    }
    free(cache);
}

//参照课本P655
int main(int argc, char *argv[])
{
    int listenfd, connfd;
    struct sockaddr clientaddr;
    pthread_t th;
    socklen_t clientlen;

    //检查命令行参数
    if (argc != 2) {
        printf("usage:%s <port> \n", argv[0]);
        exit(0);
    }

    //无法正确监听端口
    if ((listenfd = open_listenfd(argv[1])) == -1) {
        printf("Proxy can't open port %s .\n", argv[1]);
        exit(0);
    }

    //初始化缓存
    pCache = initCache();

    while(1) {
        clientlen = sizeof(clientaddr);
        connfd = Accept(listenfd, &clientaddr, &clientlen);
        pthread_create(&th, NULL, threadDoit, (void *)connfd);
    }

    //释放缓存
    freeCache(pCache);
    return 0;
}
