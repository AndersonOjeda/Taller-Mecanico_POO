package com.example.uccexample.application.dto;

import java.util.List;

/**
 * DTO para la entidad Cliente
 * Utilizado para transferir datos entre capas sin exponer la entidad directamente
 */
public class ClienteDTO {
    
    private Long idCliente;
    private String nombre;
    private float presupuesto;
    private List<CarroDTO> carros;
    
    // Constructor por defecto
    public ClienteDTO() {}
    
    // Constructor con parámetros
    public ClienteDTO(Long idCliente, String nombre, float presupuesto) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }
    
    // Getters y Setters
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