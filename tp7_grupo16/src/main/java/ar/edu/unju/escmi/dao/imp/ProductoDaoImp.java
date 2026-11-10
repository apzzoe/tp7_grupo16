package ar.edu.unju.escmi.dao.imp;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IProductoDao;
import ar.edu.unju.escmi.entities.Producto;

import jakarta.persistence.EntityManager;

public class ProductoDaoImp implements IProductoDao {

    private EntityManager crearManager() {
        return EmfSingleton.getInstance().getEmf().createEntityManager();
    }

    @Override
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("El producto no puede ser null.");
            return;
        }

        EntityManager manager = crearManager();

        try {
            manager.getTransaction().begin();
            manager.persist(producto);
            manager.getTransaction().commit();
            System.out.println("¡Producto guardado con éxito!");
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo guardar el producto: " + e.getMessage());
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
        } catch (Exception e) {
            System.out.println("No se pudo buscar el producto: " + e.getMessage());
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void modificarPrecio(Producto producto) {
        if (producto == null || producto.getId() == null) {
            System.out.println("El producto y su ID deben ser válidos.");
            return;
        }

        EntityManager manager = crearManager();

        try {
            manager.getTransaction().begin();
            Producto productoExistente = manager.find(Producto.class, producto.getId());
            
            if (productoExistente != null) {
                productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
                manager.getTransaction().commit();
                System.out.println("¡Precio del producto modificado con éxito!");
            } else {
                System.out.println("No existe un producto con ese ID.");
                manager.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo modificar el producto: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public void eliminarProducto(Producto producto) {
        if (producto == null || producto.getId() == null) {
            System.out.println("El producto y su ID deben ser válidos.");
            return;
        }

        EntityManager manager = crearManager();

        try {
            manager.getTransaction().begin();
            Producto productoExistente = manager.find(Producto.class, producto.getId());
            
            if (productoExistente != null) {
                productoExistente.setEstado(false);
                manager.getTransaction().commit();
                System.out.println("¡Producto dado de baja de forma lógica!");
            } else {
                System.out.println("No existe un producto con ese ID.");
                manager.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo eliminar el producto: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

}