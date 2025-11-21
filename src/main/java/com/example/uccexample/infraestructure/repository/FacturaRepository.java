package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.application.mapper.FacturaMapper;
import com.example.uccexample.application.repository.IFacturaRepository;
import com.example.uccexample.infraestructure.modelo.Factura;
import com.example.uccexample.infraestructure.repository.jpa.FacturaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio para la entidad Factura
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public class FacturaRepository implements IFacturaRepository {
    
    @Autowired
    private FacturaJpaRepository facturaJpaRepository;

    @Autowired
    private FacturaMapper facturaMapper;
    
    @Override
    public List<Factura> findAll() {
        return facturaJpaRepository.findAll();
    }
    
    @Override
    public List<FacturaDTO> findAllDTO() {
        List<Factura> facturas = findAll();
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public Optional<Factura> findById(Long id) {
        return facturaJpaRepository.findById(id);
    }
    
    @Override
    public Optional<FacturaDTO> findByIdDTO(Long id) {
        Optional<Factura> factura = findById(id);
        return factura.map(facturaMapper::toDTO);
    }
    
    @Override
    public Factura save(Factura factura) {
        return facturaJpaRepository.save(factura);
    }
    
    @Override
    public FacturaDTO saveDTO(FacturaDTO facturaDTO) {
        Factura factura = facturaMapper.toEntity(facturaDTO);
        Factura facturaGuardada = save(factura);
        return facturaMapper.toDTO(facturaGuardada);
    }
    
    @Override
    public void deleteById(Long id) {
        facturaJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return facturaJpaRepository.existsById(id);
    }
    
    @Override
    public List<Factura> findByFecha(String fecha) {
        return facturaJpaRepository.findByFecha(fecha);
    }
    
    @Override
    public List<FacturaDTO> findByFechaDTO(String fecha) {
        List<Factura> facturas = findByFecha(fecha);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByFechaBetween(String fechaInicio, String fechaFin) {
        return facturaJpaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    @Override
    public List<FacturaDTO> findByFechaBetweenDTO(String fechaInicio, String fechaFin) {
        List<Factura> facturas = findByFechaBetween(fechaInicio, fechaFin);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMonto(float monto) {
        return facturaJpaRepository.findByMonto(monto);
    }
    
    @Override
    public List<FacturaDTO> findByMontoDTO(float monto) {
        List<Factura> facturas = findByMonto(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMontoBetween(float montoMinimo, float montoMaximo) {
        return facturaJpaRepository.findByMontoBetween(montoMinimo, montoMaximo);
    }
    
    @Override
    public List<FacturaDTO> findByMontoBetweenDTO(float montoMinimo, float montoMaximo) {
        List<Factura> facturas = findByMontoBetween(montoMinimo, montoMaximo);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMontoGreaterThan(float monto) {
        return facturaJpaRepository.findByMontoGreaterThan(monto);
    }
    
    @Override
    public List<FacturaDTO> findByMontoGreaterThanDTO(float monto) {
        List<Factura> facturas = findByMontoGreaterThan(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMontoLessThan(float monto) {
        return facturaJpaRepository.findByMontoLessThan(monto);
    }
    
    @Override
    public List<FacturaDTO> findByMontoLessThanDTO(float monto) {
        List<Factura> facturas = findByMontoLessThan(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findAllByOrderByFechaDesc() {
        return facturaJpaRepository.findAllByOrderByFechaDesc();
    }
    
    @Override
    public List<FacturaDTO> findAllByOrderByFechaDescDTO() {
        List<Factura> facturas = findAllByOrderByFechaDesc();
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findAllByOrderByMontoDesc() {
        return facturaJpaRepository.findAllByOrderByMontoDesc();
    }
    
    @Override
    public List<FacturaDTO> findAllByOrderByMontoDescDTO() {
        List<Factura> facturas = findAllByOrderByMontoDesc();
        return facturaMapper.toDTOList(facturas);
    }
    
    public Float calcularTotalPorFecha(String fecha) {
        return facturaJpaRepository.calcularTotalPorFecha(fecha);
    }
    
    public Float calcularPromedioMontos() {
        return facturaJpaRepository.calcularPromedioMontos();
    }
    
    public Long contarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        return facturaJpaRepository.contarFacturasPorRangoFechas(fechaInicio, fechaFin);
    }
    
    @Override
    public Optional<FacturaDTO> updateDTO(Long id, FacturaDTO facturaDTO) {
        return findById(id)
                .map(facturaExistente -> {
                    facturaExistente.setFecha(facturaDTO.getFecha());
                    facturaExistente.setMonto(facturaDTO.getMonto());
                    
                    Factura facturaActualizada = save(facturaExistente);
                    return facturaMapper.toDTO(facturaActualizada);
                });
    }
    
    @Override
    public void deleteByIdDTO(Long id) {
        deleteById(id);
    }
    
    @Override
    public boolean existsByIdDTO(Long id) {
        return existsById(id);
    }
    
    @Override
    public Float calcularTotalPorFechaDTO(String fecha) {
        return calcularTotalPorFecha(fecha);
    }
    
    @Override
    public Float calcularPromedioMontosDTO() {
        return calcularPromedioMontos();
    }
    
    @Override
    public Long contarFacturasPorRangoFechasDTO(String fechaInicio, String fechaFin) {
        return contarFacturasPorRangoFechas(fechaInicio, fechaFin);
    }
}
