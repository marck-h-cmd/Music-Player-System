/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.node;

/**
 *
 * @author marck
 */
public class NodoDoble<T> {
     private T info;
    private NodoDoble ant;
    private NodoDoble sgte;

    public NodoDoble(T x) {
        info = x;
    }

    public NodoDoble(T x, NodoDoble ant, NodoDoble sgte) {
        info = x;
        this.ant = ant;
        this.sgte = sgte;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodoDoble getAnt() {
        return ant;
    }

    public void setAnt(NodoDoble ant) {
        this.ant = ant;
    }

    public NodoDoble getSgte() {
        return sgte;
    }

    public void setSgte(NodoDoble sgte) {
        this.sgte = sgte;
    }

    
    
}
