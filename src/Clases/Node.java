package Clases;

/**
 *
 * @author Diana Bello
 *
 */
public class Node {

    // Attributes
    private Object data;
    private String word;
    private Node nextList;
    private Node nextHash;
    public int repeat;

    /**
     * Node constructor of the hashTable
     *
     * @param word
     */
    public Node(String word) {
        this.nextHash = null;
        this.nextList = null;
        this.word = word;
        this.repeat = 1;
    }

    /**
     * Node constructor of the linkedList
     *
     * @param datum
     */
    public Node(Object datum) {
        this.data = datum;
        this.nextList = null;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Node getNextList() {
        return nextList;
    }

    public void setNextList(Node nextList) {
        this.nextList = nextList;
    }

    public Node getNextHash() {
        return nextHash;
    }

    public void setNextHash(Node nextHash) {
        this.nextHash = nextHash;
    }
}
