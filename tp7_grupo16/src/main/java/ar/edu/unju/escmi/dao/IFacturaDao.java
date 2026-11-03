package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.Factura;

public interface IFacturaDao {
    void agregarFactura(Factura factura);
    Factura buscarFactura(int nroFactura);
    void eliminarFactura(Factura factura);

}
