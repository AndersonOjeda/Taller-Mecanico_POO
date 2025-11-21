package com.example.uccexample.infraestructure.repository.jpa;

import com.example.uccexample.infraestructure.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacturaJpaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findByFecha(String fecha);

    List<Factura> findByFechaBetween(String fechaInicio, String fechaFin);

    List<Factura> findByMonto(float monto);

    List<Factura> findByMontoBetween(float montoMinimo, float montoMaximo);

    List<Factura> findByMontoGreaterThan(float monto);

    List<Factura> findByMontoLessThan(float monto);

    List<Factura> findAllByOrderByFechaDesc();

    List<Factura> findAllByOrderByMontoDesc();

    @Query("SELECT SUM(f.monto) FROM Factura f WHERE f.fecha = :fecha")
    Float calcularTotalPorFecha(@Param("fecha") String fecha);

    @Query("SELECT AVG(f.monto) FROM Factura f")
    Float calcularPromedioMontos();

    @Query("SELECT COUNT(f) FROM Factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin")
    Long contarFacturasPorRangoFechas(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}
