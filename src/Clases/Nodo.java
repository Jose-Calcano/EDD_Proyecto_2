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
public class Nodo {
    String palabra;
    Nodo next;

    Nodo(String palabra ) {
        this.palabra = palabra;
        this.next = null;
    }
    
 
    public String valorString(){
        return palabra; 
    }
 

    /**
    public Nodo getSiguiente() {
        return next;
    } */
}

