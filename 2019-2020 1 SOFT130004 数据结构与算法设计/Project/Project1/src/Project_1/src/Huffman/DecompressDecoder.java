package Huffman;

import java.io.IOException;

public class DecompressDecoder {
    private BitsInputstream in;
    private Codetree codetree;

    DecompressDecoder(BitsInputstream bitsInputstream) throws IOException{
        if (bitsInputstream == null){
            throw new IOException("In is invalid");
        }
        this.in = bitsInputstream;
    }

    void setCodetree(Codetree codetree) {
        this.codetree = codetree;
    }

    public int read() throws IOException{
        if (codetree == null){
            throw new NullPointerException("CodeTree is empty");
        }
        InternalNode currentNode = codetree.getRoot();
        while (true){
            int current = in.isover();
            Node nextnode;
            if (current == 0){
                nextnode = currentNode.getLeftchild();
            } else if (current == 1){
                nextnode = currentNode.getRightchild();
            } else {
               return -1;
            }
            if (nextnode instanceof Leaf){
                return ((Leaf) nextnode).getCode();
            } else if (nextnode instanceof InternalNode){
                currentNode = (InternalNode)nextnode;
            } else {
                throw new IllegalArgumentException("Next node is illegal");
            }
        }
    }
}
