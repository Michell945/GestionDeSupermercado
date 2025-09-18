/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizestructura;

/**
 *
 * @author Michel Yepez
 */
public class NodoCarrito {
     public CarroDeCompras dato;
    public NodoCarrito siguiente;

    public NodoCarrito(CarroDeCompras dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
