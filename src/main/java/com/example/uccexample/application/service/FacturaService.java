package com.example.uccexample.application.service;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.application.mapper.FacturaMapper;
import com.example.uccexample.infraestructure.modelo.Factura;
import com.example.uccexample.infraestructure.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de facturas
 * Utiliza el patrón de capas para separar la lógica de negocio
 */
@Service
@Transactional
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    @Autowired
    private FacturaMapper facturaMapper;
    
    /**
     * Obtiene todas las facturas
     * @return lista de DTOs de facturas
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaRepository.findAll();
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Obtiene una factura por su ID
     * @param id el ID de la factura
     * @return Optional con el DTO de la factura si existe
     */
    @Transactional(readOnly = true)
    public Optional<FacturaDTO> obtenerFacturaPorId(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.map(facturaMapper::toDTO);
    }
    
    /**
     * Busca facturas por fecha específica
     * @param fecha la fecha de la factura
     * @return lista de DTOs de facturas de esa fecha
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> buscarFacturasPorFecha(String fecha) {
        List<Factura> facturas = facturaRepository.findByFecha(fecha);
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Busca facturas por rango de fechas
     * @param fechaInicio fecha de inicio del rango
     * @param fechaFin fecha de fin del rango
     * @return lista de DTOs de facturas en ese rango
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> buscarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        List<Factura> facturas = facturaRepository.findByFechaBetween(fechaInicio, fechaFin);
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Busca facturas por monto específico
     * @param monto el monto de la factura
     * @return lista de DTOs de facturas con ese monto
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> buscarFacturasPorMonto(float monto) {
        List<Factura> facturas = facturaRepository.findByMonto(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Busca facturas por rango de montos
     * @param montoMinimo monto mínimo
     * @param montoMaximo monto máximo
     * @return lista de DTOs de facturas en ese rango
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> buscarFacturasPorRangoMontos(float montoMinimo, float montoMaximo) {
        List<Factura> facturas = facturaRepository.findByMontoBetween(montoMinimo, montoMaximo);
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Busca facturas con monto mayor al especificado
     * @param monto monto mínimo
     * @return lista de DTOs de facturas con monto mayor
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> buscarFacturasConMontoMayorA(float monto) {
        List<Factura> facturas = facturaRepository.findByMontoGreaterThan(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Busca facturas con monto menor al especificado
     * @param monto monto máximo
     * @return lista de DTOs de facturas con monto menor
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> buscarFacturasConMontoMenorA(float monto) {
        List<Factura> facturas = facturaRepository.findByMontoLessThan(monto);
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Obtiene facturas ordenadas por fecha descendente
     * @return lista de DTOs de facturas ordenadas por fecha
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerFacturasOrdenadasPorFecha() {
        List<Factura> facturas = facturaRepository.findAllByOrderByFechaDesc();
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Obtiene facturas ordenadas por monto descendente
     * @return lista de DTOs de facturas ordenadas por monto
     */
    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerFacturasOrdenadasPorMonto() {
        List<Factura> facturas = facturaRepository.findAllByOrderByMontoDesc();
        return facturaMapper.toDTOList(facturas);
    }
    
    /**
     * Crea una nueva factura
     * @param facturaDTO el DTO de la factura a crear
     * @return el DTO de la factura creada
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public FacturaDTO crearFactura(FacturaDTO facturaDTO) {
        // Validaciones básicas
        if (facturaDTO.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        
        if (facturaDTO.getFecha() == null || facturaDTO.getFecha().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        
        Factura factura = facturaMapper.toEntity(facturaDTO);
        Factura facturaGuardada = facturaRepository.save(factura);
        return facturaMapper.toDTO(facturaGuardada);
    }
    
    /**
     * Actualiza una factura existente
     * @param id el ID de la factura a actualizar
     * @param facturaDTO el DTO con los nuevos datos
     * @return Optional con el DTO de la factura actualizada
     */
    public Optional<FacturaDTO> actualizarFactura(Long id, FacturaDTO facturaDTO) {
        return facturaRepository.findById(id)
                .map(facturaExistente -> {
                    // Actualizar campos
                    facturaExistente.setFecha(facturaDTO.getFecha());
                    facturaExistente.setMonto(facturaDTO.getMonto());
                    
                    Factura facturaActualizada = facturaRepository.save(facturaExistente);
                    return facturaMapper.toDTO(facturaActualizada);
                });
    }
    
    /**
     * Elimina una factura por su ID
     * @param id el ID de la factura a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarFactura(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    /**
     * Calcula el total de facturas por fecha
     * @param fecha la fecha a consultar
     * @return suma total de montos para esa fecha
     */
    @Transactional(readOnly = true)
    public Float calcularTotalPorFecha(String fecha) {
        Float total = facturaRepository.calcularTotalPorFecha(fecha);
        return total != null ? total : 0.0f;
    }
    
    /**
     * Calcula el promedio de montos de todas las facturas
     * @return promedio de todos los montos
     */
    @Transactional(readOnly = true)
    public Float calcularPromedioMontos() {
        Float promedio = facturaRepository.calcularPromedioMontos();
        return promedio != null ? promedio : 0.0f;
    }
    
    /**
     * Cuenta facturas por rango de fechas
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return número de facturas en el rango
     */
    @Transactional(readOnly = true)
    public Long contarFacturasPorRangoFechas(String fechaInicio, String fechaFin) {
        return facturaRepository.contarFacturasPorRangoFechas(fechaInicio, fechaFin);
    }
}