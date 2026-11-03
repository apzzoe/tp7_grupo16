package ar.edu.unju.escmi.dao.imp;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IFacturaDao;
import ar.edu.unju.escmi.entities.Factura;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class FacturaDaoImp implements IFacturaDao {

    private EntityManager crearManager() {
        return EmfSingleton.getInstance().getEmf().createEntityManager();
    }

    @Override
    public void agregarFactura(Factura factura) {
        EntityManager manager = crearManager();
        try {
            manager.getTransaction().begin();
            manager.persist(factura);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo guardar la factura: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public Factura buscarFactura(Long nroFactura) {
        EntityManager manager = crearManager();
        try {
            return manager.find(Factura.class, nroFactura);
        } catch (Exception e) {
            System.out.println("No se pudo buscar la factura: " + e.getMessage());
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void eliminarFactura(Factura factura) {
        EntityManager manager = crearManager();
        try {
            manager.getTransaction().begin();
            factura.setEstado(false);
            manager.merge(factura);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo eliminar la factura: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Factura> mostrarFacturas() {
        EntityManager manager = crearManager();
        try {
            TypedQuery<Factura> query = manager.createQuery(
                    "SELECT f FROM Factura f WHERE f.estado = true", Factura.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("No se pudieron obtener las facturas: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Factura> mostrarFacturasMayoresA(double monto) {
        EntityManager manager = crearManager();
        try {
            TypedQuery<Factura> query = manager.createQuery(
                    "SELECT f FROM Factura f WHERE f.estado = true AND f.total > :monto", Factura.class);
            query.setParameter("monto", monto);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("No se pudieron obtener las facturas: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            manager.close();
        }
    }
}
