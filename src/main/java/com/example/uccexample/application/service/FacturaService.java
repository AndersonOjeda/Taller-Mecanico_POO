package com.example.uccexample.application.service;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.infraestructure.modelo.Factura;
import com.example.uccexample.infraestructure.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    public List<FacturaDTO> obtenerTodasLasFacturas() {
        return facturaRepository.findAllDTO();
    }
    
    public Optional<FacturaDTO> obtenerFacturaPorId(Long id) {
        return facturaRepository.findByIdDTO(id);
    }
    
    public List<FacturaDTO> buscarFacturasPorFecha(String fecha) {
        return facturaRepository.findByFechaDTO(fecha);
    }
    
    public List<FacturaDTO> buscarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        return facturaRepository.findByFechaBetweenDTO(fechaInicio, fechaFin);
    }
    
    public List<FacturaDTO> buscarFacturasPorMonto(float monto) {
        return facturaRepository.findByMontoDTO(monto);
    }
    
    public List<FacturaDTO> buscarFacturasPorRangoMontos(float montoMinimo, float montoMaximo) {
        return facturaRepository.findByMontoBetweenDTO(montoMinimo, montoMaximo);
    }
    
    public List<FacturaDTO> buscarFacturasConMontoMayorA(float monto) {
        return facturaRepository.findByMontoGreaterThanDTO(monto);
    }
    
    public List<FacturaDTO> buscarFacturasConMontoMenorA(float monto) {
        return facturaRepository.findByMontoLessThanDTO(monto);
    }
    
    public List<FacturaDTO> obtenerFacturasOrdenadasPorFecha() {
        return facturaRepository.findAllByOrderByFechaDescDTO();
    }
    
    public List<FacturaDTO> obtenerFacturasOrdenadasPorMonto() {
        return facturaRepository.findAllByOrderByMontoDescDTO();
    }
    
    public Factura crearFactura(float monto, String fecha) {
        Factura factura = new Factura();
        factura.setMonto(monto);
        factura.setFecha(fecha);
        return facturaRepository.save(factura);
    }
    
    public Optional<Factura> actualizarFactura(Long id, float monto, String fecha) {
        return facturaRepository.findById(id)
                .map(facturaExistente -> {
                    facturaExistente.setMonto(monto);
                    facturaExistente.setFecha(fecha);
                    return facturaRepository.save(facturaExistente);
                });
    }
    
    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
    
    public boolean existeFactura(Long id) {
        return facturaRepository.existsById(id);
    }
    
    public Float calcularTotalPorFecha(String fecha) {
        return facturaRepository.calcularTotalPorFechaDTO(fecha);
    }
    
    public Float calcularPromedioMontos() {
        return facturaRepository.calcularPromedioMontosDTO();
    }
    
    public Long contarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        return facturaRepository.contarFacturasPorRangoFechasDTO(fechaInicio, fechaFin);
    }
}
