/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Main.Node;

/**
 *
 * @author dianabello
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public Node<T> getHead() {
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
    public LinkedList(Node<T> n) {
        this.head = this.tail = n;
    }

    public void setHead(Node<T> head) {
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
        
        Node<T> aux = this.head;
        while(aux != null) {
            aux = aux.getNext();
            i++;
        }
        return i;
    }
    
    /**
     * Add datum to the first position of the list
     * @param datum Datum to be added
     */
    public void addFirst(T datum) {
        Node<T> n = new Node(datum);
        if (isEmpty()) {
            this.head = n;
            this.tail = n;
            this.head.setNext(this.tail);
            this.tail.setNext(null);
        } else {
            n.setNext(this.head);
            this.head = n;
        }
    }
    
    /**
     * Add datum to the last position of the list
     * @param datum Datum to be added
     */
    public void addLast(T datum) {
        Node<T> n = new Node(datum);
        if (isEmpty()) {
            this.head = n;
            this.tail = n;
            this.head.setNext(this.tail);
            this.tail.setNext(null);
        } else {
            this.tail.setNext(n);
            this.tail = n;
        }
    }
    
    /**
     * Add datum to the specified position
     * @param datum Datum to be added
     * @param i Position to be added in
     */
    public void add(T datum, int i) {
        if (isEmpty() || i == 0) {
            this.addFirst(datum);
        } else if (i >= (size() - 1)) {
            this.addLast(datum);
        } else if (i < 0) {
            this.add(datum, size() + i);
        }else {
            Node<T> n = new Node(datum);
            Node aux = this.head; // Nodo previo
            int count = 0;
            while(count < i -1) {
                aux = aux.getNext();
                count++;
            }
            n.setNext(aux.getNext());
            aux.setNext(n);
        }
    }

    /**
     * Deletes first element of the list
     * @return The data of the first element
     */
    public T deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = this.head;
        this.head= this.head.getNext();
        temp.setNext(null);
        return temp.getData();
    }
    
    /**
     * Deletes last element of the list
     * @return The data of the last element
     */
    public T deleteLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> pre = this.head;
        while(pre.getNext().getNext() != null) {
            pre = pre.getNext();
        }
        Node<T> temp = pre.getNext();
        pre.setNext(null);
        this.tail = pre;
        temp.setNext(null);
        return temp.getData();
        
    }
    
    /**
     * Deletes the element at the specified position
     * @param i The position to be deleted
     * @return The data of the deleted element
     */
    public T delete(int i) {
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
            Node<T> aux = this.head;
            int count = 0;
            while(count < i-1) {
                aux = aux.getNext();
                count++;
            }
            Node<T> del = aux.getNext();
            aux.setNext(del.getNext());
            del.setNext(null);
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
                aux = aux.getNext();
                i++;
            }
            System.out.println("");
        }
    }
    
    public void sortList() {  
        //Node current will point to head  
        Node<T> current = head, index = null;  
        T temp;  
          
        if(head == null) {  
            return;  
        }  
        else {  
            while(current != null) {  
                //Node index will point to node next to current  
                index = current.getNext();  
                  
                while(index != null) {  
                    //If current node's data is greater than index's node data, swap the data between them  
                    if(Node.comparator(current, index) > 0) {  
                        temp = current.getData();  
                        current.setData(index.getData());  
                        index.setData(temp);  
                    }  
                    index = index.getNext();  
                }  
                current = current.getNext();  
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
                    result.setNext(sortedMerge(n1.getNext(), n2));
            }
            else
            {
                    result = n2;
                    result.setNext(sortedMerge(n1, n2.getNext()));
            }

            return result;
    }

//splitting list into two halves
    public Node[] splitList(Node n) {
            // base case
            if (n == null || n.getNext() == null) {
                    return new Node[]{ n, null } ;
            }

            Node backward = n;
            Node forward = n.getNext();

            // Forward moves twice and backward moves once
            while (forward != null)
            {
                    forward = forward.getNext();
                    if (forward != null)
                    {
                            backward = backward.getNext();
                            forward = forward.getNext();
                    }
            }

    // splitting the linked list
            Node[] arr = new Node[]{ n, backward.getNext() };
            backward.setNext(null);

            return arr;
    }

    // Sorting linked list using merge sort.
    public Node mergeSort(Node head)
    {
            // Base case
            if (head == null || head.getNext() == null) {
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
    public T get(int index) {
        Node<T> aux = this.head;
        int count = 0;
        while(count < index) {
            aux = aux.getNext();
            count++;
        }
        
        return aux.getData();
    }

    /**
     * Replaces the first node of the list with another
     * @param datum data to be replaced in the first node
     */
    public void setFirst(T datum) {
        if (isEmpty()) {
            addFirst(datum);
        } else {
            Node<T> n = new Node(datum);
            n.setNext(this.head.getNext());
            this.head.setNext(null);
            this.head = n;
        }
    }
    
    /**
     * Replaces the last node of the list with another
     * @param datum data to be replaced in the last node
     */
    public void setLast(T datum) {
        Node<T> n = new Node(datum);
        Node<T> pre = this.head;
        while(pre.getNext().getNext() != null) {
            pre = pre.getNext();
        }
        
        Node<T> temp = pre.getNext();
        pre.setNext(n);
        this.tail = n;
        temp.setNext(null);
    }
    
    /**
     * Replaces the node at the given index with another containing the given data
     * @param i index of the node to be replaced
     * @param datam data that will contain the node to replace with
     */
    public void set(int i, T datum) {
        if (isEmpty() || i == 0) {
            setFirst(datum);
        } else if (i == size() - 1) {
            setLast(datum);
        } else if (i < 0) {
            set(size() + i, datum);
        } else if (i > size() - 1) {
            System.out.println("\nError");
        } else {
            Node<T> n = new Node(datum);
            Node<T> aux = this.head;
            int count = 0;
            while (count < i-1) {
                aux = aux.getNext();
                count++;
            }
            
            Node<T> toReplace = aux.getNext();
            n.setNext(toReplace.getNext());
            aux.setNext(n);
            toReplace.setNext(null);
        }
    }
}
