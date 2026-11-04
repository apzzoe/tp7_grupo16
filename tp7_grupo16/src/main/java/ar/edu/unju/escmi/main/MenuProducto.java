package ar.edu.unju.escmi.main;

import java.util.Scanner;

import ar.edu.unju.escmi.dao.IProductoDao;
import ar.edu.unju.escmi.dao.imp.ProductoDaoImp;
import ar.edu.unju.escmi.entities.Producto;

public class MenuProducto {

    private Scanner scanner = new Scanner(System.in);
    private IProductoDao productoDao = new ProductoDaoImp();

    public void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("++++++++++ MENU PRODUCTO +++++++++");
            System.out.println("1. Alta de producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Cambiar precio de un producto");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                // Se lee de forma limpia y segura una sola vez
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        altaProducto();
                        break;
                    case 2:
                        eliminarProducto();
                        break;
                    case 3:
                        modificarPrecio();
                        break;
                    case 4:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }

        } while (opcion != 4);
    }
    
    private void altaProducto() {
        System.out.println("----- ALTA DE PRODUCTO -----");

        System.out.print("Ingrese descripcion: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese precio unitario: ");
        double precio = Double.parseDouble(scanner.nextLine());

        if (precio <= 0) {
            System.out.println("El precio debe ser mayor a cero.");
            return;
        }

        Producto producto = new Producto(descripcion, precio);
        productoDao.agregarProducto(producto);
    }

    private void eliminarProducto() {
        System.out.println("----- ELIMINAR PRODUCTO -----");

        System.out.print("Ingrese el ID del producto: ");
        Long id = Long.parseLong(scanner.nextLine());

        Producto producto = productoDao.buscarProducto(id);

        if (producto == null || !producto.isEstado()) {
            System.out.println("Producto no encontrado");
            return;
        }

        productoDao.eliminarProducto(producto);
    }
    
    private void modificarPrecio() {
        System.out.print("Ingrese el ID del producto: ");
        Long id = Long.parseLong(scanner.nextLine());

        Producto producto = productoDao.buscarProducto(id);

        if (producto == null || !producto.isEstado()) {
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("Producto: " + producto.getDescripcion());
        System.out.println("Precio actual: " + producto.getPrecioUnitario());

        System.out.print("Ingrese nuevo precio: ");
        double nuevoPrecio = Double.parseDouble(scanner.nextLine());

        if (nuevoPrecio <= 0) {
            System.out.println("El precio debe ser mayor a cero.");
            return;
        }

        producto.setPrecioUnitario(nuevoPrecio);
        productoDao.modificarPrecio(producto);
    }
}