package ar.edu.unju.escmi.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MenuCliente menuCliente = new MenuCliente();
        MenuProducto menuProducto = new MenuProducto();
        MenuFactura menuFactura = new MenuFactura();

        int opcion = 0;

        do {
            System.out.println("** MENU PRINCIPAL **");
            System.out.println("1. Clientes");
            System.out.println("2. Productos");
            System.out.println("3. Facturas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            try {
                switch (opcion) {
                    case 1:
                        menuCliente.mostrarMenu();
                        break;
                    case 2:
                        menuProducto.mostrarMenu();
                        break;
                    case 3:
                        menuFactura.mostrarMenu();
                        break;
                    case 4:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }

        } while (opcion != 4);

        scanner.close();
    }
}