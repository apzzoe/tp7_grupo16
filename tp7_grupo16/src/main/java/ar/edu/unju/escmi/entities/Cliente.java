package ar.edu.unju.escmi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column(name = "cliente_nombre", length = 15, nullable = false)
    private String nombre;

    @Column(name = "cliente_apellido", length = 15, nullable = false)
    private String apellido;

    @Column(name = "cliente_domicilio", length = 100)
    private String domicilio;

    @Column(name = "cliente_dni", nullable = false)
    private int dni;

    @Column(name = "cliente_estado")
    private boolean estado;
    

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String domicilio, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.estado = true;
    }


    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", domicilio=" + domicilio
				+ ", dni=" + dni + ", estado=" + estado + "]";
	}

	
}