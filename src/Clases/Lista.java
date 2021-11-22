package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jose_
 */
public class Lista {
    public Nodo first;
    public Nodo last;
    public int size;

    public Lista(){
        this.first = null ;
        this.last = null;
        this.size = 0 ;
    }
    
    public boolean isEmpty(){
        return first == null ;
    }
    
    public void empty(){
        this.first = null ;
        this.last = null ; 
        this.size = 0 ;
    }
    
    public void addAtTheEnd(Nodo newNodo){
        if(this.isEmpty()){
            first = last = newNodo;
        }else{
            last.next = newNodo;   
            last = newNodo;
        }
        size++ ; 
    }
    
    public void DeleteAtTheStart(){
        if(!this.isEmpty()){
            if(size ==1){
                this.empty();
            }else{
               first = first.next;
               size--;
            }
        }
    }
    public void print(){
        Nodo temp = first;
        if(this.isEmpty()){
            System.out.println("The list is Empty. ");
        }
        while(temp != null){
            System.out.println(temp.valorString());
            temp = temp.next;
            
        }
    }
 }


