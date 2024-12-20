/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.tree;

/**
 *
 * @author CRISTHIAN
 */
public class NodoArbol<T> {

    private T info;
    private NodoArbol<T> hi;
    private NodoArbol<T> hd;

    public NodoArbol() {

    }

    public NodoArbol(T x) {
        info = x;
        hi = null;
        hd = null;
    }

    public NodoArbol(T info, NodoArbol hi, NodoArbol hd) {
        this.info = info;
        this.hi = hi;
        this.hd = hd;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodoArbol getHi() {
        return hi;
    }

    public void setHi(NodoArbol hi) {
        this.hi = hi;
    }

    public NodoArbol getHd() {
        return hd;
    }

    public void setHd(NodoArbol hd) {
        this.hd = hd;
    }

}
