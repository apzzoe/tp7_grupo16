package ar.edu.unju.escmi.main;

import java.util.Scanner;

import ar.edu.unju.escmi.dao.IProductoDao;
import ar.edu.unju.escmi.dao.imp.ProductoDaoImp;
import ar.edu.unju.escmi.entities.Producto;

public class MenuProducto {

    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("++++++++++ MENU PRODUCTO +++++++++");
            System.out.println("1. Alta de producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Cambiar precio de un producto");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                      
                        break;
                    case 5:
                    	System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }

        } while (opcion != 5);
    }

}