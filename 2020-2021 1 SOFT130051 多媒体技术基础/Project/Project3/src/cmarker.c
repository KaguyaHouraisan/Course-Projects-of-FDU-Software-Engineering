/** 
 * @file cmarker.c
 * @brief write JPEG markers.
 */

#include "cjpeg.h"
#include "cio.h"

/*
 * Length of APP0 block   (2 bytes)
 * Block ID               (4 bytes - ASCII "JFIF")
 * Zero byte              (1 byte to terminate the ID string)
 * Version Major, Minor   (2 bytes - major first)
 * Units                  (1 byte - 0x00 = none, 0x01 = inch, 0x02 = cm)
 * Xdpu                   (2 bytes - dots per unit horizontal)
 * Ydpu                   (2 bytes - dots per unit vertical)
 * Thumbnail X size       (1 byte)
 * Thumbnail Y size       (1 byte)
 */
void
write_app0(compress_io *cio)
{
    write_marker(cio, M_APP0);
    write_word(cio, 2 + 4 + 1 + 2 + 1 + 2 + 2 + 1 + 1); /* length */
    write_byte(cio, 0x4A);       /* Identifier: ASCII "JFIF" */
    write_byte(cio, 0x46);
    write_byte(cio, 0x49);
    write_byte(cio, 0x46);
    write_byte(cio, 0);
    write_byte(cio, 1); /* Version fields */
    write_byte(cio, 1);
    write_byte(cio, 0); /* Pixel size information */
    write_word(cio, 1);
    write_word(cio, 1);
    write_byte(cio, 0); /* No thumbnail image */
    write_byte(cio, 0);
}

void
write_sof0(compress_io *cio, bmp_info *binfo)
{
    write_marker(cio, M_SOF0);
    write_word(cio, 3 * COMP_NUM + 2 + 5 + 1); /* length */
    write_byte(cio, PRECISION);

    write_word(cio, binfo->height);
    write_word(cio, binfo->width);

    write_byte(cio, COMP_NUM);

    /*
     * Component:
     *  Component ID
     *  Sampling factors:   bit 0-3 vert., 4-7 hor.
     *  Quantization table No.
     */
    /* component Y */
    write_byte(cio, 1);
    write_byte(cio, 0x11);
    write_byte(cio, 0);
    /* component Cb */
    write_byte(cio, 2);
    write_byte(cio, 0x11);
    write_byte(cio, 1);
    /* component Cr */
    write_byte(cio, 3);
    write_byte(cio, 0x11);
    write_byte(cio, 1);
}

void
write_sos(compress_io *cio)
{
    write_marker(cio, M_SOS);
    write_word(cio, 2 + 1 + COMP_NUM * 2 + 3); /* length */

    write_byte(cio, COMP_NUM);

    /*
     * Component:
     *  Component ID
     *  DC & AC table No.   bits 0..3: AC table (0..3),
     *                      bits 4..7: DC table (0..3)
     */
    /* component Y */
    write_byte(cio, 1);
    write_byte(cio, 0x00);
    /* component Cb */
    write_byte(cio, 2);
    write_byte(cio, 0x11);
    /* component Cr */
    write_byte(cio, 3);
    write_byte(cio, 0x11);

    write_byte(cio, 0);       /* Ss */
    write_byte(cio, 0x3F);    /* Se */
    write_byte(cio, 0);       /* Bf */
}

void
write_dqt(compress_io *cio)
{
    /* index:
     *  bit 0..3: number of QT, Y = 0
     *  bit 4..7: precision of QT, 0 = 8 bit
     */
    int index;
    int i;
    write_marker(cio, M_DQT);
    write_word(cio, 2 + (DCTSIZE2 + 1) * 2);

    index = 0;                  /* table for Y */
    write_byte(cio, index);
    for (i = 0; i < DCTSIZE2; i++)
        write_byte(cio, q_tables.lu[i]);

    index = 1;                  /* table for Cb,Cr */
    write_byte(cio, index);
    for (i = 0; i < DCTSIZE2; i++)
        write_byte(cio, q_tables.ch[i]);
}

int
get_ht_length(UINT8 *nrcodes)
{
    int length = 0;
    int i;
    for (i = 1; i <= 16; i++)
        length += nrcodes[i];
    return length;
}

void
write_htable(compress_io *cio,
        UINT8 *nrcodes, UINT8 *values, int len, UINT8 index)
{
    /*
     * index:
     *  bit 0..3: number of HT (0..3), for Y = 0
     *  bit 4   : type of HT, 0 = DC table, 1 = AC table
     *  bit 5..7: not used, must be 0
     */
    write_byte(cio, index);

    int i;
    for (i = 1; i <= 16; i++)
        write_byte(cio, nrcodes[i]);
    for (i = 0; i < len; i++)
        write_byte(cio, values[i]);
}

void
write_dht(compress_io *cio)
{
    int len1, len2, len3, len4;

    write_marker(cio, M_DHT);

    len1 = get_ht_length(STD_LU_DC_NRCODES);
    len2 = get_ht_length(STD_LU_AC_NRCODES);
    len3 = get_ht_length(STD_CH_DC_NRCODES);
    len4 = get_ht_length(STD_CH_AC_NRCODES);
    write_word(cio, 2 + (1 + 16) * 4 + len1 + len2 + len3 + len4);

    write_htable(cio, STD_LU_DC_NRCODES, STD_LU_DC_VALUES, len1, 0x00);
    write_htable(cio, STD_LU_AC_NRCODES, STD_LU_AC_VALUES, len2, 0x10);
    write_htable(cio, STD_CH_DC_NRCODES, STD_CH_DC_VALUES, len3, 0x01);
    write_htable(cio, STD_CH_AC_NRCODES, STD_CH_AC_VALUES, len4, 0x11);
}

/*
 * Write datastream header.
 * This consists of an SOI and optional APPn markers.
 */
void
write_file_header(compress_io *cio)
{
    write_marker(cio, M_SOI);
    write_app0(cio);
}

/*
 * Write frame header.
 * This consists of DQT and SOFn markers.
 * Note that we do not emit the SOF until we have emitted the DQT(s).
 * This avoids compatibility problems with incorrect implementations that
 * try to error-check the quant table numbers as soon as they see the SOF.
 */
void
write_frame_header(compress_io *cio, bmp_info *binfo)
{
    write_dqt(cio);
    write_sof0(cio, binfo);
}

/*
 * Write scan header.
 * This consists of DHT or DAC markers, optional DRI, and SOS.
 * Compressed data will be written following the SOS.
 */
void
write_scan_header(compress_io *cio)
{
    write_dht(cio);
    write_sos(cio);

}

/*
 * Write datastream trailer.
 */
void
write_file_trailer(compress_io *cio)
{
    write_marker(cio, M_EOI);
}

