package Huffman;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BitsInputstream {
    private InputStream in;
    private int currentBytes;
    private int numBitsIncurrentByte;
    private boolean isover;

    BitsInputstream(InputStream in){
        this.in = in;
        this.numBitsIncurrentByte = 0;
        this.isover = false;
    }

    public int read() throws IOException{
         if (isover){
             return -1;
         }
         if (numBitsIncurrentByte == 0){
             currentBytes = in.read();
             if (currentBytes == -1){
                 isover = true;
                 return -1;
             }
             numBitsIncurrentByte = 8;
         }
         numBitsIncurrentByte--;
         return currentBytes>>>numBitsIncurrentByte & 1;
    }

    int isover() throws IOException{
        int result = read();
        if (result != -1){
            return result;
        } else {
           throw new EOFException();
        }
    }

    public void close () throws IOException{
        in.close();
    }
}
