package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.Producto;

public interface IProductoDao {
    void agregarProducto(Producto producto);
    Producto buscarProducto(Long id);
    void modificarPrecio(Producto producto);
    void eliminarProducto(Producto producto);
}