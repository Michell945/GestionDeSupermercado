/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizestructura;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDeProductos inventario = new ListaDeProductos();
        ListaDeCarro carrito = new ListaDeCarro();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n------MENU PRINCIPAL------");
            System.out.println("1. Gestionar productos");
            System.out.println("2. Vender productos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    gestionarProductos(sc, inventario);
                    break;
                case "2":
                    venderProductos(sc, inventario, carrito);
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }

        sc.close();
    }

    // CRUD de productos
    private static void gestionarProductos(Scanner sc, ListaDeProductos inventario) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIONAR PRODUCTOS ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Descripcion: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();
                    inventario.agregarProducto(new Producto(nombre, descripcion, precio,stock));
                    break;
                case "2":
                    System.out.println("\nProductos en inventario:");
                    inventario.listarProductos();
                    break;
                case "3":
                    System.out.print("Ingrese ID del producto a editar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Producto producEn = inventario.buscarPorId(id);
                    if (producEn == null) {
                        System.out.println("Producto no encontrado.");
                    } else {
                        System.out.println("Producto actual: ID: " + producEn.id
                                + " | Nombre: " + producEn.nombre
                                + " | Descripcion: " + producEn.descripcion
                                + " | Precio: $" + producEn.precio
                                + " | Stock: " + producEn.stock);

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Nueva descripcion: ");
                        String nuevaDesc = sc.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Nuevo stock: ");  
                        int nuevoStock =sc.nextInt();
                        sc.nextLine();

                        boolean Encontrado = inventario.editarProducto(id, nuevoNombre, nuevaDesc, nuevoPrecio, nuevoStock);
                        if (Encontrado) System.out.println("Producto editado correctamente.");
                        else System.out.println("No se pudo editar el producto.");
                    }
                    break;
                case "4":
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    int idEli = sc.nextInt();
                    sc.nextLine();
                    boolean eliminado = inventario.eliminarProducto(idEli);
                    if (eliminado) System.out.println("Producto eliminado.");
                    else System.out.println("Producto no encontrado.");
                    break;
                case "5":
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static void venderProductos(Scanner sc, ListaDeProductos inventario, ListaDeCarro carrito) {
        carrito.vaciarCarrito(); // empezamos con carrito vacío
        System.out.println("\n--- VENDER PRODUCTOS ---");
        System.out.print("Nombre del cliente: ");
        String nombreCliente = sc.nextLine();
        System.out.print("Cedula del cliente: ");
        String cedula = sc.nextLine();
        Usuario cliente = new Usuario(nombreCliente, cedula);

        boolean terminarCompra = false;
        while (!terminarCompra) {
            System.out.println("\nProductos disponibles:");
            inventario.listarProductos();

            System.out.print("Ingrese ID del producto para agregar al carrito (o '0' para terminar): ");
            int idSeleccion = sc.nextInt();
            sc.nextLine();
            if (idSeleccion == 0) {
                // finalizar selección
                if (carrito.estaVacio()) {
                    System.out.println("No agrego ningun producto. Venta cancelada.");
                    return;
                } else {
                    System.out.println("Desea finalizar la compra? (S/N)");
                    String r = sc.nextLine();
                    if (r.equalsIgnoreCase("S")) terminarCompra = true;
                    continue;
                }
            }

            Producto productoSeleccionado = inventario.buscarPorId(idSeleccion);
            if (productoSeleccionado == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            carrito.agregarAlCarrito(productoSeleccionado, cantidad);
            sc.nextLine();

            System.out.print("Agregar mas productos? (S/N): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("N")) {
                if (carrito.estaVacio()) {
                    System.out.println("Carrito vacio. Venta cancelada.");
                    return;
                } else {
                    System.out.println("Desea finalizar la compra? (S/N)");
                    String r = sc.nextLine();
                    if (r.equalsIgnoreCase("S")) terminarCompra = true;
                }
            }
        }

        // resumen de la compra
        System.out.println("\n=== RESUMEN DE COMPRA ===");
        System.out.println("Cliente: " + cliente.nombre + " => Cedula: " + cliente.cedula);
        carrito.listarCarrito();
        double total = carrito.calcularTotal();
        System.out.printf("TOTAL A PAGAR: $" + total);
        
    }
    }
    
