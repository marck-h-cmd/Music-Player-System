/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.tree;

import javax.swing.DefaultListModel;
import structures.node.NodoArbol;
/**
 *
 * @author CRISTHIAN
 * @param <T>
 */
public class ArbolBB<T extends Comparable<T>> {

    private NodoArbol<T> raiz;

    public ArbolBB() {
        raiz = null;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void inserta(T x) {
        raiz = inserta(x, raiz);
    }

    private NodoArbol inserta(T x, NodoArbol<T> r) {
        if (r == null) {
            r = new NodoArbol(x);
        } else if (x.compareTo(r.getInfo()) < 0) {
            r.setIzquierdo(inserta(x,r.getIzquierdo()));
        } else if (x.compareTo(r.getInfo()) > 0) {
            r.setDerecho(inserta(x,r.getDerecho()));
        }

        return r;
    }

    public NodoArbol buscar(T x) {
        return buscar(x, raiz);
    }

    private NodoArbol buscar(T x, NodoArbol<T> r) {
        if (r != null) {
            if (x == r.getInfo()) {
                return r;
            } else if (x.compareTo(r.getInfo()) < 0) {
                return buscar(x,r.getIzquierdo());
            } else {
                return buscar(x,r.getDerecho());
            }
        } else {
            return null;
        }
    }

    public void preOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        preOrden(raiz, modelo);
    }

    private void preOrden(NodoArbol<T> r, DefaultListModel modelo) {
        if (r != null) {
            modelo.addElement(r.getInfo());
            preOrden(r.getIzquierdo(),modelo);
            preOrden(r.getDerecho(),modelo);
        }
    }

    public void enOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        enOrden(raiz, modelo);
    }

    private void enOrden(NodoArbol<T> r, DefaultListModel modelo) {
        if (r != null) {
            enOrden(r.getIzquierdo(),modelo);
            modelo.addElement(r.getInfo());
            enOrden(r.getDerecho(),modelo);
        }
    }

    public void postOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        postOrden(raiz, modelo);
    }

    private void postOrden(NodoArbol<T> r, DefaultListModel modelo) {
        if (r != null) {
            postOrden(r.getIzquierdo(),modelo);
            postOrden(r.getDerecho(),modelo);
            modelo.addElement(r.getInfo());
        }
    }

    public T buscarMax() {
        return buscarMax(raiz);
    }

    private T buscarMax(NodoArbol<T> r) {
        T x;
        if (r.getDerecho() == null) {
            x = r.getInfo();
        } else {
            x = (T) buscarMax(r.getDerecho());
        }
        return x;
    }

    public T buscarMin() {
        return buscarMin(raiz);
    }

    private T buscarMin(NodoArbol<T> r) {
        T x;
        if (r.getIzquierdo() == null) {
            x = r.getInfo();
        } else {
            x = (T) buscarMin(r.getIzquierdo());
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
            return 1 + contar(r.getIzquierdo()) + contar(r.getDerecho());
        }
    }

    public int numeroHojas() {
        return numeroHojas(raiz);
    }

    private int numeroHojas(NodoArbol r) {
        if (r == null) {
            return 0;
        }
        if (r.getIzquierdo() == null && r.getDerecho() == null) {
            return 1;
        } else {
            return numeroHojas(r.getIzquierdo()) + numeroHojas(r.getDerecho());
        }
    }

    public void eliminarMin() {
        raiz = eliminarMin(raiz);
    }

    private NodoArbol eliminarMin(NodoArbol r) {
        if (r.getIzquierdo() == null) {
            r = r.getDerecho();
        } else {
            r.setIzquierdo(eliminarMin(r.getIzquierdo()));
        }
        return r;

    }

    public void elimina(T x) {
        raiz = elimina(x, raiz);
    }

    private NodoArbol<T> elimina(T x, NodoArbol<T> r) {
        if (r != null) {
            if (x.compareTo(r.getInfo()) < 0) {
                r.setIzquierdo(elimina(x,r.getIzquierdo()));
            } else if (x.compareTo(r.getInfo()) > 0) {
                r.setDerecho(elimina(x, r.getDerecho()));
            } else if (r.getIzquierdo() == null) {
                r = r.getDerecho();
            } else if (r.getDerecho() == null) {
                r = r.getIzquierdo();
            } else {
                r.setInfo((T) buscarMin(r.getDerecho()));
                r.setDerecho(eliminarMin(r.getDerecho()));

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
            ahi = 1 + alturaArbol(r.getIzquierdo());
            ahd = 1 + alturaArbol(r.getDerecho());
            if (ahi > ahd) {
                return ahi;
            } else {
                return ahd;
            }

        }

    }

    public int alturaNodo(T x) {
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
            int alturaIzquierda = alturaNodo(r.getIzquierdo());
            int alturaDerecha = alturaNodo(r.getDerecho());
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
                    + profundidadTotal(r.getIzquierdo(), profundidadActual + 1)
                    + profundidadTotal(r.getDerecho(), profundidadActual + 1);
        }
    }
}
