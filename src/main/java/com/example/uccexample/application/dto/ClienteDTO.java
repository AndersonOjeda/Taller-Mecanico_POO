package com.example.uccexample.application.dto;

import java.util.List;

public class ClienteDTO {
    
    private Long idCliente;
    private String nombre;
    private float presupuesto;
    private List<CarroDTO> carros;
    
    public ClienteDTO() {}
    
    public ClienteDTO(Long idCliente, String nombre, float presupuesto) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }
    
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
    
    public List<CarroDTO> getCarros() {
        return carros;
    }
    
    public void setCarros(List<CarroDTO> carros) {
        this.carros = carros;
    }
    
    @Override
    public String toString() {
        return "ClienteDTO{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", carros=" + carros +
                '}';
    }
}