package com.example.uccexample.application.contract;

import com.example.uccexample.infraestructure.modelo.Factura;
import com.example.uccexample.application.dto.FacturaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Contrato (interfaz) CRUD personalizada para Factura
 * Define las operaciones de negocio específicas para Factura
 */
public interface IFacturaContract {
    
    // Operaciones CRUD básicas
    List<Factura> findAll();
    Optional<Factura> findById(Long id);
    Factura save(Factura factura);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
    void delete(Factura factura);
    
    // Operaciones específicas de negocio
    List<Factura> findByFecha(String fecha);
    List<Factura> findByFechaBetween(String fechaInicio, String fechaFin);
    List<Factura> findByMonto(float monto);
    List<Factura> findByMontoBetween(float montoMinimo, float montoMaximo);
    List<Factura> findByMontoGreaterThan(float monto);
    List<Factura> findByMontoLessThan(float monto);
    List<Factura> findAllByOrderByFechaDesc();
    List<Factura> findAllByOrderByMontoDesc();
    Float calcularTotalPorFecha(String fecha);
    Float calcularPromedioMontos();
    Long contarFacturasPorRangoFechas(String fechaInicio, String fechaFin);
    List<Factura> findByAnio(int anio);
    List<Factura> findByMes(int mes);
    
    // Operaciones con DTOs
    List<FacturaDTO> findAllDTO();
    Optional<FacturaDTO> findByIdDTO(Long id);
    FacturaDTO saveDTO(FacturaDTO facturaDTO);
    Optional<FacturaDTO> updateDTO(Long id, FacturaDTO facturaDTO);
    List<FacturaDTO> findByFechaDTO(String fecha);
    List<FacturaDTO> findByMontoDTO(float monto);
}