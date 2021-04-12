package Huffman;

import java.io.*;
import java.util.Arrays;

class FileDecompress {
    FileDecompress(InputStream in, OutputStream out) throws IOException{
        BitsInputstream inputStream;
        OutputStream outputStream;
        inputStream = new BitsInputstream(new BufferedInputStream(in));
        outputStream = new BufferedOutputStream(out);
        decompress(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }

    private void decompress(BitsInputstream bitsInputstream, OutputStream outputStream) throws IOException {
        int[] frquencytable = new int[257];
        Arrays.fill(frquencytable,1);
        Frequencylist frequencylist = new Frequencylist(frquencytable);
        DecompressDecoder decompressDecoder = new DecompressDecoder(bitsInputstream);
        decompressDecoder.setCodetree(frequencylist.buildcodetree());
        int i = 0;
        while (true){
            int symbol = decompressDecoder.read();
            if (symbol == 256){
                break;
            }
            outputStream.write(symbol);
            frequencylist.increment(symbol);
            i++;
            if (i < 262144 && ispowerof2(i) || i % 262144 == 0){
                decompressDecoder.setCodetree(frequencylist.buildcodetree());
            }
            if (i % 262144 == 0){
                frequencylist=new Frequencylist(frquencytable);
            }
        }
    }

    private boolean ispowerof2(int x){
        return x > 0 && (x & -x) == x;
    }
}
