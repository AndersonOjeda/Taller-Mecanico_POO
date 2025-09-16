package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.infraestructure.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Factura
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
    /**
     * Busca facturas por fecha específica
     * @param fecha la fecha de la factura
     * @return lista de facturas de esa fecha
     */
    List<Factura> findByFecha(String fecha);
    
    /**
     * Busca facturas por rango de fechas
     * @param fechaInicio fecha de inicio del rango
     * @param fechaFin fecha de fin del rango
     * @return lista de facturas en ese rango
     */
    List<Factura> findByFechaBetween(String fechaInicio, String fechaFin);
    
    /**
     * Busca facturas por monto específico
     * @param monto el monto de la factura
     * @return lista de facturas con ese monto
     */
    List<Factura> findByMonto(float monto);
    
    /**
     * Busca facturas por rango de montos
     * @param montoMinimo monto mínimo
     * @param montoMaximo monto máximo
     * @return lista de facturas en ese rango de montos
     */
    List<Factura> findByMontoBetween(float montoMinimo, float montoMaximo);
    
    /**
     * Busca facturas con monto mayor al especificado
     * @param monto monto mínimo
     * @return lista de facturas con monto mayor
     */
    List<Factura> findByMontoGreaterThan(float monto);
    
    /**
     * Busca facturas con monto menor al especificado
     * @param monto monto máximo
     * @return lista de facturas con monto menor
     */
    List<Factura> findByMontoLessThan(float monto);
    
    /**
     * Busca facturas ordenadas por fecha descendente
     * @return lista de facturas ordenadas por fecha
     */
    List<Factura> findAllByOrderByFechaDesc();
    
    /**
     * Busca facturas ordenadas por monto descendente
     * @return lista de facturas ordenadas por monto
     */
    List<Factura> findAllByOrderByMontoDesc();
    
    /**
     * Consulta personalizada para calcular el total de facturas por fecha
     * @param fecha la fecha a consultar
     * @return suma total de montos para esa fecha
     */
    @Query("SELECT SUM(f.monto) FROM Factura f WHERE f.fecha = :fecha")
    Float calcularTotalPorFecha(@Param("fecha") String fecha);
    
    /**
     * Consulta personalizada para obtener el promedio de montos
     * @return promedio de todos los montos
     */
    @Query("SELECT AVG(f.monto) FROM Factura f")
    Float calcularPromedioMontos();
    
    /**
     * Consulta personalizada para contar facturas por rango de fechas
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return número de facturas en el rango
     */
    @Query("SELECT COUNT(f) FROM Factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin")
    Long contarFacturasPorRangoFechas(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}