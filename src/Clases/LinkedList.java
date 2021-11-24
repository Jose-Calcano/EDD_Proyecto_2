/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author dianabello
 */
public class LinkedList {
    private Node head;
    private Node tail;

    public Node getHead() {
        return this.head;
    }
    /**
     * Constructor for initially empty list
     */
    public LinkedList() {
        this.head = this.tail = null;
    }

    /**
     * Constructor for list initially with one node
     * @param n
     */
    public LinkedList(Node n) {
        this.head = this.tail = n;
    }

    public void setHead(Node head) {
        this.head = head;
    }
    
    /**
     * Checks whether list is empty or not
     * @return 
     */
    private boolean isEmpty() {
        return this.head == null;
    }
    
    /**
     *  Get the current size of the list
     * @return 
     */
    public int size() {
        int i = 0;
        
        if (isEmpty()) {
            return 0;
        }
        
        Node aux = this.head;
        while(aux != null) {
            aux = aux.getNextList();
            i++;
        }
        return i;
    }
    
    /**
     * Add datum to the first position of the list
     * @param datum Datum to be added
     */
    public void addFirst(Object datum) {
        Node n = new Node(datum);
        if (isEmpty()) {
            this.head = n;
            this.tail = n;
            this.head.setNextList(this.tail);
            this.tail.setNextList(null);
        } else {
            n.setNextList(this.head);
            this.head = n;
        }
    }
    
    /**
     * Add datum to the last position of the list
     * @param datum Datum to be added
     */
    public void addLast(Object datum) {
        Node n = new Node(datum);
        if (isEmpty()) {
            this.head = n;
            this.tail = n;
            this.head.setNextList(this.tail);
            this.tail.setNextList(null);
        } else {
            this.tail.setNextList(n);
            this.tail = n;
        }
    }
    
    /**
     * Add datum to the specified position
     * @param datum Datum to be added
     * @param i Position to be added in
     */
    public void add(Object datum, int i) {
        if (isEmpty() || i == 0) {
            this.addFirst(datum);
        } else if (i >= (size() - 1)) {
            this.addLast(datum);
        } else if (i < 0) {
            this.add(datum, size() + i);
        }else {
            Node n = new Node(datum);
            Node aux = this.head; // Nodo previo
            int count = 0;
            while(count < i -1) {
                aux = aux.getNextList();
                count++;
            }
            n.setNextList(aux.getNextList());
            aux.setNextList(n);
        }
    }

    /**
     * Deletes first element of the list
     * @return The data of the first element
     */
    public Object deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        Node temp = this.head;
        this.head= this.head.getNextList();
        temp.setNextList(null);
        return temp.getData();
    }
    
    /**
     * Deletes last element of the list
     * @return The data of the last element
     */
    public Object deleteLast() {
        if (isEmpty()) {
            return null;
        }
        Node pre = this.head;
        while(pre.getNextList().getNextList() != null) {
            pre = pre.getNextList();
        }
        Node temp = pre.getNextList();
        pre.setNextList(null);
        this.tail = pre;
        temp.setNextList(null);
        return temp.getData();
        
    }
    
    /**
     * Deletes the element at the specified position
     * @param i The position to be deleted
     * @return The data of the deleted element
     */
    public Object delete(int i) {
        if (isEmpty()) {
            return null;
        } else if (i == 0) {
            return deleteFirst();
        } else if (i == size() -1) {
            return deleteLast();
        } else if (i < 0)  {
            return delete(size() + i);
        } else if (i > size() - 1) {
            System.out.println("\nError");
            return null;
        } else {
            Node aux = this.head;
            int count = 0;
            while(count < i-1) {
                aux = aux.getNextList();
                count++;
            }
            Node del = aux.getNextList();
            aux.setNextList(del.getNextList());
            del.setNextList(null);
            return del.getData();
        }
    }
    
    /**
     * Prints the list in a pretty format
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Vacia");
        } else {
            Node aux = this.head;
            int i = 0;
            while (aux != null) {
                System.out.print(aux.getData() + "(" + i + ")" + " -> ");
                aux = aux.getNextList();
                i++;
            }
            System.out.println("");
        }
    }
    
    public void sortList() {  
        //Node current will point to head  
        Node current = head, index = null;  
        Object temp;  
          
        if(head == null) {  
            return;  
        }  
        else {  
            while(current != null) {  
                //Node index will point to node next to current  
                index = current.getNextList();  
                  
                while(index != null) {  
                    //If current node's data is greater than index's node data, swap the data between them  
                    if(Node.comparator(current, index) > 0) {  
                        temp = current.getData();  
                        current.setData(index.getData());  
                        index.setData(temp);  
                    }  
                    index = index.getNextList();  
                }  
                current = current.getNextList();  
            }      
        }  
    }  
    
    // merging two linked list
	public Node sortedMerge(Node n1, Node n2) {
            // Base cases
            if (n1 == null)
                    return n2;

            else if (n2 == null)
                    return n1;

            Node result;

            if (Node.comparator(n1, n2) <= 0)
            {
                    result = n1;
                    result.setNextList(sortedMerge(n1.getNextList(), n2));
            }
            else
            {
                    result = n2;
                    result.setNextList(sortedMerge(n1, n2.getNextList()));
            }

            return result;
    }

//splitting list into two halves
    public Node[] splitList(Node n) {
            // base case
            if (n == null || n.getNextList() == null) {
                    return new Node[]{ n, null } ;
            }

            Node backward = n;
            Node forward = n.getNextList();

            // Forward moves twice and backward moves once
            while (forward != null)
            {
                    forward = forward.getNextList();
                    if (forward != null)
                    {
                            backward = backward.getNextList();
                            forward = forward.getNextList();
                    }
            }

    // splitting the linked list
            Node[] arr = new Node[]{ n, backward.getNextList() };
            backward.setNextList(null);

            return arr;
    }

    // Sorting linked list using merge sort.
    public Node mergeSort(Node head)
    {
            // Base case
            if (head == null || head.getNextList() == null) {
                    return head;
            }

            Node[] arr = splitList(head);
            Node first_half = arr[0];
            Node second_half = arr[1];


            first_half = mergeSort(first_half);
            second_half = mergeSort(second_half);

            // merge the two sorted list into single list.
            return sortedMerge(first_half, second_half);
    }
    
    /**
     * Gets the data of the element at the given index
     * @param index index of the element to look for
     * @return Data of the element at given index
     */
    public Object get(int index) {
        Node aux = this.head;
        int count = 0;
        while(count < index) {
            aux = aux.getNextList();
            count++;
        }
        
        return aux.getData();
    }

    /**
     * Replaces the first node of the list with another
     * @param datum data to be replaced in the first node
     */
    public void setFirst(Object datum) {
        if (isEmpty()) {
            addFirst(datum);
        } else {
            Node n = new Node(datum);
            n.setNextList(this.head.getNextList());
            this.head.setNextList(null);
            this.head = n;
        }
    }
    
    /**
     * Replaces the last node of the list with another
     * @param datum data to be replaced in the last node
     */
    public void setLast(Object datum) {
        Node n = new Node(datum);
        Node pre = this.head;
        while(pre.getNextList().getNextList() != null) {
            pre = pre.getNextList();
        }
        
        Node temp = pre.getNextList();
        pre.setNextList(n);
        this.tail = n;
        temp.setNextList(null);
    }
    
    /**
     * Replaces the node at the given index with another containing the given data
     * @param i index of the node to be replaced
     * @param datum data that will contain the node to replace with
     */
    public void set(int i, Object datum) {
        if (isEmpty() || i == 0) {
            setFirst(datum);
        } else if (i == size() - 1) {
            setLast(datum);
        } else if (i < 0) {
            set(size() + i, datum);
        } else if (i > size() - 1) {
            System.out.println("\nError");
        } else {
            Node n = new Node(datum);
            Node aux = this.head;
            int count = 0;
            while (count < i-1) {
                aux = aux.getNextList();
                count++;
            }
            
            Node toReplace = aux.getNextList();
            n.setNextList(toReplace.getNextList());
            aux.setNextList(n);
            toReplace.setNextList(null);
        }
    }
}
