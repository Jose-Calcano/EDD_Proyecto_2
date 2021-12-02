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
     *
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
     *
     * @return
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Get the current size of the list
     *
     * @return
     */
    public int size() {
        int i = 0;

        if (isEmpty()) {
            return 0;
        }

        Node aux = this.head;
        while (aux != null) {
            aux = aux.getNextList();
            i++;
        }
        return i;
    }

    /**
     * Add datum to the last position of the list
     *
     * @param nodo
     */
    public void addLast(Node nodo) {
        if (isEmpty()) {
            this.head = nodo;
            this.tail = nodo;
        } else {
            Node temp = this.getHead();
            while (temp != null) {
                if (temp.getWord().equals(nodo.getWord())) {
                    break;
                } else {
                    temp = temp.getNextList();
                }
            }
            if (temp == null) {
                this.tail.setNextList(nodo);
                this.tail = nodo;
            }
        }
    }
    /**
     * Description: Añade al final de una de las listas de la HashTable
     * @param nodo nodo a añadir
     */
    public void addLastHash(Node nodo) {
        if (isEmpty()) {
            this.head = nodo;
            this.tail = nodo;
        } else {
            this.tail.setNextHash(nodo);
            this.tail = nodo;
        }
    }
    /**
     * Description: Elimina un nodo de la lista
     * @param element nodo a eliminar
     */
    public void eliminar(Node element) {
        if (!this.isEmpty()) {
            Node temp = this.head;
            if (temp.getWord().equals(element.getWord())) {
                this.head = temp.getNextList();
                if (temp.getNextList() == null) {
                    this.tail = null;
                }
            } else {
                while (temp.getNextList() != null) {
                    if (temp.getNextList().getWord().equals(element.getWord())) {
                        if (this.tail.getWord().equals(element.getWord())) {
                            this.tail = temp;
                        }
                        temp.setNextList(element.getNextList());
                        break;
                    } else {
                        temp = temp.getNextList();
                    }
                }
            }
        }
    }
    /**
     * Description: Obtiene uno de los nodos con el menor número de repeticiones
     * @return Nodo buscado
     */
    public Node getMin() {
        if (!isEmpty()) {
            Node result = this.head;
            Node aux = result.getNextList();
            if (aux != null) {
                while (aux != null) {
                    if (aux.getRepeat() < result.getRepeat()) {
                        result = aux;
                    }
                    aux = aux.getNextList();
                }
                return result;
            } else {
                return result;
            }
        } else {
            return null;
        }
    }
    /**
     * Description: Ordena la lista de mayor a menor respecto al número de repeticiones
     */
    public void sortList() {
        if (!isEmpty()) {
            Node nodo = this.getMin();
            eliminar(nodo);
            this.sortList();
            nodo.setNextList(null);
            this.addLast(nodo);
        }
    }

}
