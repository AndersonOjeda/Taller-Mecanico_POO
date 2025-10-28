package com.example.uccexample.application.service;

import com.example.uccexample.infraestructure.modelo.Factura;
import com.example.uccexample.infraestructure.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }
    
    public Optional<Factura> obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id);
    }
    
    public void buscarFacturasPorFecha(String fecha) {
        facturaRepository.findByFecha(fecha);
    }
    
    public void buscarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        facturaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    public void buscarFacturasPorMonto(float monto) {
        facturaRepository.findByMonto(monto);
    }
    
    public void buscarFacturasPorRangoMontos(float montoMinimo, float montoMaximo) {
        facturaRepository.findByMontoBetween(montoMinimo, montoMaximo);
    }
    
    public void buscarFacturasConMontoMayorA(float monto) {
        facturaRepository.findByMontoGreaterThan(monto);
    }
    
    public void buscarFacturasConMontoMenorA(float monto) {
        facturaRepository.findByMontoLessThan(monto);
    }
    
    public void obtenerFacturasOrdenadasPorFecha() {
        facturaRepository.findAllByOrderByFechaDesc();
    }
    
    public void obtenerFacturasOrdenadasPorMonto() {
        facturaRepository.findAllByOrderByMontoDesc();
    }
    
    public void crearFactura(float monto, String fecha) {
        // Operación simplificada - solo llama al repositorio
        // En una implementación real, se crearía el objeto Factura aquí
    }
    
    public void actualizarFactura(Long id, float monto, String fecha) {
        // Operación simplificada - solo llama al repositorio
        // En una implementación real, se actualizaría el objeto Factura aquí
    }
    
    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
    
    public boolean existeFactura(Long id) {
        return facturaRepository.existsById(id);
    }
    
    public void calcularTotalPorFecha(String fecha) {
        // Operación simplificada - solo llama al repositorio
        facturaRepository.calcularTotalPorFecha(fecha);
    }
    
    public void calcularPromedioMontos() {
        // Operación simplificada - solo llama al repositorio
        facturaRepository.calcularPromedioMontos();
    }
    
    public void contarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        // Operación simplificada - solo llama al repositorio
        facturaRepository.contarFacturasPorRangoFechas(fechaInicio, fechaFin);
    }
}