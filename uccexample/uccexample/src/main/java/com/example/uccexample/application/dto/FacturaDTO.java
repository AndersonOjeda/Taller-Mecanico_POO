package com.example.uccexample.application.dto;

public class FacturaDTO {
    
    private Long idFactura;
    private String fecha;
    private float monto;
    
    public FacturaDTO() {}
    
    public FacturaDTO(Long idFactura, String fecha, float monto) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.monto = monto;
    }
    public Long getIdFactura() {
        return idFactura;
    }
    
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public float getMonto() {
        return monto;
    }
    
    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    @Override
    public String toString() {
        return "FacturaDTO{" +
                "idFactura=" + idFactura +
                ", fecha='" + fecha + '\'' +
                ", monto=" + monto +
                '}';
    }
}