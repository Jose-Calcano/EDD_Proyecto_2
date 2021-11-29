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
public class HashTable<K,V> {
    
    private Node hash[];
    private int size;     // Tamaño actual de la tabla 
    
    
    /**
     * Crea una hash Table con un tamaño predeterminado
     * @param size
     */
    public HashTable(int size){
        this.size = size; 
        this.hash = new Node[size];
        for (int i = 0; i < size; i++) {
            this.hash[i] = null; 
        }
    }
    /**
     * Obtiene el tamaño actuañ de la tabla
     * @return Tamaño de la tabla
     */
    public int size() { return size; }
    
    /**
     * Obtiene si la HashTable esta vacia o no
     * @return Un boolean que indica si esta o no vacia
     */
    public boolean isEmpty() { return size() == 0; }
    
    
    /**
     * Obtiene un key para los valores agg
     * @param key
     * @return La posición del arreglo
     */
    public int Hashing(String key){
        int value = 0; 
        int position = 1; 
        
        for (int i = 0; i < key.length(); i++) {
            if (key.codePointAt(i) == 32) {
                value += 0;
            }else if (key.codePointAt(i) >= 48 && key.codePointAt(i) <= 57) {
                value += ((key.codePointAt(i) - 47) * position);
            }else if (key.codePointAt(i) >= 65 && key.codePointAt(i) <= 90) {
                value += ((key.codePointAt(i) - 54) * position);
            }else if (key.codePointAt(i) >= 97 && key.codePointAt(i) <= 122) {
                value += ((key.codePointAt(i) - 60) * position);
            }
            position ++;
        }
        return (value % size);
    }
     
    public void saveInList(Node node) {
        LinkedList list = new LinkedList();
        list.addFirst(node);
        list.sortList();
        System.out.println(list.print());
        }
   
    /**
     * Inserta un dato en la hashTable 
     * @param word
     */
     public void insert(String word){
        int position = Hashing(word);
        boolean exist = false; 
        if (this.hash[position] != null) {
           Node temp = this.hash[position];
            if (temp.getWord().equals(word)) {
                exist = true; 
                //cantidad ++;
            }
            while(temp.getNextHash() != null){
                temp = temp.getNextHash();
                if (temp.getWord().equals(word)) {
                exist = true; 
                }
            }
            if (!exist) {
                Node new2 = new Node(word);
                temp.setNextHash(new2);
                saveInList(new2);
            }
        }else{
            Node new3 = new Node(word);
            this.hash[position] = new3;
            saveInList(new3);
        }
    }
    
    
     /**
     * Busca un nodo en la hashTable
     * @return El nodo buscado
     */
    public Node search(String word){
        int position = Hashing(word);
        Node temp = this.hash[position];
        boolean exist = false;
        if (temp != null) {
            if (temp.getNextHash() != null) {
                exist = true; 
            }else{
                while(temp.getNextHash() != null && !exist){
                    if (temp.getData().equals(word)) {
                        exist = true; 
                    }else{
                        temp = temp.getNextHash();
                    }
                }
            }
        }
        if (exist) {
            return temp;
        }else{
            return null;
        }
    }
    
    
    
    /**
     * Muestra los datos de la hash table por consola
     */
 public void print() {            
        int count = 1;
        for (int j = 0; j < size; j++) { 
            if (this.hash[j] != null) {
                System.out.print("\n Key: " + Hashing(this.hash[j].getWord()) + ". Value: " + this.hash[j].getWord());
                if (this.hash[j].getNextHash() != null) {
                    Node temp = this.hash[j].getNextHash();
                    while (temp != null) {
                        System.out.print(" --> Key: " + Hashing(temp.getWord()) + ". Value: " + temp.getWord());
                        temp = temp.getNextHash();
                        count++;
                    }
                }
                count++;
            }
        }

    } 
   
    
}
