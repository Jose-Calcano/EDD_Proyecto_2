
package Clases;

/**
 *
 * @author dianabello
 */
public class Application {
    
     
    public static void main(String[] args) {
     // TODO code application logic here
        HashTable hash = new HashTable(4);
        
        hash.insert("juan");
        hash.insert("Diana");
        hash.insert("carlos");
        hash.insert("miranda");
        hash.insert("jose");
        
        hash.print();
        System.out.print("");
//        
//        System.out.println(hash.Hashing("juan"));
//        System.out.println(hash.Hashing("Diana"));
//        System.out.println(hash.Hashing("carlos"));
   }
}
