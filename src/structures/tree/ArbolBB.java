/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.tree;

import javax.swing.DefaultListModel;

/**
 *
 * @author CRISTHIAN
 */
public class ArbolBB {

    private NodoArbol raiz;

    public ArbolBB() {
        raiz = null;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void inserta(int x) {
        raiz = inserta(x, raiz);
    }

    private NodoArbol inserta(int x, NodoArbol r) {
        if (r == null) {
            r = new NodoArbol(x);
        } else if (x < r.getInfo()) {
            r.setHi(inserta(x, r.getHi()));
        } else if (x > r.getInfo()) {
            r.setHd(inserta(x, r.getHd()));
        }

        return r;
    }

    public NodoArbol buscar(int x) {
        return buscar(x, raiz);
    }

    public NodoArbol buscar(int x, NodoArbol r) {
        if (r != null) {
            if (x == r.getInfo()) {
                return r;
            } else if (x < r.getInfo()) {
                return buscar(x, r.getHi());
            } else {
                return buscar(x, r.getHd());
            }
        } else {
            return null;
        }
    }
    
    public void preOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        preOrden(raiz, modelo);
    }

    private void preOrden(NodoArbol r, DefaultListModel modelo) {
        if (r != null) {
            modelo.addElement(r.getInfo());
            preOrden(r.getHi(), modelo);
            preOrden(r.getHd(), modelo);
        }
    }

    
    public void enOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        enOrden(raiz, modelo);
    }

    private void enOrden(NodoArbol r, DefaultListModel modelo) {
        if (r != null) {
            enOrden(r.getHi(), modelo);
            modelo.addElement(r.getInfo());
            enOrden(r.getHd(), modelo);
        }
    }

    public void postOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        postOrden(raiz, modelo);
    }

    private void postOrden(NodoArbol r, DefaultListModel modelo) {
        if (r != null) {
            postOrden(r.getHi(), modelo);
            postOrden(r.getHd(), modelo);
            modelo.addElement(r.getInfo());
        }
    }


    public int buscarMax() {
        return buscarMax(raiz);
    }

    private int buscarMax(NodoArbol r) {
        int x;
        if (r.getHd() == null) {
            x = r.getInfo();
        } else {
            x = buscarMax(r.getHd());
        }
        return x;
    }

    public int buscarMin() {
        return buscarMin(raiz);
    }

    private int buscarMin(NodoArbol r) {
        int x;
        if (r.getHi() == null) {
            x = r.getInfo();
        } else {
            x = buscarMin(r.getHi());
        }
        return x;
    }

    public int contar() {
        return contar(raiz);
    }

    private int contar(NodoArbol r) {
        if (r == null) {
            return 0;
        } else {
            return 1 + contar(r.getHi()) + contar(r.getHd());
        }
    }

    public int numeroHojas() {
        return numeroHojas(raiz);
    }

    private int numeroHojas(NodoArbol r) {
        if (r == null) {
            return 0;
        }
        if (r.getHi() == null && r.getHd() == null) {
            return 1;
        } else {
            return numeroHojas(r.getHi()) + numeroHojas(r.getHd());
        }
    }

    public void eliminarMin() {
        raiz = eliminarMin(raiz);
    }

    private NodoArbol eliminarMin(NodoArbol r) {
        if (r.getHi() == null) {
            r = r.getHd();
        } else {
            r.setHi(eliminarMin(r.getHi()));
        }
        return r;

    }

    public void elimina(int x) {
        raiz = elimina(x, raiz);
    }

    private NodoArbol elimina(int x, NodoArbol r) {
        if (r != null) {
            if (x < r.getInfo()) 
                r.setHi(elimina(x, r.getHi()));
             else 
                if (x > r.getInfo()) 
                  r.setHd(elimina(x, r.getHd()));
             else 
                if (r.getHi() == null) 
                  r = r.getHd();
             else 
                 if (r.getHd() == null) 
                 r = r.getHi();
             else 
                 {
                 r.setInfo(buscarMin(r.getHd()));
                 r.setHd(eliminarMin(r.getHd()));
                 
                 }
        }

        return r;
    }

    public int alturaArbol() {
        return alturaArbol(raiz);
    }

    private int alturaArbol(NodoArbol r) {
        int ahi, ahd;
        if (r == null) {
            return -1;
        } else {
            ahi = 1 + alturaArbol(r.getHi());
            ahd = 1 + alturaArbol(r.getHd());
            if (ahi > ahd) {
                return ahi;
            } else {
                return ahd;
            }

        }

    }

    public int alturaNodo(int x) {
        NodoArbol nodo = buscar(x);
        if (nodo == null) {
            return -1; 
        }
        return alturaNodo(nodo);
    }

    private int alturaNodo(NodoArbol r) {
        if (r == null) {
            return -1;
        } else {
            int alturaIzquierda = alturaNodo(r.getHi());
            int alturaDerecha = alturaNodo(r.getHd());
            return 1 + Math.max(alturaIzquierda, alturaDerecha);
        }
    }

    public int profundidadTotal() {
        return profundidadTotal(raiz, 0);
    }

    private int profundidadTotal(NodoArbol r, int profundidadActual) {
        if (r == null) {
            return 0;
        } else {
            return profundidadActual
                    + profundidadTotal(r.getHi(), profundidadActual + 1)
                    + profundidadTotal(r.getHd(), profundidadActual + 1);
        }
    }

    public int profundidadNodo(int x) {
        return profundidadNodo(x, raiz, 0);
    }

    private int profundidadNodo(int x, NodoArbol r, int profundidadActual) {
        if (r == null) {
            return -1;
        }
        if (x == r.getInfo()) {
            return profundidadActual;
        } else if (x < r.getInfo()) {
            return profundidadNodo(x, r.getHi(), profundidadActual + 1);
        } else {
            return profundidadNodo(x, r.getHd(), profundidadActual + 1);
        }
    }

    public int sumaElementos() {
        return sumaElementos(raiz);
    }

    private int sumaElementos(NodoArbol r) {
        if (r == null) {
            return 0;
        } else {
            return r.getInfo() + sumaElementos(r.getHi()) + sumaElementos(r.getHd());
        }
    }

    public String resultados() {
        return "El mayor es: " + (esVacio() ? "N/A" : buscarMax()) + "\n"
                + "El menor es: " + (esVacio() ? "N/A" : buscarMin()) + "\n"
                + "Número de nodos: " + contar() + "\n"
                + "Altura del árbol: " + alturaArbol() + "\n"
                + "Suma de los elementos: " + sumaElementos() + "\n"
                + "Profundidad total del árbol: " + profundidadTotal() + "\n"
                + "Número de hojas: " + numeroHojas();
    }
}