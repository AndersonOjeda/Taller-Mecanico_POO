package com.example.uccexample.application.repository;

import java.util.List;
import java.util.Optional;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.infraestructure.modelo.Factura;


public interface IFacturaRepository {
    
    List<Factura> findAll();
    
    Optional<Factura> findById(Long id);
    
    Factura save(Factura factura);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
    
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
    

    List<FacturaDTO> findAllDTO();
    
    Optional<FacturaDTO> findByIdDTO(Long id);
    
    FacturaDTO saveDTO(FacturaDTO facturaDTO);
    
    List<FacturaDTO> findByFechaDTO(String fecha);
    
    List<FacturaDTO> findByFechaBetweenDTO(String fechaInicio, String fechaFin);
    
    List<FacturaDTO> findByMontoDTO(float monto);
    
    List<FacturaDTO> findByMontoBetweenDTO(float montoMinimo, float montoMaximo);
    
    List<FacturaDTO> findByMontoGreaterThanDTO(float monto);
    
    List<FacturaDTO> findByMontoLessThanDTO(float monto);
    
    List<FacturaDTO> findAllByOrderByFechaDescDTO();
    
    List<FacturaDTO> findAllByOrderByMontoDescDTO();
    
    Optional<FacturaDTO> updateDTO(Long id, FacturaDTO facturaDTO);
    
    void deleteByIdDTO(Long id);
    
    boolean existsByIdDTO(Long id);
    
    Float calcularTotalPorFechaDTO(String fecha);
    
    Float calcularPromedioMontosDTO();
    
    Long contarFacturasPorRangoFechasDTO(String fechaInicio, String fechaFin);
}