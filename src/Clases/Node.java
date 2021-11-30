
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
     * @param datum
     */
    public Node(Object datum){
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
    
    public static int comparator(Node firstNode, Node secondNode) {
        String firstClass = firstNode.getData().getClass().getSimpleName();
        String secondClass = firstNode.getData().getClass().getSimpleName();
        if (!firstClass.equals(secondClass)) {
            throw new Error("Nodes does not have same data");
        }
        
        switch(firstClass) {
            case "Integer":
                Integer firstDataInteger = (Integer)firstNode.getData();
                Integer secondDataInteger = (Integer)secondNode.getData();
                if (firstDataInteger > secondDataInteger) {
                    return 1;
                } else if (secondDataInteger > firstDataInteger) {
                    return -1;
                } else {
                    return 0;
                }
            case "Double":
                Double firstDataDouble = (Double)firstNode.getData();
                Double secondDataDouble = (Double)secondNode.getData();
                if (firstDataDouble > secondDataDouble) {
                    return 1;
                } else if (secondDataDouble > firstDataDouble) {
                    return -1;
                } else {
                    return 0;
                }
            case "String":
                String firstDataString = (String)firstNode.getData();
                String secondDataString = (String)secondNode.getData();
                if (firstDataString.length() > secondDataString.length()) {
                    return 1;
                } else if (secondDataString.length() > firstDataString.length()) {
                    return -1;
                } else {
                    return 0;
                }
        }
        return 0;
    }
}
