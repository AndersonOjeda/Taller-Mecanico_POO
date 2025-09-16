package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.application.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de facturas
 * Expone endpoints para operaciones CRUD de facturas
 */
@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
    /**
     * Obtiene todas las facturas
     * @return lista de facturas
     */
    @GetMapping
    public ResponseEntity<List<FacturaDTO>> obtenerTodasLasFacturas() {
        List<FacturaDTO> facturas = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(facturas);
    }
    
    /**
     * Obtiene una factura por ID
     * @param id ID de la factura
     * @return factura encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Long id) {
        Optional<FacturaDTO> factura = facturaService.obtenerFacturaPorId(id);
        return factura.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea una nueva factura
     * @param facturaDTO datos de la factura
     * @return factura creada
     */
    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDTO) {
        FacturaDTO nuevaFactura = facturaService.crearFactura(facturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
    }
    
    /**
     * Actualiza una factura existente
     * @param id ID de la factura
     * @param facturaDTO datos actualizados
     * @return factura actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizarFactura(@PathVariable Long id, @RequestBody FacturaDTO facturaDTO) {
        Optional<FacturaDTO> facturaActualizada = facturaService.actualizarFactura(id, facturaDTO);
        return facturaActualizada.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Elimina una factura
     * @param id ID de la factura
     * @return respuesta sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        boolean eliminada = facturaService.eliminarFactura(id);
        if (eliminada) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Busca facturas por fecha
     * @param fecha fecha de la factura
     * @return lista de facturas encontradas
     */
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorFecha(@PathVariable String fecha) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorFecha(fecha);
        return ResponseEntity.ok(facturas);
    }
    
    /**
     * Busca facturas por rango de fechas
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de facturas encontradas
     */
    @GetMapping("/fecha-rango")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorRangoFechas(
            @RequestParam String fechaInicio, 
            @RequestParam String fechaFin) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(facturas);
    }
    
    /**
     * Busca facturas por monto específico
     * @param monto monto de la factura
     * @return lista de facturas encontradas
     */
    @GetMapping("/monto/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorMonto(@PathVariable float monto) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorMonto(monto);
        return ResponseEntity.ok(facturas);
    }
    
    /**
     * Busca facturas por rango de montos
     * @param montoMinimo monto mínimo
     * @param montoMaximo monto máximo
     * @return lista de facturas encontradas
     */
    @GetMapping("/monto-rango")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorRangoMontos(
            @RequestParam float montoMinimo, 
            @RequestParam float montoMaximo) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorRangoMontos(montoMinimo, montoMaximo);
        return ResponseEntity.ok(facturas);
    }
    
    /**
     * Busca facturas con monto mayor al especificado
     * @param monto monto mínimo
     * @return lista de facturas encontradas
     */
    @GetMapping("/monto-mayor/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasConMontoMayor(@PathVariable float monto) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasConMontoMayorA(monto);
        return ResponseEntity.ok(facturas);
    }
}