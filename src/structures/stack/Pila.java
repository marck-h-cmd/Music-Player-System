package structures.stack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.DefaultListModel;
import structures.node.Nodo;
/**
 *
 * @author marck
 */
public class Pila<T> {
     private Nodo<T> L;
    
    public Pila(){
        L=null;
    }

    public Nodo<T> getL() {
        return L;
    }

    public void setL(Nodo<T> L) {
        this.L = L;
    }
    
    
    
    public void push(T valor)
    {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSgte(L);
        L=nuevo;
    }
    
    public T pop(){
        T cima=L.getInfo();
        L=L.getSgte();
        return cima;
    }
    
   public T top(){
        return L.getInfo();
    }

    
    public boolean isEmpty(){
        return L==null;
    }
    
    public void clear(){
        while(L!=null)
            L=L.getSgte();
    }
    
    public int contar() {
        int c = 0;
        Nodo<T> p =L;
        while (p != null) {
            c++;
            p = p.getSgte();
        }
        return c;
    }
    
    public void mostrar(DefaultListModel modelo){
        Nodo actual=L;
        modelo.removeAllElements();
        while(actual!=null)
        {
            modelo.addElement(actual.getInfo());
            actual=actual.getSgte();
        }
    }
}
