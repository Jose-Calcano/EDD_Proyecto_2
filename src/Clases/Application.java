package Clases;

/**
 *
 * @author dianabello
 */
public class Application {

    public static void main(String[] args) {
        // TODO code application logic here 
        HashTable hash = new HashTable(4);
        LinkedList l1 = new LinkedList();

        Node n1 = new Node("juan");
        hash.insert(n1);
        l1.addLast(n1);
        Node n2 = new Node("Diana");
        hash.insert(n2);
        l1.addLast(n2);
        Node n3 = new Node("juan");
        hash.insert(n3);
        l1.addLast(n3);
        Node n4 = new Node("jose");
        hash.insert(n4);
        l1.addLast(n4);
        Node n5 = new Node("juan");
        hash.insert(n5);
        l1.addLast(n5);
        Node n6 = new Node("Diana");
        hash.insert(n6);
        l1.addLast(n6);
        
        l1.sortList();
        System.out.println(l1.print());
        System.out.println("");

    }
}
