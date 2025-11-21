package com.example.uccexample.infraestructure.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Long idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "presupuesto")
    private float presupuesto;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carro> carros;


    public Long getIdCliente() { 
        return idCliente; 
    }
    public void setIdCliente(Long idCliente) { 
        this.idCliente = idCliente; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public float getPresupuesto() { 
        return presupuesto; 
    }
    public void setPresupuesto(float presupuesto) { 
        this.presupuesto = presupuesto; 
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Carro> getCarros() { 
        return carros; 
    }
    public void setCarros(List<Carro> carros) { 
        this.carros = carros; 
    }
}
