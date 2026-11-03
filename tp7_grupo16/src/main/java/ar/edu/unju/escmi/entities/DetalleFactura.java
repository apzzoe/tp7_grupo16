package ar.edu.unju.escmi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private Producto producto;

    @Column(name = "det_cantidad")
    private int cantidad;

    @Column(name = "det_subtotal")
    private double subtotal;

    public DetalleFactura() {
    }

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    private void calcularSubtotal() {
        this.subtotal = cantidad * producto.getPrecioUnitario();
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "\nDetalleFactura [id=" + id + ", producto=" + producto.getDescripcion() + ", cantidad=" + cantidad
                + ", subtotal=" + subtotal + "]";
    }
}