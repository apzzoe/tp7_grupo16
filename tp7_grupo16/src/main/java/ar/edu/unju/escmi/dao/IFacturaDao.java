package ar.edu.unju.escmi.dao;

import java.util.List;

import ar.edu.unju.escmi.entities.Factura;

public interface IFacturaDao {
    void agregarFactura(Factura factura);
    Factura buscarFactura(Long nroFactura);
    void eliminarFactura(Factura factura);

}
