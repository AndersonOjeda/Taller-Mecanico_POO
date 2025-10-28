package com.example.uccexample.application.service;

import com.example.uccexample.infraestructure.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    @Transactional(readOnly = true)
    public void obtenerTodasLasFacturas() {
        facturaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public void obtenerFacturaPorId(Long id) {
        facturaRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public void buscarFacturasPorFecha(String fecha) {
        facturaRepository.findByFecha(fecha);
    }
    
    @Transactional(readOnly = true)
    public void buscarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        facturaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    @Transactional(readOnly = true)
    public void buscarFacturasPorMonto(float monto) {
        facturaRepository.findByMonto(monto);
    }
    
    @Transactional(readOnly = true)
    public void buscarFacturasPorRangoMontos(float montoMinimo, float montoMaximo) {
        facturaRepository.findByMontoBetween(montoMinimo, montoMaximo);
    }
    
    @Transactional(readOnly = true)
    public void buscarFacturasConMontoMayorA(float monto) {
        facturaRepository.findByMontoGreaterThan(monto);
    }
    
    @Transactional(readOnly = true)
    public void buscarFacturasConMontoMenorA(float monto) {
        facturaRepository.findByMontoLessThan(monto);
    }
    
    @Transactional(readOnly = true)
    public void obtenerFacturasOrdenadasPorFecha() {
        facturaRepository.findAllByOrderByFechaDesc();
    }
    
    @Transactional(readOnly = true)
    public void obtenerFacturasOrdenadasPorMonto() {
        facturaRepository.findAllByOrderByMontoDesc();
    }
    
    public void crearFactura(float monto, String fecha) {
        // Validaciones básicas
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        
        // Solo operaciones de repositorio
    }
    
    public void actualizarFactura(Long id, float monto, String fecha) {
        facturaRepository.findById(id);
        // Solo operaciones de repositorio
    }
    
    public boolean eliminarFactura(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = true)
    public Float calcularTotalPorFecha(String fecha) {
        Float total = facturaRepository.calcularTotalPorFecha(fecha);
        return total != null ? total : 0.0f;
    }
    
    @Transactional(readOnly = true)
    public Float calcularPromedioMontos() {
        Float promedio = facturaRepository.calcularPromedioMontos();
        return promedio != null ? promedio : 0.0f;
    }
    
    @Transactional(readOnly = true)
    public Long contarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        return facturaRepository.contarFacturasPorRangoFechas(fechaInicio, fechaFin);
    }
}