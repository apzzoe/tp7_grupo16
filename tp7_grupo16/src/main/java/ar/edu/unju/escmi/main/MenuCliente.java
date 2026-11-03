package ar.edu.unju.escmi.main;

import java.util.Scanner;

import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.entities.Cliente;

public class MenuCliente {

    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("++++++++++ MENU CLIENTE +++++++++");
            System.out.println("1. Alta cliente");
            System.out.println("2. Modificar datos del cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Mostrar todos los clientes");
            System.out.println("5. Atras");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1:

                        break;
                    case 2:
                    	do {
                    	submenucliente();
                    	 System.out.print("Seleccione una opcion: ");
                         opcion = scanner.nextInt();
                         scanner.nextLine();
                    	}while(opcion!=7);
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
    
    public void submenucliente() {
    	 System.out.println("++++++++++ MENU DATOS-CLIENTE +++++++++");
         System.out.println("1. ID");
         System.out.println("2. Nombre");
         System.out.println("3. Apellido");
         System.out.println("4. Domicilio");
         System.out.println("5. dni");
         System.out.print("6. Estado: ");
         System.out.print("7. Cancelar: ");
    }

}