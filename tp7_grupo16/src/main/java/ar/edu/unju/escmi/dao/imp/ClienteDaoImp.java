package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.entities.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ClienteDaoImp implements IClienteDao {

    private EntityManager crearManager() {
        return EmfSingleton.getInstance().getEmf().createEntityManager();
    }

    @Override
    public void agregarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null.");
        }

        EntityManager manager = crearManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            manager.persist(cliente);

            tx.commit();

            System.out.println("¡Cliente guardado con éxito!");

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            System.out.println("No se pudo guardar el cliente: " + e.getMessage());
            throw e;

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
            throw e;

        } finally {
            manager.close();
        }
    }

    @Override
    public void modificarDatosCliente(Cliente cliente) {

        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException(
                    "El cliente y su ID deben ser válidos.");
        }

        EntityManager manager = crearManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Cliente clienteExistente =
                    manager.find(Cliente.class, cliente.getId());

            if (clienteExistente == null) {
                throw new IllegalArgumentException(
                        "No existe un cliente con ese ID.");
            }

            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setDomicilio(cliente.getDomicilio());
            clienteExistente.setDni(cliente.getDni());

            tx.commit();

            System.out.println("¡Datos del cliente actualizados!");

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            System.out.println("No se pudo modificar el cliente: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }

    @Override
    public void eliminarCliente(Cliente cliente) {

        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException(
                    "El cliente y su ID deben ser válidos.");
        }

        EntityManager manager = crearManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Cliente clienteExistente =
                    manager.find(Cliente.class, cliente.getId());

            if (clienteExistente == null) {
                throw new IllegalArgumentException(
                        "No existe un cliente con ese ID.");
            }

            clienteExistente.setEstado(false);

            tx.commit();

            System.out.println("¡Cliente dado de baja de forma lógica!");

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            System.out.println("No se pudo eliminar el cliente: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }

    @Override
    public List<Cliente> mostrarClientes() {

        EntityManager manager = crearManager();

        try {
            TypedQuery<Cliente> query = manager.createQuery(
                    "SELECT c FROM Cliente c",
                    Cliente.class
            );

            return query.getResultList();

        } catch (RuntimeException e) {

            System.out.println("No se pudieron obtener los clientes: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }
}