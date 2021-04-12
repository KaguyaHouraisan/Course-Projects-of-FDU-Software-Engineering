package Huffman;

import java.io.IOException;
import java.io.OutputStream;

public class BitsOutputstream {
    private OutputStream outputStream;
    private int currentType;
    private int CurrentBits;

    BitsOutputstream(OutputStream out){
        if (out == null){
            throw new NullPointerException("out is null");
        }
        this.outputStream = out;
        this.CurrentBits = 0;
        this.currentType = 0;
    }

    public void write(int bits) throws IOException{
        if (bits != 0 && bits != 1){
            throw new IllegalArgumentException("Bit is not legal");
        }
        currentType = currentType<<1 | bits;
        CurrentBits++;
        if (CurrentBits == 8){
            outputStream.write(currentType);
            CurrentBits = 0;
        }
    }

    public void close() throws IOException {
        while (CurrentBits != 0) {
            write(1);
        }
        outputStream.close();
    }
}
