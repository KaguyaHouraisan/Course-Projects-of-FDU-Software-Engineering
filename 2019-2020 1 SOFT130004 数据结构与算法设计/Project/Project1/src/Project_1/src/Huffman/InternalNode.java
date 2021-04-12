package Huffman;

class InternalNode extends Node {
    private Node leftchild;
    private Node rightchild;

    InternalNode(Node leftchild, Node rightnode){
        if (leftchild != null && rightnode != null){
            this.leftchild = leftchild;
            this.rightchild = rightnode;
        } else {
            throw new NullPointerException("Child is null");
        }
    }

    Node getLeftchild() {
        return leftchild;
    }

    Node getRightchild() {
        return rightchild;
    }
}
