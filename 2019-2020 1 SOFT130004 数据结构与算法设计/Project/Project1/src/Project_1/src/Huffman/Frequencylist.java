package Huffman;

import java.util.PriorityQueue;
import java.util.Queue;

public class Frequencylist {
    private int[] frequencytable;

    Frequencylist(int [] frequencytable){
        if (frequencytable == null){
            throw new NullPointerException("Table is empty");
        }
        this.frequencytable = frequencytable.clone();
        for (int x: this.frequencytable){
            if (x < 0){
                throw new IllegalArgumentException("Negative symbol");
            }
        }
    }

    public void increment(int symbol){
        if (symbol < 0 || symbol > frequencytable.length){
            throw new IllegalArgumentException("Symbol is out of range");
        }
        frequencytable[symbol]++;
    }

    Codetree buildcodetree(){
        Queue<Node_frequency> codetree = new PriorityQueue<>();
        for (int i = 0; i < frequencytable.length; i++){
            if (frequencytable[i] > 0){
                codetree.add(new Node_frequency(new Leaf(i), i, frequencytable[i]));
            }
        }
        for (int i = 0; i < frequencytable.length && codetree.size() < 2; i++) {
            if (frequencytable[i] == 0) {
                codetree.add(new Node_frequency(new Leaf(i), i, 0));
            }
        }
        while (codetree.size()>1){
            Node_frequency least1 = codetree.remove();
            Node_frequency least2 = codetree.remove();
            codetree.add(new Node_frequency(new InternalNode(least1.getNode(),least2.getNode()) ,Math.min(least1.getSymbol(),least2.getSymbol()), least1.getFrquencies()+least2.getFrquencies()));
        }
        return new Codetree((InternalNode) codetree.remove().getNode(),frequencytable.length);
    }

    private class Node_frequency implements Comparable<Node_frequency>{
        private Node node;
        private int Symbol;
        private long frquencies;

        Node_frequency(Node node, int Symbol, long frequencies){
            this.node = node;
            this.Symbol = Symbol;
            this.frquencies = frequencies;
        }

        long getFrquencies() {
            return frquencies;
        }

        public Node getNode() {
            return node;
        }

        public int getSymbol() {
            return Symbol;
        }

        public int compareTo(Node_frequency another){
            if (this.frquencies < another.getFrquencies()){
                return -1;
            } else if (this.frquencies > another.getFrquencies()){
                return 1;
            } else if (Symbol < another.Symbol) {
                return -1;
            } else if (Symbol > another.Symbol) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
