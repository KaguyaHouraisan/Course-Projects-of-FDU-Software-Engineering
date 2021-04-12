package Huffman;

import java.io.IOException;
import java.util.List;

public class CompressEncoder {
    private  BitsOutputstream out;
    private Codetree codetree;

    CompressEncoder(BitsOutputstream out){
        this.out=out;
    }

    void setCodetree(Codetree codetree) {
        this.codetree = codetree;
    }

    public void write(int Symbol) throws IOException{
        List<Integer> bits = codetree.getCode(Symbol);
        for (int i: bits){
            out.write(i);
        }
    }
}
