/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizestructura;

/**
 *
 * @author Michel Yepez
 */
public class CarroDeCompras {
    public Producto producto;
    public int cantidad;

    public CarroDeCompras(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double subtotal() {
        return producto.precio * cantidad;
    }
}
