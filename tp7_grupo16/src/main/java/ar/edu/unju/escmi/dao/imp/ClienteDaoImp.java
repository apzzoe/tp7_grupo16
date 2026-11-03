package ar.edu.unju.escmi.dao.imp;

import java.util.List;
import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.entities.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ClienteDaoImp implements IClienteDao {

    private EntityManager crearManager() {
        return EmfSingleton.getInstance().getEmf().createEntityManager();
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("El cliente no puede ser null.");
            return;
        }

        EntityManager manager = crearManager();

        try {
            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();
            System.out.println("¡Cliente guardado con éxito!");
        } catch (RuntimeException e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo guardar el cliente: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public Cliente buscarCliente(Long id) {
        if (id == null) {
            return null;
        }

        EntityManager manager = crearManager();
        try {
            return manager.find(Cliente.class, id);
        } catch (RuntimeException e) {
            System.out.println("No se pudo buscar el cliente: " + e.getMessage());
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void modificarDatosCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            System.out.println("El cliente y su ID deben ser válidos.");
            return;
        }

        EntityManager manager = crearManager();

        try {
            manager.getTransaction().begin();
            manager.merge(cliente); 
            manager.getTransaction().commit();
            System.out.println("¡Datos del cliente actualizados!");
        } catch (RuntimeException e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo modificar el cliente: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            System.out.println("El cliente y su ID deben ser válidos.");
            return;
        }

        EntityManager manager = crearManager();

        try {
            manager.getTransaction().begin();
            Cliente clienteExistente = manager.find(Cliente.class, cliente.getId());
            if (clienteExistente != null) {
                clienteExistente.setEstado(false); 
            }
            manager.getTransaction().commit();
            System.out.println("¡Cliente dado de baja de forma lógica!");
        } catch (RuntimeException e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo eliminar el cliente: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Cliente> mostrarClientes() {
        EntityManager manager = crearManager();
        try {
            TypedQuery<Cliente> query = manager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } catch (RuntimeException e) {
            System.out.println("No se pudieron obtener los clientes: " + e.getMessage());
            return null;
        } finally {
            manager.close();
        }
    }
}
