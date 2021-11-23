
package Clases;

import Main.HashTable;

/**
 *
 * @author dianabello
 */
public class Application {
    
      public static class Person {
        int age;
        long nationalId;
        String birthDate;
        Person(int age, long nationalId, String birthDate) {
            this.age = age;
            this.nationalId = nationalId;
            this.birthDate = birthDate;
        }
        public String toString() {
            return "Tengo " + this.age + ". Mi cedula es: " + this.nationalId + ". Naci: " + this.birthDate;
        }
    }
    
    public static void main(String[] args) {
     // TODO code application logic here
        HashTable<String, Person> hashTable = new HashTable<>(4);
        
        Person p1 = new Person(19, 23423424, "10/01/1900");
        hashTable.add("Diego", p1);
        Person p2 = new Person(21, 43535345, "10/01/1800");
        hashTable.add("Tyler", p2);
        Person p3 = new Person(20, 4543, "10/01/1700");
        hashTable.add("Mariana", p3);
        Person p4 = new Person(21, 334543, "12/08/1999");
        hashTable.add("Kevin", p4);
        
        System.out.println(hashTable.get("Kevin"));
        hashTable.print();
//        hashTable.add("Diego", 1);
//        hashTable.add("Tyler", 2);
//        hashTable.add("Mariana", 4);
//        hashTable.add("Kevin", 5);
//        hashTable.print();
////        hashTable.add("Alex", 6);
////        hashTable.add("Hannah", 7);
////        hashTable.add("Fernando", 8);
////        hashTable.add("Patricia", 9);
////        hashTable.add("Rafael", 10);
////        hashTable.add("Kenny", 53);
////        hashTable.add("Carlos", 55);
////        hashTable.add("Benito", 500);
////        System.out.println(hashTable.isEmpty());
////        System.out.println(hashTable.size());
//        hashTable.print();
//        System.out.println(hashTable.get("Kevin"));
//        System.out.println(hashTable.get("Fernando"));
//        hashTable.remove("Tyler");
//        hashTable.remove("Diego");
//        System.out.println("El tamaño es: " + hashTable.size());
    }
}
