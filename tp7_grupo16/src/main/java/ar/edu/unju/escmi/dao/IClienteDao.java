package ar.edu.unju.escmi.dao;

import java.util.List;
import ar.edu.unju.escmi.entities.Cliente;

public interface IClienteDao {
    void guardarCliente(Cliente cliente);
    Cliente buscarCliente(Long id);
    void modificarDatosCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
    List<Cliente> obtenerClientes();
}