/** 
 * @file cjpeg.h
 * @brief head file of cjpeg, include defines, prototypes.
 */

#ifndef __CJPEG_H
#define __CJPEG_H

#include <stdio.h>
#include <stdlib.h>


typedef unsigned char   UINT8;
typedef unsigned short  UINT16;
typedef unsigned int    UINT32;

typedef char            INT8;
typedef short           INT16;
typedef int             INT32;

typedef unsigned char   bool;   /* no bool type in C */
#define true            1;
#define false           0;


#define FILE_TYPE_ERR       "file type error", 1
#define FILE_OPEN_ERR       "fopen: open file error", 2
#define FILE_READ_ERR       "fread: read file error", 3
#define BUFFER_ALLOC_ERR    "malloc: alloc buffer error", 4
#define BUFFER_READ_ERR     "fread: read buffer error", 5
#define BUFFER_WRITE_ERR    "fwrite: write buffer error", 6


#define REVERSED        /* regularly, BMP image is stored reversely */

#define MEM_OUT_SIZE    1 << 17 /* alloc output memory with 128 KB */
#define BMP_HEAD_LEN    54      /* file head length of BMP image */
#define COMP_NUM        3       /* number of components */
#define PRECISION       8       /* data precision */
#define DCTSIZE         8       /* data unit size */
#define DCTSIZE2        64      /* data unit value number */


typedef enum {                  /* JPEG marker codes */
    M_SOF0  = 0xc0,
    M_SOF1  = 0xc1,
    M_SOF2  = 0xc2,
    M_SOF3  = 0xc3,
    
    M_SOF5  = 0xc5,
    M_SOF6  = 0xc6,
    M_SOF7  = 0xc7,
    
    M_JPG   = 0xc8,
    M_SOF9  = 0xc9,
    M_SOF10 = 0xca,
    M_SOF11 = 0xcb,
    
    M_SOF13 = 0xcd,
    M_SOF14 = 0xce,
    M_SOF15 = 0xcf,
    
    M_DHT   = 0xc4,
    
    M_DAC   = 0xcc,
    
    M_RST0  = 0xd0,
    M_RST1  = 0xd1,
    M_RST2  = 0xd2,
    M_RST3  = 0xd3,
    M_RST4  = 0xd4,
    M_RST5  = 0xd5,
    M_RST6  = 0xd6,
    M_RST7  = 0xd7,
    
    M_SOI   = 0xd8,
    M_EOI   = 0xd9,
    M_SOS   = 0xda,
    M_DQT   = 0xdb,
    M_DNL   = 0xdc,
    M_DRI   = 0xdd,
    M_DHP   = 0xde,
    M_EXP   = 0xdf,
    
    M_APP0  = 0xe0,
    M_APP1  = 0xe1,
    M_APP2  = 0xe2,
    M_APP3  = 0xe3,
    M_APP4  = 0xe4,
    M_APP5  = 0xe5,
    M_APP6  = 0xe6,
    M_APP7  = 0xe7,
    M_APP8  = 0xe8,
    M_APP9  = 0xe9,
    M_APP10 = 0xea,
    M_APP11 = 0xeb,
    M_APP12 = 0xec,
    M_APP13 = 0xed,
    M_APP14 = 0xee,
    M_APP15 = 0xef,
    
    M_JPG0  = 0xf0,
    M_JPG13 = 0xfd,
    M_COM   = 0xfe,
    
    M_TEM   = 0x01,
    
    M_ERROR = 0x100
} JPEG_MARKER;


typedef struct {
    UINT8 len;
    UINT16 val;
} BITS;


/* zigzag table */

static UINT8 ZIGZAG[DCTSIZE2] = {
     0,   1,   5,   6,  14,  15,  27,  28,
     2,   4,   7,  13,  16,  26,  29,  42,
     3,   8,  12,  17,  25,  30,  41,  43,
     9,  11,  18,  24,  31,  40,  44,  53,
    10,  19,  23,  32,  39,  45,  52,  54,
    20,  22,  33,  38,  46,  51,  55,  60,
    21,  34,  37,  47,  50,  56,  59,  61,
    35,  36,  48,  49,  57,  58,  62,  63
};


/* RGB to YCbCr table */

typedef struct {
    INT32 r2y[256];
    INT32 r2cb[256];
    INT32 r2cr[256];
    INT32 g2y[256];
    INT32 g2cb[256];
    INT32 g2cr[256];
    INT32 b2y[256];
    INT32 b2cb[256];
    INT32 b2cr[256];
} ycbcr_tables;

extern ycbcr_tables ycc_tables;

/* store color unit in YCbCr */

typedef struct {
    float y[DCTSIZE2];
    float cb[DCTSIZE2];
    float cr[DCTSIZE2];
} ycbcr_unit;


/* standard quantization tables */

static UINT8 STD_LU_QTABLE[DCTSIZE2] = {       /* luminance */
    16,  11,  10,  16,  24,  40,  51,  61,
    12,  12,  14,  19,  26,  58,  60,  55,
    14,  13,  16,  24,  40,  57,  69,  56,
    14,  17,  22,  29,  51,  87,  80,  62,
    18,  22,  37,  56,  68, 109, 103,  77,
    24,  35,  55,  64,  81, 104, 113,  92,
    49,  64,  78,  87, 103, 121, 120, 101,
    72,  92,  95,  98, 112, 100, 103,  99
};

static UINT8 STD_CH_QTABLE[DCTSIZE2] = {       /* chrominance */
    17,  18,  24,  47,  99,  99,  99,  99,
    18,  21,  26,  66,  99,  99,  99,  99,
    24,  26,  56,  99,  99,  99,  99,  99,
    47,  66,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99
};

static double AAN_SCALE_FACTOR[DCTSIZE] = {
    1.0,
    1.387039845,
    1.306562965,
    1.175875602,
    1.0,
    0.785694958,
    0.541196100,
    0.275899379
};

/* store scaled quantization tables */

typedef struct {
    UINT8 lu[DCTSIZE2];
    UINT8 ch[DCTSIZE2];
} quant_tables;

extern quant_tables q_tables;

/* store color unit after quantizing operation */

typedef struct {
    INT16 y[DCTSIZE2];
    INT16 cb[DCTSIZE2];
    INT16 cr[DCTSIZE2];
} quant_unit;


/* standard huffman tables */

/* luminance DC */

static UINT8 STD_LU_DC_NRCODES[17] = {       /* code No. */
    0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};

static UINT8 STD_LU_DC_VALUES[12] = {        /* code value */
    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

/* chrominance DC */

static UINT8 STD_CH_DC_NRCODES[17] = {
    0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0};

static UINT8 STD_CH_DC_VALUES[12] = {
    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

/* luminance AC */

static UINT8 STD_LU_AC_NRCODES[17] = {
    0, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 0x7d};

static UINT8 STD_LU_AC_VALUES[162] = {
      0x01, 0x02, 0x03, 0x00, 0x04, 0x11, 0x05, 0x12,
      0x21, 0x31, 0x41, 0x06, 0x13, 0x51, 0x61, 0x07,
      0x22, 0x71, 0x14, 0x32, 0x81, 0x91, 0xa1, 0x08,
      0x23, 0x42, 0xb1, 0xc1, 0x15, 0x52, 0xd1, 0xf0,
      0x24, 0x33, 0x62, 0x72, 0x82, 0x09, 0x0a, 0x16,
      0x17, 0x18, 0x19, 0x1a, 0x25, 0x26, 0x27, 0x28,
      0x29, 0x2a, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
      0x3a, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49,
      0x4a, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
      0x5a, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69,
      0x6a, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79,
      0x7a, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89,
      0x8a, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98,
      0x99, 0x9a, 0xa2, 0xa3, 0xa4, 0xa5, 0xa6, 0xa7,
      0xa8, 0xa9, 0xaa, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6,
      0xb7, 0xb8, 0xb9, 0xba, 0xc2, 0xc3, 0xc4, 0xc5,
      0xc6, 0xc7, 0xc8, 0xc9, 0xca, 0xd2, 0xd3, 0xd4,
      0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xda, 0xe1, 0xe2,
      0xe3, 0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9, 0xea,
      0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
      0xf9, 0xfa
};

/* chrominance AC */

static UINT8 STD_CH_AC_NRCODES[17] = {
    0, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 0x77};

static UINT8 STD_CH_AC_VALUES[162] = {
      0x00, 0x01, 0x02, 0x03, 0x11, 0x04, 0x05, 0x21,
      0x31, 0x06, 0x12, 0x41, 0x51, 0x07, 0x61, 0x71,
      0x13, 0x22, 0x32, 0x81, 0x08, 0x14, 0x42, 0x91,
      0xa1, 0xb1, 0xc1, 0x09, 0x23, 0x33, 0x52, 0xf0,
      0x15, 0x62, 0x72, 0xd1, 0x0a, 0x16, 0x24, 0x34,
      0xe1, 0x25, 0xf1, 0x17, 0x18, 0x19, 0x1a, 0x26,
      0x27, 0x28, 0x29, 0x2a, 0x35, 0x36, 0x37, 0x38,
      0x39, 0x3a, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48,
      0x49, 0x4a, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
      0x59, 0x5a, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
      0x69, 0x6a, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
      0x79, 0x7a, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
      0x88, 0x89, 0x8a, 0x92, 0x93, 0x94, 0x95, 0x96,
      0x97, 0x98, 0x99, 0x9a, 0xa2, 0xa3, 0xa4, 0xa5,
      0xa6, 0xa7, 0xa8, 0xa9, 0xaa, 0xb2, 0xb3, 0xb4,
      0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xba, 0xc2, 0xc3,
      0xc4, 0xc5, 0xc6, 0xc7, 0xc8, 0xc9, 0xca, 0xd2,
      0xd3, 0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xda,
      0xe2, 0xe3, 0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9,
      0xea, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
      0xf9, 0xfa
};

/* store precalculated huffman tables */

typedef struct {

    BITS lu_dc[12];
    BITS lu_ac[256];
    BITS ch_dc[12];
    BITS ch_ac[256];
} huff_tables;

extern huff_tables h_tables;


/* store BMP image informations */

typedef struct {
    UINT32 size;      /* bmp file size:                        2- 5 */
    UINT32 offset;    /* offset between file start and data:  10-13 */
    UINT32 width;     /* pixel width of bmp image:            18-21 */
    UINT32 height;    /* pixel height of bmp image:           22-25 */
    UINT16 bitppx;    /* bit number per pixel:                28-29 */
    UINT32 datasize;  /* image data size:                     34-37 */
} bmp_info;


extern void err_exit(const char *error_string, int exit_num);


#endif /* __CJPEG_H */

