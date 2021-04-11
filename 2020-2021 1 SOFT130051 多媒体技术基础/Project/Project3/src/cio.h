/** 
 * @file cio.h
 * @brief memory manager and operations for compressing JPEG IO.
 */

#ifndef __CIO_H
#define __CIO_H

typedef bool (*CIO_METHOD) (void *);

typedef struct {
    UINT8 *set;
    UINT8 *pos;
    UINT8 *end;
    CIO_METHOD flush_buffer;
    FILE *fp;
} mem_mgr;

typedef struct {
    mem_mgr *in;
    mem_mgr *out;
    BITS temp_bits;

} compress_io;


bool flush_cin_buffer(void *cio);
bool flush_cout_buffer(void *cio);

void init_mem(compress_io *cio,
              FILE *in_fp, int in_size, FILE *out_fp, int out_size);
void free_mem(compress_io *cio);

void write_byte(compress_io *cio, UINT8 val);
void write_word(compress_io *cio, UINT16 val);
void write_marker(compress_io *cio, JPEG_MARKER mark);
void write_bits(compress_io *cio, BITS bits);
void write_align_bits(compress_io *cio);

#endif /* __CIO_H */

