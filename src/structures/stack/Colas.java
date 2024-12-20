package structures.stack;
import structures.node.Nodo;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.DefaultListModel;

/**
 *
 * @author marck
 */
public class Colas<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;

    public Colas() {
        primero = null;
        ultimo = null;
    }

    public boolean isEmpty() {
        return primero == null && ultimo == null;
    }

    public void encolar(T valor) {
        Nodo<T> nuevo = new Nodo(valor);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSgte(nuevo);
            ultimo = nuevo;
        }
    }
    
    public void encolarAlInicio(T valor) {
        Nodo<T> nuevo = new Nodo(valor);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setSgte(primero);
            primero = nuevo;
        }
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }

    
    public T frente() {
        if (!isEmpty()) {
            return primero.getInfo();
        }
        return null;
    }

    public T desencolar() {

        if (!isEmpty()) {
            T valor = primero.getInfo();

            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                primero = primero.getSgte();
            }
            return valor;
        }
        return null;
    }

    public T eliminar(T valor) {
    if (isEmpty()) 
        return null; 
    
    if (primero.getInfo().equals(valor)) 
        return desencolar(); 
    
    Nodo<T> actual = primero;
    while (actual.getSgte() != null && !actual.getSgte().getInfo().equals(valor)) 
        actual = actual.getSgte();
    

    if (actual.getSgte() == null) 
        return null; 
   
    Nodo<T> nodoAEliminar = actual.getSgte();
    actual.setSgte(nodoAEliminar.getSgte());

     if (nodoAEliminar == ultimo) 
        ultimo = actual;
    
    return nodoAEliminar.getInfo();
}

    public void clear() {
        while (primero != null) {
            primero = primero.getSgte();
        }
        ultimo = null;
    }

    public void mostrar(DefaultListModel modelo) {

        modelo.removeAllElements();
        Nodo<T> p = primero;
        while (p != null) {
            modelo.addElement(p.getInfo());
            p = p.getSgte();
        }
    }
    
    public int contar() {
        int c = 0;
        Nodo<T> p = primero;
        while (p != null) {
            c++;
            p = p.getSgte();
        }
        return c;
    }
}
