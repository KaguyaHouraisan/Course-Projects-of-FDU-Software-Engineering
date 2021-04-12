package Huffman;

import java.io.*;
import java.util.Arrays;

public class FileCompress {
    FileCompress (InputStream in, OutputStream out) throws IOException{
        InputStream inputStream;
        BitsOutputstream outputStream;
        inputStream = new BufferedInputStream(in);
        outputStream = new BitsOutputstream(new BufferedOutputStream(out));
        compress(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }

    public void compress(InputStream inputStream, BitsOutputstream outputStream)throws IOException{
        int[] frequencytable = new int[257];
        Arrays.fill(frequencytable,1);
        Frequencylist frequencylist = new Frequencylist(frequencytable);
        CompressEncoder encoder = new CompressEncoder(outputStream);
        encoder.setCodetree(frequencylist.buildcodetree());
        int i = 0;
        while (true){
            int symbol = inputStream.read();
            if (symbol == -1){
                break;
            }
            encoder.write(symbol);
            frequencylist.increment(symbol);
            i++;
            if (i < 262144 && isPowerOf2(i) || i % 262144 == 0){
            encoder.setCodetree(frequencylist.buildcodetree());
            }
            if (i % 262144 == 0) {
                frequencylist = new Frequencylist(frequencytable);
            }
       }
       encoder.write(256);
    }

    private static boolean isPowerOf2(int x) {
        return x > 0 && (x & -x) == x;
    }
}
