/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizestructura;

/**
 *
 * @author Michel Yepez
 */
public class ListaDeProductos {
    private NodoProducto cabeza;
    private int siguienteId;

    public ListaDeProductos() {
        cabeza = null;
        siguienteId = 1;
    }

    // Agregar producto
    public void agregarProducto(Producto producto) {
        producto.id = siguienteId++;  // id automatico
        NodoProducto nuevo = new NodoProducto(producto);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        System.out.println("Producto agregado con exito. ID asignado: " + producto.id + " | Stock: " + producto.stock);
    }

    // Listar productos
    public void listarProductos() {
        if (cabeza == null) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        NodoProducto actual = cabeza;
        while (actual != null) {
            Producto p = actual.dato;
            System.out.println("ID: " + p.id + " | Nombre: " + p.nombre
                    + " | Descripcion: " + p.descripcion
                    + " | Precio: $" + p.precio
                    + " | Stock: " + p.stock);
            
            actual = actual.siguiente;
        }
    }

    // Buscar por id
    public Producto buscarPorId(int id) {
        NodoProducto actual = cabeza;
        while (actual != null) {
            if (actual.dato.id == id) return actual.dato;  // acceso directo
            actual = actual.siguiente;
        }
        return null;
    }

    // Editar producto
    public boolean editarProducto(int id, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevoStock) {
        Producto p = buscarPorId(id);
        if (p == null) return false;
        p.nombre = nuevoNombre;
        p.descripcion = nuevaDescripcion;
        p.precio = nuevoPrecio;
        p.stock = nuevoStock;
        return true;
    }

    // Eliminar producto
    public boolean eliminarProducto(int id) {
        if (cabeza == null) return false;

        if (cabeza.dato.id == id) {
            cabeza = cabeza.siguiente;
            return true;
        }

        NodoProducto actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.id == id) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean estaVacio() {
        return cabeza == null;
    }
}
