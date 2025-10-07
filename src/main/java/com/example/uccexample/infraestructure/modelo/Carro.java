package com.example.uccexample.infraestructure.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="auto")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auto")
    private Long idAuto;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "anio")
    private int anio;

    @Column(name = "estado")
    private String estado;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "costo_dano")
    private String costoDano;

    @ManyToOne
    @JoinColumn(name = "id_cliente") 
    private Cliente cliente;


    public Long getIdAuto() { 
        return idAuto; 
    }
    public void setIdAuto(Long idAuto) { 
        this.idAuto = idAuto; 
    }

    public String getModelo() { 
        return modelo; 
    }
    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    }

    public int getAnio() { 
        return anio; 
    }
    public void setAnio(int anio) { 
        this.anio = anio; 
    }

    public String getEstado() { 
        return estado; 
    }
    public void setEstado(String estado) { 
        this.estado = estado; 
    }

    public String getTipo() { 
        return tipo; 
    }
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }

    public String getCostoDano() { 
        return costoDano;
    }
    public void setCostoDano(String costoDano) { 
        this.costoDano = costoDano; 
    }

    public Cliente getCliente() { 
        return cliente; 
    }
    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }
}
