/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.tree;

import javax.swing.DefaultListModel;

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
            r.setHi(inserta(x, r.getHi()));
        } else if (x.compareTo(r.getInfo()) > 0) {
            r.setHd(inserta(x, r.getHd()));
        }

        return r;
    }

    public NodoArbol buscar(T x) {
        return buscar(x, raiz);
    }

    public NodoArbol buscar(T x, NodoArbol<T> r) {
        if (r != null) {
            if (x == r.getInfo()) {
                return r;
            } else if (x.compareTo(r.getInfo()) < 0) {
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

    private void preOrden(NodoArbol<T> r, DefaultListModel modelo) {
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

    private void enOrden(NodoArbol<T> r, DefaultListModel modelo) {
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

    private void postOrden(NodoArbol<T> r, DefaultListModel modelo) {
        if (r != null) {
            postOrden(r.getHi(), modelo);
            postOrden(r.getHd(), modelo);
            modelo.addElement(r.getInfo());
        }
    }

    public T buscarMax() {
        return buscarMax(raiz);
    }

    private T buscarMax(NodoArbol<T> r) {
        T x;
        if (r.getHd() == null) {
            x = r.getInfo();
        } else {
            x = (T) buscarMax(r.getHd());
        }
        return x;
    }

    public T buscarMin() {
        return buscarMin(raiz);
    }

    private T buscarMin(NodoArbol<T> r) {
        T x;
        if (r.getHi() == null) {
            x = r.getInfo();
        } else {
            x = (T) buscarMin(r.getHi());
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

    public void elimina(T x) {
        raiz = elimina(x, raiz);
    }

    private NodoArbol<T> elimina(T x, NodoArbol<T> r) {
        if (r != null) {
            if (x.compareTo(r.getInfo()) < 0) {
                r.setHi(elimina(x, r.getHi()));
            } else if (x.compareTo(r.getInfo()) > 0) {
                r.setHd(elimina(x, r.getHd()));
            } else if (r.getHi() == null) {
                r = r.getHd();
            } else if (r.getHd() == null) {
                r = r.getHi();
            } else {
                r.setInfo((T) buscarMin(r.getHd()));
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
}
