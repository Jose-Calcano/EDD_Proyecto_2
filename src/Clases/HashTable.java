/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.HashNode;
import Clases.Node;
import Clases.LinkedList;

/**
 *
 * @author dianabello
 */
public class HashTable<K,V> {
    // bucketArray is used to store array of chains
    private LinkedList<HashNode<K, V>> bucketArray;
    // Current capacity of Hash Table
    private int capacity;
    // Current size of Hash Table
    private int size;
    
    /**
     *  Creates a new HashTable instance with a limit number of buckets of 10
     */
    public HashTable() {
        this.bucketArray = new LinkedList<>();
        this.capacity = 10;
        this.size = 0;
        // Create empty buckets
        for (int i = 0; i < capacity; i++)
            bucketArray.addLast(null);
    }
    
    /**
     * Creates a new HashTable instance with the specified limit number of buckets 
     * @param capacity maximum number of Buckets for the HashTable
     */
    public HashTable(int capacity) {
        this.bucketArray = new LinkedList<>();
        this.capacity = capacity;
        this.size = 0;
        // Create empty buckets
        for (int i = 0; i < capacity; i++)
            bucketArray.addLast(null);
    }
    
    /**
     * Gets the current size of the HashTable
     * @return Size of the HashTable
     */
    public int size() { return size; }
    
    /**
     * Get whether HashTable is empty or not
     * @return Boolean indicating whether HashTable is empty or not
     */
    public boolean isEmpty() { return size() == 0; }
    
    /**
     * Generates unique HashCode for the specified key
     * @param key necessary to generate HashCode
     * @return Unique HashCode
     */
    private final int hashCode(K key) {
        // Convert element to String
        String keyStringified = key.toString();
        // Returnable hash
        int hash = 0;
        for (int i = 0; i < keyStringified.length(); i++)
            // Generate unique value by converting each character in its unicode
            // representation and multiplying by position on string
            hash = (hash + Character.codePointAt(keyStringified, i) * i);
        
        return hash;
    }
    
    /**
     * Gets the bucketIndex that corresponds to the given key
     * @param key to which index will be calculated
     * @return Index in the bucketArray
     */
    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        // hashCode % capacity allows to get a key in the range of the capacity
        int index = hashCode % this.capacity;
        return index;
    }
    
    /**
     * Adds a new key-value pair to the HashTable
     * @param key key of the element
     * @param value value of the element
     */
    public void add(K key, V value) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K,V> head = bucketArray.get(bucketIndex);
        
        // Check if key is already present
        while (head != null) {
            if (head.getKey().equals(key) && head.getHashCode() == hashCode) {
                head.setValue(value);
                return;
            }
            head = head.getNext();
        }
        
        // Insert key in chain
        
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<K,V>(key, value, hashCode);
        newNode.setNext(head);
        bucketArray.set(bucketIndex, newNode);
        
        
        // If load factor goes beyond threshold, then
        // double hash table size
        
        if ((1.0 * size)/capacity >= 0.7) {
            LinkedList<HashNode<K,V>> temp = bucketArray;
            bucketArray = new LinkedList<>();
            capacity *=2;
            size = 0;
            for (int i = 0; i< capacity; i++)
                bucketArray.addLast(null);
            
            Node bucketHead = temp.getHead();
            while(bucketHead != null) {
                HashNode<K,V> headNode = (HashNode<K,V>)bucketHead.getData();
                while(headNode != null) {
                    add(headNode.getKey(), headNode.getValue());
                    headNode = headNode.getNext();
                }
                bucketHead = bucketHead.getNext();
            }
        }
        
    }
    
    /**
     * Returns value for the specified key
     * @param key to get value to
     * @return value for the specified key
     */
    public V get(K key) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        
        HashNode<K,V> head = bucketArray.get(bucketIndex);
        
        // Search key in chain
        while(head != null) {
            if (head.getKey().equals(key) && head.getHashCode() == hashCode) {
                return head.getValue();
            }
            head = head.getNext();
        }
        
        // If key not found
        return null;
    }
    
    /**
     * Removes the given key from the HashTable
     * @param key key to be deleted
     * @return the value of the deleted key
     */
    public V remove(K key) {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of chain
        HashNode<K,V> head = bucketArray.get(bucketIndex);
        
        // Search for key in its chain
        HashNode<K,V> prev = null;
        while (head != null) {
            // If Key found
            if (head.getKey().equals(key)  && head.getHashCode() == hashCode)
                break;
            
            // Else keep moving in chain
            prev = head;
            head = head.getNext();
        }
        
        // If key was not there
        if (head == null)
            return null;
        
        // Reduce size
        size--;
        
        // Remove key
        if (prev != null)
            prev.setNext(head.getNext());
        else
            bucketArray.set(bucketIndex, head.getNext());
        
        return head.getValue();
    }
    
    /**
     * Prints the current state of the Hash Table
     */
    public void print() {
        // Reference to the first bucket
        Node bucketHead = bucketArray.getHead();
        int index = 0;
        // For every bucket
        while (bucketHead != null) {
            // Get reference to the chain in current bucket
            HashNode<K,V> headNode = (HashNode<K,V>)bucketHead.getData();
            // Print current bucket
            System.out.print("i" + index + ":");
            int j = 0;
            // For every Node in chain
            while (headNode != null) {
//                System.out.print(headNode.getValue() + "->");
                // Print current element in chain
                System.out.print((j > 0 ? "->" : "") + headNode.getValue());
                j++;
                // Move to next element
                headNode = headNode.getNext();
            }
            // Increase bucket index
            index++;
            j = 0;
            System.out.println("");
            // Move to next bucket
            bucketHead = bucketHead.getNext();
        }
    }
}
