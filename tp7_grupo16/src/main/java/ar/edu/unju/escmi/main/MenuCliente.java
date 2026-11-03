package ar.edu.unju.escmi.main;

import java.util.Scanner;
import java.util.List;
import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.entities.Cliente;

public class MenuCliente {

	private IClienteDao clienteDao = new ClienteDaoImp();
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion=0;

        do {
            System.out.println("++++++++++ MENU CLIENTE +++++++++");
            System.out.println("1. Alta cliente");
            System.out.println("2. Modificar datos del cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Mostrar todos los clientes");
            System.out.println("5. Atras");
            System.out.print("Seleccione una opcion: ");
            
            try {
            	opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                    	altaCliente();
                    	break;
                    case 2:
                    	modificarCliente();
                        break;
                    case 3:
                    	eliminarCliente();
                        break;
                    case 4:
                    	mostrarClientes();
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
    
    
    private void altaCliente() {
        System.out.println("--- ALTA DE CLIENTE ---");

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese domicilio: ");
        String domicilio = scanner.nextLine();

        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(scanner.nextLine());

        Cliente cliente = new Cliente(nombre, apellido, domicilio, dni);

        clienteDao.agregarCliente(cliente);
    }
    
    public void modificarCliente() {
    	 System.out.print("Ingrese ID del cliente que desea modificar: ");
         Long id = Long.parseLong(scanner.nextLine());
         Cliente cliente = clienteDao.buscarCliente(id);
         
         if (cliente == null || !cliente.isEstado()) {
             System.out.println("No se encontro un cliente activo con ese ID.");
             return;
         }
         int opcion;
         
         do {
             submenucliente();
             System.out.print("Seleccione el dato que desea modificar: ");
             opcion = Integer.parseInt(scanner.nextLine());

             switch (opcion) {
                 case 1:
                     System.out.print("Ingrese nuevo nombre: ");
                     cliente.setNombre(scanner.nextLine());
                     break;

                 case 2:
                     System.out.print("Ingrese nuevo apellido: ");
                     cliente.setApellido(scanner.nextLine());
                     break;

                 case 3:
                     System.out.print("Ingrese nuevo domicilio: ");
                     cliente.setDomicilio(scanner.nextLine());
                     break;

                 case 4:
                     System.out.print("Ingrese nuevo DNI: ");
                     cliente.setDni(Integer.parseInt(scanner.nextLine()));
                     break;

                 case 5:
                     System.out.println("Finalizando modificacion");
                     break;

                 default:
                     System.out.println("Opcion incorrecta.");
             }

         } while (opcion != 5);
         
         clienteDao.modificarDatosCliente(cliente);
    }
    
    private void eliminarCliente() {
        System.out.print("Ingrese el ID del cliente que desea eliminar: ");
        Long id = Long.parseLong(scanner.nextLine());

        Cliente cliente = clienteDao.buscarCliente(id);

        if (cliente == null || !cliente.isEstado()) {
            System.out.println("No se encontro un cliente activo con ese ID.");
            return;
        }

        clienteDao.eliminarCliente(cliente);
    }

    private void mostrarClientes() {
        List<Cliente> clientes = clienteDao.mostrarClientes();
        boolean hayClientes = false;

        for (Cliente cliente : clientes) {
            if (cliente.isEstado()) {
                System.out.println(cliente);
                hayClientes = true;
            }
        }

        if (!hayClientes) {
            System.out.println("No hay clientes activos para mostrar.");
        }
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