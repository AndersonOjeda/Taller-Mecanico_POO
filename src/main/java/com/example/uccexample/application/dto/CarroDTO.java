package com.example.uccexample.application.dto;

/**
 * DTO para la entidad Carro
 * Utilizado para transferir datos entre capas sin exponer la entidad directamente
 */
public class CarroDTO {
    
    private Long idAuto;
    private String modelo;
    private int anio;
    private String estado;
    private String tipo;
    private String costoDano;
    private Long clienteId; // Solo el ID del cliente para evitar referencias circulares
    
    // Constructor por defecto
    public CarroDTO() {}
    
    // Constructor con parámetros
    public CarroDTO(Long idAuto, String modelo, int anio, String estado, String tipo, String costoDano, Long clienteId) {
        this.idAuto = idAuto;
        this.modelo = modelo;
        this.anio = anio;
        this.estado = estado;
        this.tipo = tipo;
        this.costoDano = costoDano;
        this.clienteId = clienteId;
    }
    
    // Getters y Setters
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
    
    public Long getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
    @Override
    public String toString() {
        return "CarroDTO{" +
                "idAuto=" + idAuto +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", estado='" + estado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", costoDano='" + costoDano + '\'' +
                ", clienteId=" + clienteId +
                '}';
    }
}