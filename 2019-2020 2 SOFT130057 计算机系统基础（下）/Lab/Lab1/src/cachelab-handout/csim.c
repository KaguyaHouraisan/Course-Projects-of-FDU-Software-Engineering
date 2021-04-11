#include "cachelab.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <unistd.h>
#include <limits.h>
#include <getopt.h>

//一个高速缓存行
typedef struct {
	int valid, tag, stamp;
} cacheLine;

//malloc式地动态分配数组
cacheLine **cache = NULL;

int v;  //是否输出详细运行过程信息
int s;  //组索引位的长度（位数）
int E;  //每一组包含的行数
int b;  //每个高速缓存块的大小（bit数）
int S;  //高速缓存组的组数
int numberOfHits;  //缓存命中次数
int numberOfMiss;  //缓存不命中次数
int numberOfEviction;  //驱逐次数
char filename[100];  //输入文件的路径
char buffer[1000];  //读取文件的缓存

//修改cache数组
void update(unsigned int address) {
	int t_address = address >> (s + b);  //组索引位
	int s_address = (address >> b) & ((-1U) >> (32 - s));  //块偏移位

	//检查是否发生了命中
	for(int i = 0; i < E; i++) {
		//发生了一次命中
		if(cache[s_address][i].tag == t_address) {
			cache[s_address][i].stamp = 0;
			numberOfHits++;
			if (v == 1) {
				printf("hit ");
			}
			return;
		}
	}

	//检查是否有空高速缓存行
	for(int i = 0; i < E; i++) {
		if(cache[s_address][i].valid == 0) {
			cache[s_address][i].valid = 1;
			cache[s_address][i].tag = t_address;
			cache[s_address][i].stamp = 0;
			numberOfMiss++;
			if (v == 1) {
				printf("miss ");
			}
			return;
		}
	}

	//若没有空高速缓存行，则发生一次驱逐
	int max_stamp = INT_MIN;
	int max_stamp_id = -1;
	numberOfEviction++;
	numberOfMiss++;
	for(int i = 0; i < E; i++) {
		if(cache[s_address][i].stamp > max_stamp) {
			max_stamp = cache[s_address][i].stamp;
			max_stamp_id = i;
			if (v == 1) {
				printf("miss eviction ");
			}
		}
	}
	cache[s_address][max_stamp_id].tag = t_address;
	cache[s_address][max_stamp_id].stamp = 0;
	return;
}

int main(int argc, char* argv[]) {
	int i, j, temp, opt;
	char type;
	unsigned int address;  //内存地址
	//初始化缓存命中数、不命中数和驱逐次数
	numberOfHits = 0;
	numberOfMiss = 0;
	numberOfEviction = 0;  

	//循环读取运行时提供的参数并为全局变量赋值
	opt = (getopt(argc, argv, "hvs:E:b:t:"));
	while(opt != -1) {
		if (opt == 'h') {  //若需要输出帮助信息
			printf("This is a simulator of cache with the following command-line arguments:\n");
			printf("-h             : Optional help flag that prints usage info\n");
			printf("-v             : Optional verbose flag that displays trace info\n");
			printf("-s <s>         : Number of set index bits (S = 2s is the number of sets)\n");
			printf("-E <E>         : Associativity (number of lines per set)\n");
			printf("-b <b>         : Number of block bits (B = 2b is the block size)\n");
			printf("-t <tracefile> : Name of the valgrind trace to replay\n");
		} else if (opt == 'v') {
			v = 1;
		} else if (opt == 's') {
			s = atoi(optarg);
			S = (1 << s);  //S = 2 ^ s
		} else if (opt == 'E') {
			E = atoi(optarg);
		} else if (opt == 'b') {
			b = atoi(optarg);
		} else if (opt == 't') {
			strcpy(filename, optarg);
		}
		opt = (getopt(argc, argv, "hvs:E:b:t:"));
	}

	//打开目标文件
	FILE* fp = fopen(filename, "r");
	//若打开文件发生错误
	if(fp == NULL) {
		printf("This file can not be found!\n");
		return -1;
	}
	
	//malloc动态分配数组的第2维
	cache = (cacheLine**)malloc(sizeof(cacheLine*) * S);
	//malloc动态分配数组的第1维
	for(int i = 0; i < S; i++) {
		cache[i] = (cacheLine*)malloc(sizeof(cacheLine) * E);
	}
	//对分配出的cache数组进行初始化
	for(int i = 0; i < S; i++) {
		for(int j = 0; j < E; j++) {
			cache[i][j].valid = 0;
			cache[i][j].tag = -1;
			cache[i][j].stamp = -1;
		}
	}

	//利用buffer数组读取目标文件中的内容，并以此对cache数组中的内容赋值
	while(fgets(buffer, 1000, fp)) {
		sscanf(buffer," %c %xu,%d", &type, &address, &temp);
		if (v == 1) {
			for (i = 1; i < 1000; i++) {
				if (buffer[i] != ',') {
					printf("%c", buffer[i]);
				} else {
					printf(",1 ");
					break;
				}
			}
		}
		switch(type) {
			case 'L':update(address);
					 break;
			case 'M':update(address);
			case 'S':update(address);
					 break;
		}
		if (v == 1) {
			printf("\n");
		}

		//修改高速缓存行
		for(i = 0; i < S; i++) {
			for(j = 0; j < E; j++) {
				if(cache[i][j].valid == 1) {
					//若标识位显示已被使用
					cache[i][j].stamp++;
				}
			}
		}
	}

	//释放为cache数组分配的空间
	for(int i = 0; i < S; i++) {
		free(cache[i]);
	}
	free(cache);
	//关闭目标文件
	fclose(fp);
	//输出结果
	printSummary(numberOfHits, numberOfMiss, numberOfEviction);
	return 0;
}

