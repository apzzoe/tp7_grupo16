package ar.edu.unju.escmi.dao.imp;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IProductoDao;
import ar.edu.unju.escmi.entities.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProductoDaoImp implements IProductoDao {

    private EntityManager crearManager() {
        return EmfSingleton.getInstance().getEmf().createEntityManager();
    }

    @Override
    public void agregarProducto(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }

        EntityManager manager = crearManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            manager.persist(producto);

            tx.commit();

            System.out.println("¡Producto guardado con éxito!");

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            System.out.println("No se pudo guardar el producto: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }

    @Override
    public Producto buscarProducto(Long id) {

        if (id == null) {
            return null;
        }

        EntityManager manager = crearManager();

        try {
            return manager.find(Producto.class, id);

        } catch (RuntimeException e) {

            System.out.println("No se pudo buscar el producto: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }

    @Override
    public void modificarPrecio(Producto producto) {

        if (producto == null || producto.getId() == null) {
            throw new IllegalArgumentException(
                    "El producto y su ID deben ser válidos.");
        }

        EntityManager manager = crearManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Producto productoExistente =
                    manager.find(Producto.class, producto.getId());

            if (productoExistente == null) {
                throw new IllegalArgumentException(
                        "No existe un producto con ese ID.");
            }

            productoExistente.setPrecioUnitario(
                    producto.getPrecioUnitario()
            );

            tx.commit();

            System.out.println("¡Precio del producto modificado con éxito!");

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            System.out.println("No se pudo modificar el producto: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }

    @Override
    public void eliminarProducto(Producto producto) {

        if (producto == null || producto.getId() == null) {
            throw new IllegalArgumentException(
                    "El producto y su ID deben ser válidos.");
        }

        EntityManager manager = crearManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            Producto productoExistente =
                    manager.find(Producto.class, producto.getId());

            if (productoExistente == null) {
                throw new IllegalArgumentException(
                        "No existe un producto con ese ID.");
            }

            productoExistente.setEstado(false);

            tx.commit();

            System.out.println("¡Producto dado de baja de forma lógica!");

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            System.out.println("No se pudo eliminar el producto: " + e.getMessage());
            throw e;

        } finally {
            manager.close();
        }
    }
}