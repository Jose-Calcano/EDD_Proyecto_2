
package Clases;

/**
 *
 * @author Diana Bello
 * @param <K>
 * @param <V>
 */
public class HashNode<K, V> {
    private K key;
    private V value;
    private final int hashCode;
    private HashNode<K,V> next;
    
    /**
     *  Creates a new HashNode instance
     * @param key
     * @param value
     * @param hashCode
     */
    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
        this.next = null;
    }

    /**
     *  Get HashNode key
     * @return
     */
    public K getKey() {
        return key;
    }

    /**
     *  Set new HashNode Key
     * @param key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     *  Get HashNode value
     * @return
     */
    public V getValue() {
        return value;
    }

    /**
     *  Set HashNode value
     * @param value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Get next HashNode
     * @return
     */
    public HashNode<K, V> getNext() {
        return next;
    }

    /**
     * Sets a new next HashNode
     * @param next
     */
    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    /**
     * Returns the unique HashCode assigned to this HashNode
     * @return
     */
    public int getHashCode() {
        return hashCode;
    }
}
