/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.linkedlist;

import javax.swing.DefaultListModel;
import structures.node.NodoDoble;

/**
 *
 * @author marck
 */
public class ListaCircularDoble<T> {

    private NodoDoble<T> L;

    public ListaCircularDoble() {
        L = null;
    }

    public NodoDoble<T> getL() {
        return L;
    }

    public void setL(NodoDoble<T> u) {
        this.L = u;
    }

    public boolean isEmpty() {
        return L == null;
    }


    public void insertar(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (L == null) {
            L = nuevo;
            nuevo.setSgte(nuevo);
            nuevo.setAnt(nuevo);
        } else {
            nuevo.setAnt(L);
            nuevo.setSgte(L.getSgte());
            L.getSgte().setAnt(nuevo);
            L.setSgte(nuevo);
            L = nuevo;
        }
    }

    public NodoDoble<T> buscar(T valor) {
        NodoDoble<T> p = L.getSgte();

        if (isEmpty()) {
            return null;
        } else {
            do {
                if (p.getInfo() == valor) {
                    return p;
                }
                p = p.getSgte();
            } while (p != L.getSgte());
        }
        return null;
    }

    public int contar() {
        NodoDoble<T> p = L.getSgte();
        int c = 0;
        do {
            c++;
            p = p.getSgte();
        } while (p != L.getSgte());

        return c;
    }

  
    
    
   
}
