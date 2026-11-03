```java
package ar.edu.unju.escmi.main;

import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.dao.IFacturaDao;
import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.IProductoDao;

import ar.edu.unju.escmi.dao.imp.FacturaDaoImp;
import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.dao.imp.ProductoDaoImp;

import ar.edu.unju.escmi.entities.Factura;
import ar.edu.unju.escmi.entities.DetalleFactura;
import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.Producto;

public class MenuFactura {

    private Scanner scanner = new Scanner(System.in);

    private IFacturaDao facturaDao = new FacturaDaoImp();
    private IClienteDao clienteDao = new ClienteDaoImp();
    private IProductoDao productoDao = new ProductoDaoImp();

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n++++++++++ MENU FACTURA +++++++++");
            System.out.println("1. Realizar venta");
            System.out.println("2. Buscar factura");
            System.out.println("3. Eliminar factura");
            System.out.println("4. Mostrar todas las facturas");
            System.out.println("5. Mostrar facturas mayores a $900.000");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1: realizarVenta(); break;
                    case 2: buscarFactura(); break;
                    case 3: eliminarFactura(); break;
                    case 4: mostrarFacturas(); break;
                    case 5: mostrarFacturasMayores(); break;
                    case 6: System.out.println("Volviendo..."); break;
                    default: System.out.println("Opcion incorrecta.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
                opcion = 0;
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
                opcion = 0;
            }

        } while (opcion != 6);
    }

    // 1. Realizar venta
    private void realizarVenta() {
        System.out.print("Ingrese el ID del cliente: ");
        Long id = Long.parseLong(scanner.nextLine());

        Cliente cliente = clienteDao.buscarCliente(id);

        if (cliente == null || !cliente.isEstado()) {
            System.out.println("Cliente inexistente o dado de baja.");
            return;
        }

        Factura factura = new Factura(cliente);
        String respuesta = "S";

        while (respuesta.equalsIgnoreCase("S")) {
            System.out.print("Ingrese el ID del producto (0 para finalizar): ");
            Long idProducto = Long.parseLong(scanner.nextLine());

            if (idProducto == 0) break;

            Producto producto = productoDao.buscarProducto(idProducto);

            if (producto == null || !producto.isEstado()) {
                System.out.println("Producto inexistente o dado de baja.");
                continue;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a cero.");
                continue;
            }

            DetalleFactura detalle = new DetalleFactura(producto, cantidad);
            factura.agregarDetalle(detalle);

            System.out.println("Subtotal: $" + detalle.getSubtotal());
            System.out.println("Total: $" + factura.getTotal());

            System.out.print("¿Desea agregar otro producto? (S/N): ");
            respuesta = scanner.nextLine();
        }

        if (factura.getDetalles().isEmpty()) {
            System.out.println("La factura no tiene productos.");
            return;
        }

        facturaDao.agregarFactura(factura);

        if (factura.getId() != null &&
                facturaDao.buscarFactura(factura.getId()) != null) {
            System.out.println("¡Venta registrada correctamente!");
            System.out.println(facturaDao.buscarFactura(factura.getId()));
        } else {
            System.out.println("No se pudo confirmar el registro de la factura.");
        }
    }

    // 2. Buscar factura
    private void buscarFactura() {
        System.out.print("Ingrese el numero de factura: ");
        Long id = Long.parseLong(scanner.nextLine());

        Factura factura = facturaDao.buscarFactura(id);

        if (factura != null) {
            System.out.println(factura);
        } else {
            System.out.println("Factura inexistente.");
        }
    }

    // 3. Eliminar factura
    private void eliminarFactura() {
        System.out.print("Ingrese el numero de factura: ");
        Long id = Long.parseLong(scanner.nextLine());

        Factura factura = facturaDao.buscarFactura(id);

        if (factura == null || !factura.isEstado()) {
            System.out.println("Factura inexistente o ya dada de baja.");
            return;
        }

        System.out.print("¿Confirma la baja de la factura? (S/N): ");

        if (scanner.nextLine().equalsIgnoreCase("S")) {
            facturaDao.eliminarFactura(factura);
            System.out.println("Factura dada de baja.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    // 4. Mostrar todas las facturas
    private void mostrarFacturas() {
        List<Factura> facturas = facturaDao.mostrarFacturas();

        if (facturas.isEmpty()) {
            System.out.println("No hay facturas para mostrar.");
        } else {
            for (Factura factura : facturas) {
                System.out.println(factura);
            }
        }
    }

    // 5. Mostrar facturas mayores a $900.000
    private void mostrarFacturasMayores() {
        List<Factura> facturas = facturaDao.mostrarFacturasMayoresA(900000);

        if (facturas.isEmpty()) {
            System.out.println("No hay facturas superiores a $900.000.");
        } else {
            for (Factura factura : facturas) {
                System.out.println(factura);
            }
        }
    }
}
```