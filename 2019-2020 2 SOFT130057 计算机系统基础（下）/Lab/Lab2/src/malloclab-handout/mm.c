/*
 * mm.c
 * 17307110112 刘人豪
 * 二叉搜索树结构，分离空闲链表
 * 部分参考自如课本P599页之内容
 */
#include <assert.h>  
#include <stdio.h>  
#include <stdlib.h>  
#include <string.h>  
#include <unistd.h>  
#include <linux/kernel.h>  
#include <linux/stddef.h>  

#include "mm.h"  
#include "memlib.h"  

/* If you want debugging output, use the following macro.  When you hand
 * in, remove the #define DEBUG line. */
/*#define DEBUG
#ifdef DEBUG
# define dbg_printf(...) printf(__VA_ARGS__)
#else
# define dbg_printf(...)
#endif*/

/* do not change the following! */  
#ifdef DRIVER  
/* create aliases for driver tests */  
#define malloc mm_malloc  
#define free mm_free  
#define realloc mm_realloc  
#define calloc mm_calloc  
#endif /* def DRIVER */  

typedef void *block_ptr;  

//全局变量
static char *heap_listp = 0;        //第一个块
static block_ptr larger_bin_root;   //二叉搜索树的根结点
static block_ptr *bins;             //分离空闲链表的所有头  
static const unsigned int fixed_bin_count = 5;  //分离空闲链表的大小

//函数
static void place(block_ptr bp, size_t asize);  
static void insert_free_block(block_ptr bp, size_t blocksize);  
static void printblock(block_ptr bp);  
static void checkblock(block_ptr bp); 
static block_ptr coalesce(block_ptr bp);  
static block_ptr extend_heap(size_t words);   
static block_ptr find_fit(size_t asize);  

//宏定义
#define WSIZE       4                                                            //字长 
#define DSIZE       8                                                            //双字长
#define BLOCKSIZE   (1 << 6)                                                     //块大小
#define WNULL 0U                                                                 //无符号零
#define MAX(x, y) ((x) > (y)? (x) : (y))                                         //取大
#define ALIGNMENT   8                                                            //对齐
#define ALIGN(size) (((size) + (ALIGNMENT - 1)) & ~0x7)                          //取整对齐  
#define PACK(size, alloc)   ((size) | (alloc))                                   //加入分配位
#define GET(p)              (*(unsigned int *)(p))                               //读位于地址p的字
#define PUTTRUNC(p, val)    (GET(p) = (val))                                     //写位于地址p的字
#define PUT(p, val)         (GET(p) = (GET(p) & 0x2) | (val))                    //写位于地址p的头
#define GET_SIZE(p)         (GET(p) & ~0x7)                                      //读分配的块的大小
#define GET_ALLOC(p)        (GET(p) & 0x1)                                       //分配从地址p开始的区域
#define HDRP(bp)            ((char *)(bp) - WSIZE)                               //确定给定块指针的头部
#define FTRP(bp)            ((char *)(bp) + GET_SIZE(HDRP(bp)) - DSIZE)          //确定给定块指针的脚部
#define NEXT_BLKP(bp)       ((char *)(bp) + GET_SIZE(((char *)(bp) - WSIZE)))    //确定给定块指针的下一个块的地址
#define PREV_BLKP(bp)       ((char *)(bp) - GET_SIZE(((char *)(bp) - DSIZE)))    //确定给定块指针的前一个块的地址
#define SET_ALLOC(bp)       (GET(HDRP(NEXT_BLKP(bp))) |= 0x2)                    //设定给定块指针的分配
#define SET_UNALLOC(bp)     (GET(HDRP(NEXT_BLKP(bp))) &= ~0x2)                   //设定给定块指针的分配
#define GET_PREV_ALLOC(bp)  (GET(HDRP(bp)) & 0x2)                                //确定给定块指针是否分配
#define NEXT_SAMESZ_BLKP(bp)    (*(unsigned int *)(bp))                          //找到后一个有相同大小的空闲块
#define PREV_SAMESZ_BLKP(bp)    (*(unsigned int *)((char *)(bp) + WSIZE))        //找到前一个有相同大小的空闲块
#define IS_OVER_BST_SIZE(size)  ((size) > DSIZE * fixed_bin_count)               //判断数据量是否足够大
#define IS_BST_NODE(bp)         (IS_OVER_BST_SIZE(GET_SIZE(HDRP(bp))))           //判断某个块算不算二叉树上的节点
#define LCHLD_BLKPREF(bp)       ((block_ptr *)((char *)(bp) + DSIZE))            //计算块的左子块地址
#define RCHLD_BLKPREF(bp)       ((block_ptr *)((char *)(bp) + DSIZE * 2))        //计算块的右子块地址
#define PARENT_CHLDSLOTPREF(bp) ((block_ptr **)((char *)(bp) + DSIZE * 3))       //指向父结点的指针的指针
#define LCHLD_BLKP(bp) (*LCHLD_BLKPREF(bp))                                      //左子块的值
#define RCHLD_BLKP(bp) (*RCHLD_BLKPREF(bp))                                      //右子块的值
#define PARENT_CHLDSLOTP(bp) (*PARENT_CHLDSLOTPREF(bp))                          //父结点的值

/* Function definitions */  

//在二叉搜索树中寻找大于等于目标大小的块
block_ptr *bestfit_search(block_ptr *node, size_t size, int specific)  
{  
    block_ptr *candidate, curr = *node;  
    size_t curr_size;  
  
    if (curr == NULL) {
        return NULL;
    }

    curr_size = GET_SIZE(HDRP(curr));  
  
    if (size < curr_size) {  
        if (specific) {
            return bestfit_search(LCHLD_BLKPREF(curr), size, specific);
        }
        if ((candidate = bestfit_search(LCHLD_BLKPREF(curr), size, specific))) {
            return candidate;
        }
        return node;  
    } else if (size > curr_size) {
        return bestfit_search(RCHLD_BLKPREF(curr), size, specific);  
    } else {
        return node;
    }
}  

//返回该指针是否在堆中
static inline int in_heap(const block_ptr p)  
{  
    return p <= mem_heap_hi() && p >= mem_heap_lo();  
}  

//返回该指针是否已对齐
static inline int aligned(const block_ptr p)  
{  
    return ((unsigned long)p % ALIGNMENT) == 0;  
}

//将4位地址转为8位地址
static inline block_ptr word_to_ptr(unsigned int w)  
{  
    return ((w) == WNULL ? NULL : (block_ptr)((unsigned int)(w) + 0x800000000UL));  
}  

//将8位地址转为4位地址
static inline unsigned int ptr_to_word(block_ptr p)  
{  
    return ((p) == NULL ? WNULL : (unsigned int)((unsigned long)(p) - 0x800000000UL));  
}  

//重设空闲块bp的范围
void reset_block(block_ptr bp)
{ 
    NEXT_SAMESZ_BLKP(bp) = WNULL;
    PREV_SAMESZ_BLKP(bp) = WNULL; 
    if (IS_BST_NODE(bp)) { 
        LCHLD_BLKP(bp) = NULL;
        RCHLD_BLKP(bp) = NULL;
        PARENT_CHLDSLOTP(bp) = NULL;
    }
}  

//从空闲链表中移除被释放的块
void remove_linked_freed_block(block_ptr bp)
{
    if (PREV_SAMESZ_BLKP(bp)) {
        NEXT_SAMESZ_BLKP(word_to_ptr(PREV_SAMESZ_BLKP(bp))) = NEXT_SAMESZ_BLKP(bp);
    }
    if (NEXT_SAMESZ_BLKP(bp)) {
        PREV_SAMESZ_BLKP(word_to_ptr(NEXT_SAMESZ_BLKP(bp))) = PREV_SAMESZ_BLKP(bp);
    }
}  

//移除被释放的块
static inline void remove_freed_block(block_ptr bp)  
{  
    if (IS_BST_NODE(bp) && PARENT_CHLDSLOTP(bp)) {  //是节点，有父节点
        block_ptr l = LCHLD_BLKP(bp), r = RCHLD_BLKP(bp), cur;  //获得左右子节点
        if ((cur = word_to_ptr(NEXT_SAMESZ_BLKP(bp)))) {  //存在下一个相同大小空闲块
            PARENT_CHLDSLOTP(cur) = PARENT_CHLDSLOTP(bp);  //cur的一个parentchldslotp指向一个指向bp的指针
            *PARENT_CHLDSLOTP(bp) = cur;  //将原本指向bp的指针指向cur
            LCHLD_BLKP(cur) = l;  
            if (l) {  //有左子节点，设置cur的左子节点指向l
                PARENT_CHLDSLOTP(l) = LCHLD_BLKPREF(cur);
            }
            RCHLD_BLKP(cur) = r;  
            if (r) {  //有右子节点，设置cur的右子节点指向r
                PARENT_CHLDSLOTP(r) = RCHLD_BLKPREF(cur);
            }
        } else if (l && r) {  //没有下一个相同大小空闲块，但有左右子节点
            //维护操作二叉搜索树
            if (!(cur = LCHLD_BLKP(r))) {  
                LCHLD_BLKP(r) = l;  
                PARENT_CHLDSLOTP(r) = PARENT_CHLDSLOTP(bp);  
                *PARENT_CHLDSLOTP(bp) = r;  
                PARENT_CHLDSLOTP(l) = LCHLD_BLKPREF(r);  
            } else {  
                while (LCHLD_BLKP(cur)) {
                    cur = LCHLD_BLKP(cur);
                }
                *PARENT_CHLDSLOTP(bp) = cur;  
                *PARENT_CHLDSLOTP(cur) = RCHLD_BLKP(cur);  
                if (RCHLD_BLKP(cur)) {
                    PARENT_CHLDSLOTP(RCHLD_BLKP(cur)) = PARENT_CHLDSLOTP(cur);
                }
                PARENT_CHLDSLOTP(cur) = PARENT_CHLDSLOTP(bp);  
                LCHLD_BLKP(cur) = l;  
                RCHLD_BLKP(cur) = r;  
                PARENT_CHLDSLOTP(l) = LCHLD_BLKPREF(cur);  
                PARENT_CHLDSLOTP(r) = RCHLD_BLKPREF(cur);  
            }  
        } else if (r) {  
            *PARENT_CHLDSLOTP(bp) = r;  
            PARENT_CHLDSLOTP(r) = PARENT_CHLDSLOTP(bp);  
        } else if (l) {  
            *PARENT_CHLDSLOTP(bp) = l;  
            PARENT_CHLDSLOTP(l) = PARENT_CHLDSLOTP(bp);  
        } else  
            *PARENT_CHLDSLOTP(bp) = NULL;  
    } else if (!PREV_SAMESZ_BLKP(bp)) {  //没有前一个空闲块
        bins[GET_SIZE(HDRP(bp)) / DSIZE - 1] = word_to_ptr(NEXT_SAMESZ_BLKP(bp));
    }
    remove_linked_freed_block(bp);  
}

//合并，并将ptr返回到合并块
static block_ptr coalesce(block_ptr bp)  
{  
    block_ptr prev, next = NEXT_BLKP(bp);  

    //判断是否被分配
    size_t prev_alloc = GET_PREV_ALLOC(bp);  
    size_t next_alloc = GET_ALLOC(HDRP(next));  
    size_t size = GET_SIZE(HDRP(bp));  
  
    if (prev_alloc && next_alloc) {             //前一个被分配，后一个被分配
        return bp;  
    } else if (prev_alloc && !next_alloc) {     //只有前一个被分配
        remove_freed_block(next);  
        size += GET_SIZE(HDRP(next));  
        PUT(HDRP(bp), PACK(size, 0));  
        PUT(FTRP(bp), PACK(size, 0));  
    } else if (!prev_alloc && next_alloc) {     //只有后一个被分配
        prev = PREV_BLKP(bp);  
        remove_freed_block(prev);  
        size += GET_SIZE(HDRP(prev));  
        PUT(FTRP(bp), PACK(size, 0));  
        PUT(HDRP(prev), PACK(size, 0));  
        bp = prev;  
    } else {                                    //全未被分配
        prev = PREV_BLKP(bp);  
        remove_freed_block(next);  
        remove_freed_block(prev);  
        size += GET_SIZE(HDRP(prev)) + GET_SIZE(FTRP(next));  
        PUT(HDRP(prev), PACK(size, 0));  
        PUT(FTRP(next), PACK(size, 0));  
        bp = prev;  
    }  
    reset_block(bp);                            //重置块
    return bp;  
}  

//使用空闲块扩展堆并返回其指针
static block_ptr extend_heap(size_t words)  
{  
    char *bp;  
    size_t size;  

    //分配偶数来维持对齐
    size = (words % 2) ? (words + 1) * WSIZE : words * WSIZE;  
    if ((long)(bp = mem_sbrk(size)) == -1) {
        return NULL;
    }

    PUT(HDRP(bp), PACK(size, 0));           //初始化空闲块的头
    PUT(FTRP(bp), PACK(size, 0));           //初始化空闲块的脚
    reset_block(bp);  
    PUT(HDRP(NEXT_BLKP(bp)), PACK(0, 1));
  
    //如果前一个块空闲，合并
    return coalesce(bp);  
}  

//将size放在空闲块bp的开头，如果余数至少为最小块大小，则拆分
static void place(block_ptr bp, size_t asize)  
{  
    size_t csize = GET_SIZE(HDRP(bp)), delta = csize - asize;  
  
    if (delta >= (2 * DSIZE)) {  
        PUT(HDRP(bp), PACK(asize, 1));  
        PUT(FTRP(bp), PACK(asize, 1));  
        SET_ALLOC(bp);  
        bp = NEXT_BLKP(bp);  
        PUT(HDRP(bp), PACK(delta, 0));  
        PUT(FTRP(bp), PACK(delta, 0));  
        SET_UNALLOC(bp);  
        reset_block(bp);  
        insert_free_block(bp, delta);  
    } else {  
        PUT(HDRP(bp), PACK(csize, 1));  
        PUT(FTRP(bp), PACK(csize, 1));  
        SET_ALLOC(bp);  
    }  
}  

//向分离空闲链表中插入空闲块
static void insert_free_block(block_ptr bp, size_t blocksize)  
{  
    block_ptr *new = &larger_bin_root, parent = NULL;  
  
    if (!IS_OVER_BST_SIZE(blocksize)) {
        //插入到分离空闲链表
        size_t dcount = blocksize / DSIZE;  
        if (bins[dcount - 1]) {  
            //在已经存在的块之前连接它们来形成新的头
            NEXT_SAMESZ_BLKP(bp) = ptr_to_word(bins[dcount - 1]);  
            PREV_SAMESZ_BLKP(bins[dcount - 1]) = ptr_to_word(bp);  
        }  
        PREV_SAMESZ_BLKP(bp) = WNULL;  
        bins[dcount - 1] = bp;  
        return;  
    }  

    //找到放新节点的位置
    while (*new) {  
        size_t curr_size = GET_SIZE(HDRP(parent = *new));  
  
        if (blocksize < curr_size) {
            new = LCHLD_BLKPREF(parent);
        } else if (blocksize > curr_size) {
            new = RCHLD_BLKPREF(parent);
        } else {
            block_ptr next = word_to_ptr(NEXT_SAMESZ_BLKP(bp) = NEXT_SAMESZ_BLKP(parent));  
            if (next) {
                //在已经存在的块之前连接它们来形成新的头
                PREV_SAMESZ_BLKP(next) = ptr_to_word(bp);
            }
            NEXT_SAMESZ_BLKP(parent) = ptr_to_word(bp);  
            PREV_SAMESZ_BLKP(bp) = ptr_to_word(parent);  
            return;  
        }  
    }  

    //连接为子节点
    *new = bp;  
    PARENT_CHLDSLOTP(bp) = new;
}  
  
//寻找匹配目标大小的块
static block_ptr find_fit(size_t asize)  
{  
    block_ptr curr, *blocks;  
    size_t dcount = asize / DSIZE;  
  
    if (!IS_OVER_BST_SIZE(asize)) {  
        if (bins[dcount - 1]) {  //找到一个该大小对应的空闲链表
            curr = bins[dcount - 1];  
            bins[dcount - 1] = word_to_ptr(NEXT_SAMESZ_BLKP(curr));  
            remove_freed_block(curr);  
            return curr;  
        }  
    }  

    if ((blocks = bestfit_search(&larger_bin_root, asize, 0)) == NULL) {  //没有找到
        return NULL;
    }
    curr = *blocks;

    *blocks = word_to_ptr(NEXT_SAMESZ_BLKP(curr));
    remove_freed_block(curr);  
    return curr;  
}  

//打印块
static inline void printblock(block_ptr bp)  
{  
    size_t hsize, halloc, fsize, falloc;  
  
    hsize = GET_SIZE(HDRP(bp));  
    halloc = GET_ALLOC(HDRP(bp));  
    fsize = GET_SIZE(FTRP(bp));  
    falloc = GET_ALLOC(FTRP(bp));  
  
    if (hsize == 0) {  
        printf("%p: EOL\n", bp);  
        return;  
    }  
    if (halloc) {
        printf("\033[43;37m%p: header: [%zu:%c:%c] footer: -\033[0m\n", bp, hsize, (GET_PREV_ALLOC(bp) ? 'a' : 'f'), (halloc ? 'a' : 'f'));
    } else {  
        printf("\033[42;30m%p: header: [%zu:%c:%c] footer: [%zu:%c]\033[0m", bp, hsize, (GET_PREV_ALLOC(bp) ? 'a' : 'f'), (halloc ? 'a' : 'f'), fsize, (falloc ? 'a' : 'f'));  
        if (IS_BST_NODE(bp)) {
            printf("\033[1;44;33m[BST Node| parent slotp: %p, l: %p, r: %p]\033[0m", PARENT_CHLDSLOTP(bp), LCHLD_BLKP(bp), RCHLD_BLKP(bp));
        }
        if (PREV_SAMESZ_BLKP(bp)) {
            printf("\033[1;33m[PREV] %p\033[0m", word_to_ptr(PREV_SAMESZ_BLKP(bp)));
        }
        putchar('\n');  
    }  
}

//检查块
static inline void checkblock(block_ptr bp)  
{  
    if (!aligned(bp)) {
        printf("\n\033[1;47;31m## Error: %p is not doubleword aligned\033[0m\n", bp);
    }
    if (!GET_ALLOC(HDRP(bp)) && (GET(HDRP(bp)) & ~0x2) != (GET(FTRP(bp)) & ~0x2)) {
        printf("\n\033[1;47;31m## Error: header does not match footer, header: %u, footer: %u \033[0m\n", GET(HDRP(bp)), GET(FTRP(bp)));
    }
    if (GET_ALLOC(HDRP(bp)) != (GET_PREV_ALLOC(NEXT_BLKP(bp)) >> 1)) {
        printf("\n\033[1;47;31m## Error: %p allocation does not match next block's prev_alloc\033[0m\n", bp);
    }
}  

//递归地遍历打印链表
static void printchain(block_ptr node)  
{  
    while (node) {  
        printblock(node);  
        printf("->");  
        node = word_to_ptr(NEXT_SAMESZ_BLKP(node));  
    }  
}  

//递归地遍历打印树
static void printtree(block_ptr node, int depth)  
{  
    int i;  
    if (node == NULL) {
        return;
    }
    printf("BST: ");  
    for (i = 0; i < depth; i++) {
        putchar('-');
    }
    printchain(node);  
    putchar('\n');  
    printtree(LCHLD_BLKP(node), depth + 1);  
    printtree(RCHLD_BLKP(node), depth + 1);  
}  

//递归地遍历检查链表
static void checklist(block_ptr node)  
{  
    if (node == NULL) {
        return;
    }
    if (PREV_SAMESZ_BLKP(node) && word_to_ptr(NEXT_SAMESZ_BLKP(word_to_ptr(PREV_SAMESZ_BLKP(node)))) != node) {
        printf("\n\033[1;47;31m## Bad neighbor pointer: %p\033[0m\n", node);
    }
    checklist(word_to_ptr(NEXT_SAMESZ_BLKP(node)));
}  

//递归地遍历检查树
static void checktree(block_ptr node)  
{  
    if (node == NULL) {
        return;
    }
    if (*PARENT_CHLDSLOTP(node) != node) {
        printf("\n\033[1;47;31m## Bad parent pointer: %p\033[0m\n", node);
    }
    checklist(node);  
    checktree(LCHLD_BLKP(node));  
    checktree(RCHLD_BLKP(node));  
}

/* 
 * Initialize: return -1 on error, 0 on success. 
 */  
int mm_init(void)  
{  
    /* Create the initial empty heap */ 
    //分配4个字节的存储数据，5个双字节的额外空间；如果失败，返回-1
    if ((bins = mem_sbrk(ALIGN(fixed_bin_count * sizeof(block_ptr)) + 4 * WSIZE)) == (block_ptr) - 1) {
        return -1;
    }
    //把4个字节的存储空间清零
    memset(bins, 0, fixed_bin_count * sizeof(block_ptr));
    //指针的起始地址
    heap_listp = (char *)ALIGN((unsigned long)(bins + fixed_bin_count));  
    larger_bin_root = NULL;  
    PUTTRUNC(heap_listp, 0);                            // Alignment padding 
    PUTTRUNC(heap_listp + (1 * WSIZE), PACK(DSIZE, 1)); // Prologue header
    PUTTRUNC(heap_listp + (2 * WSIZE), PACK(DSIZE, 1)); // Prologue footer
    PUTTRUNC(heap_listp + (3 * WSIZE), PACK(0, 1));     // Epilogue header
    heap_listp += (2 * WSIZE);  
    //设置分配位
    SET_ALLOC(heap_listp);  
    return 0;  
}  

/* 
 * malloc 
 */  
block_ptr malloc(size_t size)  
{  
    //调整块大小
    size_t asize;
    //需要扩展的堆大小
    size_t extendsize;
    char *bp;
    
    //如果没有初始化
    if (heap_listp == 0) {
        mm_init();
    }  
  
    //忽略size = 0的情况
    if (size == 0) {
        return NULL;
    }

    //调整块大小来满足对齐要求和开销
    if (size <= DSIZE) {
        asize = 2 * DSIZE;
    } else {
        asize = DSIZE * ((size + (WSIZE) + (DSIZE - 1)) / DSIZE);
    }

    //寻找合适的空闲块
    if ((bp = find_fit(asize)) != NULL) {
        place(bp, asize);
        return bp;
    }  

    //没有找到适合的空闲块，开辟更多空间 
    extendsize = MAX(asize, BLOCKSIZE);  
    if ((bp = extend_heap(extendsize / WSIZE)) == NULL) {
        return NULL;
    }
    place(bp, asize);
    return bp;
}  

/* 
 * free 
 */  
void free(block_ptr ptr)  
{  
    block_ptr tmp;  
    size_t size;  
    if (!ptr || !in_heap(ptr) || !aligned(ptr)) {
        return;
    }

    //头指针的大小
    size = GET_SIZE(HDRP(ptr));
    PUT(HDRP(ptr), PACK(size, 0));
    PUT(FTRP(ptr), PACK(size, 0));
    SET_UNALLOC(ptr);
    reset_block(ptr);
    //合并指针
    tmp = coalesce(ptr);
    //插入空闲的块
    insert_free_block(tmp, GET_SIZE(HDRP(tmp)));  
}  

/* 
 * realloc - I don't want to look at mm-naive.c 
 */  
block_ptr realloc(block_ptr oldptr, size_t size)  
{  
    size_t oldsize;  
    block_ptr newptr;  
  
    //忽略size = 0的情况  
    if (size == 0) {  
        free(oldptr);
        return 0;
    }
  
    //如果原本的指针是空的，直接malloc
    if (oldptr == NULL) {  
        return malloc(size);
    }

    newptr = malloc(size);  
  
    //如果realloc失败，return 0
    if (!newptr) {  
        return 0;
    }

    //记录旧数据
    oldsize = GET_SIZE(HDRP(oldptr));  
    if (size < oldsize) {
        oldsize = size;
    }
    memcpy(newptr, oldptr, oldsize);
  
    //释放旧的块
    free(oldptr);
  
    return newptr;
}  

/* 
 * calloc - I don't want to look at mm-naive.c 
 * This function is not tested by mdriver, but it is 
 * needed to run the traces. 
 */  
block_ptr calloc(size_t nmemb, size_t size)  
{  
    size_t bytes = nmemb * size;  
    block_ptr newptr;  
    newptr = malloc(bytes);

    //在malloc的基础上设置为0
    memset(newptr, 0, bytes);  
    return newptr;  
}  
  
/* 
 * checkheap - check the heap for consistency 
 */  
void mm_checkheap(int verbose)  
{  
    char *bp = heap_listp;  
  
    if (verbose) {
        printf("Heap (%p):\n", heap_listp);
    }
  
    if ((GET_SIZE(HDRP(heap_listp)) != DSIZE) || !GET_ALLOC(HDRP(heap_listp))) {
        printf("\n\033[1;47;31m## Bad prologue header\033[0m\n");
    }
    checkblock(heap_listp);  
  
    for (bp = heap_listp; GET_SIZE(HDRP(bp)) > 0; bp = NEXT_BLKP(bp)) {  
        if (verbose) {
            printblock(bp);
        }
        checkblock(bp);  
    }  
  
    if (verbose) {  
        printblock(bp);  
        {  
            unsigned int i;  
            for (i = 1; i <= fixed_bin_count; i++) {
                if (bins[i - 1]) {  
                    printf("BIN #%d: size = %d ", i, i * DSIZE);  
                    checklist(bins[i - 1]);  
                    printchain(bins[i - 1]);  
                }
            }
            putchar('\n');  
            checktree(larger_bin_root);  
            printtree(larger_bin_root, 0);  
        }  
    }  
    if ((GET_SIZE(HDRP(bp)) != 0) || !(GET_ALLOC(HDRP(bp)))) {
        printf("\n\033[1;47;31m## Bad epilogue header\033[0m\n");
    }
} 


