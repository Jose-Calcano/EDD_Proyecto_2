
package Clases;

/**
 *
 * @author dianabello
 */
public class Application {
    
     
    public static void main(String[] args) {
     // TODO code application logic here
        HashTable hash = new HashTable(10);
        
        hash.insert("juan");
        hash.insert("Diana");
        hash.insert("carlos");
        
        hash.print();
        System.out.println("");
        
        System.out.println(hash.Hashing("juan"));
        System.out.println(hash.Hashing("Diana"));
        System.out.println(hash.Hashing("carlos"));
    }
}
