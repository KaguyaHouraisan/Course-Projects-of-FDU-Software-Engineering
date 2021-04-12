package Huffman;

public class Leaf extends Node{
    private int code;

    Leaf(int code){
        if (code >= 0){
            this.code = code;
        }
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
