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
public class HashTable<K, V> {

    private LinkedList hash[];
    private int size;     // Tamaño actual de la tabla 

    /**
     * Crea una hash Table con un tamaño predeterminado
     *
     * @param size
     */
    public HashTable(int size) {
        this.size = size;
        this.hash = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.hash[i] = null;
        }
    }

    /**
     * Obtiene el tamaño actuañ de la tabla
     *
     * @return Tamaño de la tabla
     */
    public int size() {
        return size;
    }

    /**
     * Obtiene si la HashTable esta vacia o no
     *
     * @return Un boolean que indica si esta o no vacia
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Obtiene un key para los valores agg
     *
     * @param key
     * @return La posición del arreglo
     */
    public int Hashing(String key) {
        int value = 0;
        int position = 1;

        for (int i = 0; i < key.length(); i++) {
            if (key.codePointAt(i) == 32) {
                value += 0;
            } else if (key.codePointAt(i) >= 48 && key.codePointAt(i) <= 57) {
                value += ((key.codePointAt(i) - 47) * position);
            } else if (key.codePointAt(i) >= 65 && key.codePointAt(i) <= 90) {
                value += ((key.codePointAt(i) - 54) * position);
            } else if (key.codePointAt(i) >= 97 && key.codePointAt(i) <= 122) {
                value += ((key.codePointAt(i) - 60) * position);
            }
            position++;
        }
        return (value % size);
    }

    public int repeatedWord(String word) {
        Node a = search(word);
        if (a == null) {
            System.out.println("\n No se encuentra en la tabla");
            return 0;
        } else {
            return a.getRepeat();
        }
    }

    /**
     * Inserta un dato en la hashTable
     *
     * @param nodo
     */
    public void insert(Node nodo) {
        int position = Hashing(nodo.getWord());
        if (search(nodo.getWord()) != null) {
            Node temp = search(nodo.getWord());
            temp.repeat += 1;
        } else {
            if (this.hash[position] == null) {
                this.hash[position] = new LinkedList();
            }
            this.hash[position].addLastHash(nodo);
        }
    }

    /**
     * Busca un nodo en la hashTable
     *
     * @param word
     * @return El nodo buscado
     */
    public Node search(String word) {
        int position = Hashing(word);
        LinkedList lista = this.hash[position];
        Node result = null;
        if (lista != null) {
            if (!lista.isEmpty()) {
                result = lista.getHead();
                while (result != null) {
                    if (result.getWord().equals(word)) {
                        break;
                    } else {
                        result = result.getNextHash();
                    }
                }
            }
        }
        return result;
    }

    /**
     * Muestra los datos de la hash table por consola
     */
//    public void print() {
//        int count = 1;
//        for (int j = 0; j < size; j++) {
//            if (this.hash[j] != null) {
//                System.out.print("\n Key: " + Hashing(this.hash[j].getWord()) + ". Value: " + this.hash[j].getWord());
//                if (this.hash[j].getNextHash() != null) {
//                    Node temp = this.hash[j].getNextHash();
//                    while (temp != null) {
//                        System.out.print(" --> Key: " + Hashing(temp.getWord()) + ". Value: " + temp.getWord());
//                        temp = temp.getNextHash();
//                        count++;
//                    }
//                }
//                count++;
//            }
//        }
//
//    }
}
