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
    String operador;
    Nodo next;

    Nodo(String operador ) {
        this.operador = operador;
        this.next = null;
    }
    
 
    public String valorString(){
        return operador; 
    }
 

    /**
    public Nodo getSiguiente() {
        return next;
    } */
}

