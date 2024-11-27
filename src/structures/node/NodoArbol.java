/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.node;

/**
 *
 * @author marck
 */
public class NodoArbol<T> {
    
    private T info;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;
    
     public NodoArbol() {
        izquierdo = null;
        derecho = null;
    }

    public NodoArbol(T info) {
        this.info = info;
        izquierdo = null;
        derecho = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodoArbol<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol<T> derecho) {
        this.derecho = derecho;
    }
     
     

     
}
