/** 
 * @file cjpeg.c
 * @brief main file, convert BMP to JPEG image.
 */

#include "cjpeg.h"
#include "cio.h"

/* YCbCr to RGB transformation */

/*
 * precalculated tables for a faster YCbCr->RGB transformation.
 * use a INT32 table because we'll scale values by 2^16 and
 * work with integers.
 */

ycbcr_tables ycc_tables;

void
init_ycbcr_tables()
{
    UINT16 i;
    for (i = 0; i < 256; i++) {
        ycc_tables.r2y[i]  = (INT32)(65536 *  0.299   + 0.5) * i;
        ycc_tables.r2cb[i] = (INT32)(65536 * -0.16874 + 0.5) * i;
        ycc_tables.r2cr[i] = (INT32)(32768) * i;
        ycc_tables.g2y[i]  = (INT32)(65536 *  0.587   + 0.5) * i;
        ycc_tables.g2cb[i] = (INT32)(65536 * -0.33126 + 0.5) * i;
        ycc_tables.g2cr[i] = (INT32)(65536 * -0.41869 + 0.5) * i;
        ycc_tables.b2y[i]  = (INT32)(65536 *  0.114   + 0.5) * i;
        ycc_tables.b2cb[i] = (INT32)(32768) * i;
        ycc_tables.b2cr[i] = (INT32)(65536 * -0.08131 + 0.5) * i;
    }
}

void
rgb_to_ycbcr(UINT8 *rgb_unit, ycbcr_unit *ycc_unit, int x, int w)
{
    ycbcr_tables *tbl = &ycc_tables;
    UINT8 r, g, b;
    int src_pos = x * 3;
    src_pos += w * (DCTSIZE - 1) * 3;
    int dst_pos = 0;
    int i, j;
    for (j = 0; j < DCTSIZE; j++) {
        for (i = 0; i < DCTSIZE; i++) {
            b = rgb_unit[src_pos];
            g = rgb_unit[src_pos+1];
            r = rgb_unit[src_pos+2];
            ycc_unit->y[dst_pos] = (INT8) ((UINT8)
                                                   ((tbl->r2y[r] + tbl->g2y[g] + tbl->b2y[b]) >> 16) - 128);
            ycc_unit->cb[dst_pos] = (INT8) ((UINT8)
                    ((tbl->r2cb[r] + tbl->g2cb[g] + tbl->b2cb[b]) >> 16));
            ycc_unit->cr[dst_pos] = (INT8) ((UINT8)
                    ((tbl->r2cr[r] + tbl->g2cr[g] + tbl->b2cr[b]) >> 16));
            src_pos += 3;
            dst_pos++;
        }
        src_pos -= (w + DCTSIZE) * 3;
    }
}


/* quantization */

quant_tables q_tables;

void
init_quant_tables(UINT32 scale_factor)
{
    quant_tables *tbl = &q_tables;
    int temp1, temp2;
    int i;
    for (i = 0; i < DCTSIZE2; i++) {
        temp1 = ((UINT32) STD_LU_QTABLE[i] * scale_factor + 50) / 100;
        if (temp1 < 1)
            temp1 = 1;
        if (temp1 > 255)
            temp1 = 255;
        tbl->lu[ZIGZAG[i]] = (UINT8) temp1;

        temp2 = ((UINT32) STD_CH_QTABLE[i] * scale_factor + 50) / 100;
        if (temp2 < 1)
            temp2 = 1;
        if (temp2 > 255)
            temp2 = 255;
        tbl->ch[ZIGZAG[i]] = (UINT8) temp2;
    }
}

void
jpeg_quant(ycbcr_unit *ycc_unit, quant_unit *q_unit)
{
    quant_tables *tbl = &q_tables;
    float q_lu, q_ch;
    int x, y, i = 0;
    for (x = 0; x < DCTSIZE; x++) {
        for (y = 0; y < DCTSIZE; y++) {
            q_lu = 1.0 / ((double) tbl->lu[ZIGZAG[i]] * \
                    AAN_SCALE_FACTOR[x] * AAN_SCALE_FACTOR[y] * 8.0);
            q_ch = 1.0 / ((double) tbl->ch[ZIGZAG[i]] * \
                    AAN_SCALE_FACTOR[x] * AAN_SCALE_FACTOR[y] * 8.0);

            q_unit->y[i] = (INT16)(ycc_unit->y[i]*q_lu + 16384.5) - 16384;
            q_unit->cb[i] = (INT16)(ycc_unit->cb[i]*q_ch + 16384.5) - 16384;
            q_unit->cr[i] = (INT16)(ycc_unit->cr[i]*q_ch + 16384.5) - 16384;

            i++;
        }
    }
}


/* huffman compression */

huff_tables h_tables;

void
set_huff_table(UINT8 *nrcodes, UINT8 *values, BITS *h_table)
{
    int i, j, k;
    j = 0;
    UINT16 value = 0;
    for (i = 1; i <= 16; i++) {
        for (k = 0; k < nrcodes[i]; k++) {
            h_table[values[j]].len = i;
            h_table[values[j]].val = value;
            j++;
            value++;
        }
        value <<= 1;
    }
}

void
init_huff_tables()
{
    huff_tables *tbl = &h_tables;
    set_huff_table(STD_LU_DC_NRCODES, STD_LU_DC_VALUES, tbl->lu_dc);
    set_huff_table(STD_LU_AC_NRCODES, STD_LU_AC_VALUES, tbl->lu_ac);
    set_huff_table(STD_CH_DC_NRCODES, STD_CH_DC_VALUES, tbl->ch_dc);
    set_huff_table(STD_CH_AC_NRCODES, STD_CH_AC_VALUES, tbl->ch_ac);
}

void
set_bits(BITS *bits, INT16 data)
{
    /******************************************************/
    UINT16 pos_data;
    int i;

    if (data < 0) {
        pos_data = (UINT16) (~data + 1);
    } else {
        pos_data = (UINT16) data;
    }

    for (i = 15; i >= 0; i--) {
        if ((pos_data & (1 << i)) != 0) {
            break;
        }
    }

    bits->len = (UINT8) (i + 1);
    
    if (data < 0) {
        bits->val = (UINT16) (data + (1 << bits->len) - 1);
    } else {
        bits->val = (UINT16) data;
    }
    /******************************************************/
}

#ifdef DEBUG
void
print_bits(BITS bits)
{
    printf("%hu %hu\t", bits.len, bits.val);
}
#endif

/*
 * compress JPEG
 */
void
jpeg_compress(compress_io *cio,
        INT16 *data, INT16 *dc, BITS *dc_htable, BITS *ac_htable)
{
    INT16 zigzag_data[DCTSIZE2];
    BITS bits;
    INT16 diff;
    int i, j;
    int zero_num;
    int mark;

    /* zigzag encode */
    for (i = 0; i < DCTSIZE2; i++)
        zigzag_data[ZIGZAG[i]] = data[i];

    /* write DC */
    diff = zigzag_data[0] - *dc;
    *dc = zigzag_data[0];

    if (diff == 0)
        write_bits(cio, dc_htable[0]);
    else {
        set_bits(&bits, diff);
        write_bits(cio, dc_htable[bits.len]);
        write_bits(cio, bits);
    }

    /* write AC */
    int end = DCTSIZE2 - 1;
    while (zigzag_data[end] == 0 && end > 0)
        end--;
    for (i = 1; i <= end; i++) {
        j = i;
        while (zigzag_data[j] == 0 && j <= end)
            j++;
        zero_num = j - i;
        for (mark = 0; mark < zero_num / 16; mark++)
            write_bits(cio, ac_htable[0xF0]);
        zero_num = zero_num % 16;
        set_bits(&bits, zigzag_data[j]);
        write_bits(cio, ac_htable[zero_num * 16 + bits.len]);
        write_bits(cio, bits);
        i = j;
    }

    /* write end of unit */
    if (end != DCTSIZE2 - 1)
        write_bits(cio, ac_htable[0]);
}


/*
 * main JPEG encoding
 */
void
jpeg_encode(compress_io *cio, bmp_info *binfo)
{
    /* init tables */
    UINT32 scale = 50;
    init_ycbcr_tables();
    init_quant_tables(scale);
    init_huff_tables();

    /* write info */
    write_file_header(cio);
    write_frame_header(cio, binfo);
    write_scan_header(cio);

    /* encode */
    mem_mgr *in = cio->in;
    ycbcr_unit ycc_unit;
    quant_unit q_unit;

    INT16 dc_y = 0,
            dc_cb = 0,
            dc_cr = 0;
    int x, y;

    int offset = binfo->offset;
    int in_size = (int) (in->end - in->set);
    offset += (binfo->datasize / in_size - 1) * in_size;
    fseek(in->fp, offset, SEEK_SET);

    /******************************************************/
    for (y = 0; y < binfo->height; y += 8) {
        //刷新输入缓冲区
        if (!(in->flush_buffer)(cio)) {
            err_exit(BUFFER_READ_ERR);
        }

        for (x = 0; x < binfo->width; x += 8) {
            //将RGB单元转换为YCbCr单元
            rgb_to_ycbcr(in->set, &ycc_unit, x, binfo->width);

            //在YCbCr单元上基于DCT的顺序模式进行DCT变换
            jpeg_fdct(ycc_unit.y);
            jpeg_fdct(ycc_unit.cb);
            jpeg_fdct(ycc_unit.cr);

            //量化
            jpeg_quant(&ycc_unit, &q_unit);

            //利用Huffman编码进行压缩
            jpeg_compress(cio, q_unit.y, &dc_y, h_tables.lu_dc, h_tables.lu_ac);
            jpeg_compress(cio, q_unit.cb, &dc_cb, h_tables.ch_dc, h_tables.ch_ac);
            jpeg_compress(cio, q_unit.cr, &dc_cr, h_tables.ch_dc, h_tables.ch_ac);
        }
    }
    /******************************************************/

    write_align_bits(cio);

    /* write file end */
    write_file_trailer(cio);
}



bool
is_bmp(FILE *fp)
{
    UINT8 marker[3];
    if (fread(marker, sizeof(UINT16), 2, fp) != 2)
        err_exit(FILE_READ_ERR);
    if (marker[0] != 0x42 || marker[1] != 0x4D)
        return false;
    rewind(fp);
    return true;
}

void
err_exit(const char *error_string, int exit_num)
{
    printf(error_string);
    exit(exit_num);
}


void
print_help()
{
    printf("compress BMP file into JPEG file.\n");
    printf("Usage:\n");
    printf("    cjpeg {BMP} {JPEG}\n");
    printf("\n");
    printf("Author: Yu, Le <yeolar@gmail.com>\n");
}



int
main(int argc, char *argv[])
{
    if (argc == 3) {
        /* open bmp file */
        FILE *bmp_fp = fopen(argv[1], "rb");
        if (!bmp_fp)
            err_exit(FILE_OPEN_ERR);
        if (!is_bmp(bmp_fp))
            err_exit(FILE_TYPE_ERR);

        /* open jpeg file */
        FILE *jpeg_fp = fopen(argv[2], "wb");
        if (!jpeg_fp)
            err_exit(FILE_OPEN_ERR);

        /* get bmp info */
        bmp_info binfo;
        read_bmp(bmp_fp, &binfo);

        /* init memory for input and output */
        compress_io cio;
        int in_size = (binfo.width * 3 + 3) / 4 * 4 * DCTSIZE;
        int out_size = MEM_OUT_SIZE;
        init_mem(&cio, bmp_fp, in_size, jpeg_fp, out_size);

        /* main encode process */
        jpeg_encode(&cio, &binfo);

        /* flush and free memory, close files */
        if (! (cio.out->flush_buffer) (&cio))
            err_exit(BUFFER_WRITE_ERR);
        free_mem(&cio);
        fclose(bmp_fp);
        fclose(jpeg_fp);
    }
    else
        print_help();
    exit(0);
}
