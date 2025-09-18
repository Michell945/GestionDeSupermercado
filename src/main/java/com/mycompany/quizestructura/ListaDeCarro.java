/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizestructura;

/**
 *
 * @author Michel Yepez
 */
public class ListaDeCarro {
    private NodoCarrito cabeza;

    public ListaDeCarro() {
        cabeza = null;
    }

    // Agregar producto al carrito
    public void agregarAlCarrito(Producto producto, int cantidad) {
        if (producto.stock < cantidad) {
            System.out.println("No hay suficiente stock disponible. Stock actual: " + producto.stock);
            return;
        }

        // Si el producto ya está en el carrito, solo actualizar cantidad
        NodoCarrito actual = cabeza;
        while (actual != null) {
            if (actual.dato.producto.id == producto.id) {
                if (producto.stock < cantidad) {
                    System.out.println("No hay suficiente stock para aumentar esa cantidad.");
                    return;
                }
                actual.dato.cantidad += cantidad;
                producto.stock -= cantidad; // actualizar inventario
                System.out.println("Se actualizo la cantidad en el carrito.");
                return;
            }
            actual = actual.siguiente;
        }

        // Si no está en el carrito, agregar uno nuevo
        producto.stock -= cantidad; // actualizar inventario
        CarroDeCompras nuevo = new CarroDeCompras(producto, cantidad);
        NodoCarrito nuevoNodo = new NodoCarrito(nuevo);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }

        System.out.println("Producto agregado al carrito.");
    }

    // Listar carrito
    public void listarCarrito() {
        if (cabeza == null) {
            System.out.println("El carrito esta vacio.");
            return;
        }
        NodoCarrito actual = cabeza;
        while (actual != null) {
            CarroDeCompras carroDeCompras = actual.dato;
            Producto p = carroDeCompras.producto;
            System.out.println("ID: " + p.id + " | Nombre: " + p.nombre
                    + " | Descripcion: " + p.descripcion
                    + " | Precio: $" + p.precio
                    + " | Cantidad: " + carroDeCompras.cantidad
                    + " | Subtotal: $" + carroDeCompras.subtotal());
            actual = actual.siguiente;
        }
    }

    // Calcular total
    public double calcularTotal() {
        double total = 0.0;
        NodoCarrito actual = cabeza;
        while (actual != null) {
            total += actual.dato.subtotal();
            actual = actual.siguiente;
        }
        return total;
    }

    // Vaciar carrito
    public void vaciarCarrito() {
        cabeza = null;
    }

    public boolean estaVacio() {
        return cabeza == null;
    }
}
    

