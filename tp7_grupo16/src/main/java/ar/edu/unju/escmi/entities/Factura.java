package ar.edu.unju.escmi.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fac_id")
    private Long id;

    @Column(name = "fac_fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "fac_domicilio", length = 100)
    private String domicilio;

    @Column(name = "fac_total")
    private double total;

    @Column(name = "fac_estado")
    private boolean estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fac_id")
    private List<DetalleFactura> detalles = new ArrayList<>();

    public Factura() {
    }

    public Factura(Cliente cliente) {
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.domicilio = cliente.getDomicilio();
        this.estado = true;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        this.detalles.add(detalle);
        calcularTotal();
    }

    public void calcularTotal() {
        double suma = 0;
        for (DetalleFactura d : detalles) {
            suma += d.getSubtotal();
        }
        this.total = suma;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public List<DetalleFactura> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleFactura> detalles) { this.detalles = detalles; }

    @Override
    public String toString() {
        return "Factura [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente.getNombre() + " "
                + cliente.getApellido() + ", domicilio=" + domicilio + ", total=" + total
                + ", estado=" + estado + ", detalles=" + detalles + "]";
    }
}
