package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.application.mapper.FacturaMapper;
import com.example.uccexample.infraestructure.modelo.Factura;
import com.example.uccexample.application.repository.IFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio para la entidad Factura
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public class FacturaRepository implements IFacturaRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private FacturaMapper facturaMapper;
    
    @Override
    public List<Factura> findAll() {
        return entityManager.createQuery("SELECT f FROM Factura f", Factura.class).getResultList();
    }
    
    @Override
    public List<FacturaDTO> findAllDTO() {
        List<Factura> facturas = findAll();
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public Optional<Factura> findById(Long id) {
        Factura factura = entityManager.find(Factura.class, id);
        return Optional.ofNullable(factura);
    }
    
    @Override
    public Optional<FacturaDTO> findByIdDTO(Long id) {
        Optional<Factura> factura = findById(id);
        return factura.map(facturaMapper::toDTO);
    }
    
    @Override
    public Factura save(Factura factura) {
        if (factura.getIdFactura() == null) {
            entityManager.persist(factura);
            return factura;
        } else {
            return entityManager.merge(factura);
        }
    }
    
    @Override
    public FacturaDTO saveDTO(FacturaDTO facturaDTO) {
        Factura factura = facturaMapper.toEntity(facturaDTO);
        Factura facturaGuardada = save(factura);
        return facturaMapper.toDTO(facturaGuardada);
    }
    
    @Override
    public void deleteById(Long id) {
        Factura factura = entityManager.find(Factura.class, id);
        if (factura != null) {
            entityManager.remove(factura);
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        Long count = entityManager.createQuery("SELECT COUNT(f) FROM Factura f WHERE f.idFactura = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }
    
    @Override
    public List<Factura> findByFecha(String fecha) {
        return entityManager.createQuery("SELECT f FROM Factura f WHERE f.fecha = :fecha", Factura.class)
                .setParameter("fecha", fecha)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findByFechaDTO(String fecha) {
        List<Factura> facturas = findByFecha(fecha);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByFechaBetween(String fechaInicio, String fechaFin) {
        return entityManager.createQuery("SELECT f FROM Factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin", Factura.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findByFechaBetweenDTO(String fechaInicio, String fechaFin) {
        List<Factura> facturas = findByFechaBetween(fechaInicio, fechaFin);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMonto(float monto) {
        return entityManager.createQuery("SELECT f FROM Factura f WHERE f.monto = :monto", Factura.class)
                .setParameter("monto", monto)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findByMontoDTO(float monto) {
        List<Factura> facturas = findByMonto(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMontoBetween(float montoMinimo, float montoMaximo) {
        return entityManager.createQuery("SELECT f FROM Factura f WHERE f.monto BETWEEN :montoMinimo AND :montoMaximo", Factura.class)
                .setParameter("montoMinimo", montoMinimo)
                .setParameter("montoMaximo", montoMaximo)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findByMontoBetweenDTO(float montoMinimo, float montoMaximo) {
        List<Factura> facturas = findByMontoBetween(montoMinimo, montoMaximo);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMontoGreaterThan(float monto) {
        return entityManager.createQuery("SELECT f FROM Factura f WHERE f.monto > :monto", Factura.class)
                .setParameter("monto", monto)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findByMontoGreaterThanDTO(float monto) {
        List<Factura> facturas = findByMontoGreaterThan(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findByMontoLessThan(float monto) {
        return entityManager.createQuery("SELECT f FROM Factura f WHERE f.monto < :monto", Factura.class)
                .setParameter("monto", monto)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findByMontoLessThanDTO(float monto) {
        List<Factura> facturas = findByMontoLessThan(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findAllByOrderByFechaDesc() {
        return entityManager.createQuery("SELECT f FROM Factura f ORDER BY f.fecha DESC", Factura.class)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findAllByOrderByFechaDescDTO() {
        List<Factura> facturas = findAllByOrderByFechaDesc();
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public List<Factura> findAllByOrderByMontoDesc() {
        return entityManager.createQuery("SELECT f FROM Factura f ORDER BY f.monto DESC", Factura.class)
                .getResultList();
    }
    
    @Override
    public List<FacturaDTO> findAllByOrderByMontoDescDTO() {
        List<Factura> facturas = findAllByOrderByMontoDesc();
        return facturaMapper.toDTOList(facturas);
    }
    
    @Override
    public Float calcularTotalPorFecha(String fecha) {
        return entityManager.createQuery("SELECT SUM(f.monto) FROM Factura f WHERE f.fecha = :fecha", Float.class)
                .setParameter("fecha", fecha)
                .getSingleResult();
    }
    
    @Override
    public Float calcularPromedioMontos() {
        return entityManager.createQuery("SELECT AVG(f.monto) FROM Factura f", Float.class)
                .getSingleResult();
    }
    
    @Override
    public Long contarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        return entityManager.createQuery("SELECT COUNT(f) FROM Factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin", Long.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getSingleResult();
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
}