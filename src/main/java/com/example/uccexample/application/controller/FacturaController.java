package com.example.uccexample.application.controller;

import com.example.uccexample.application.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
    @GetMapping
    public ResponseEntity<String> obtenerTodasLasFacturas() {
        facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok("Operación completada");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerFacturaPorId(@PathVariable Long id) {
        facturaService.obtenerFacturaPorId(id);
        return ResponseEntity.ok("Operación completada");
    }
    
    @PostMapping
    public ResponseEntity<String> crearFactura(@RequestParam float monto, @RequestParam String fecha) {
        try {
            facturaService.crearFactura(monto, fecha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Factura creada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarFactura(@PathVariable Long id, @RequestParam float monto, @RequestParam String fecha) {
        try {
            facturaService.actualizarFactura(id, monto, fecha);
            return ResponseEntity.ok("Factura actualizada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.ok("Factura eliminada exitosamente");
    }
    
    @GetMapping("/buscar/fecha/{fecha}")
    public ResponseEntity<String> buscarFacturasPorFecha(@PathVariable String fecha) {
        facturaService.buscarFacturasPorFecha(fecha);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/fecha/{fechaInicio}/{fechaFin}")
    public ResponseEntity<String> buscarFacturasPorRangoFechas(
            @PathVariable String fechaInicio, 
            @PathVariable String fechaFin) {
        facturaService.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/monto/{monto}")
    public ResponseEntity<String> buscarFacturasPorMonto(@PathVariable float monto) {
        facturaService.buscarFacturasPorMonto(monto);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/monto/{montoInicio}/{montoFin}")
    public ResponseEntity<String> buscarFacturasPorRangoMontos(
            @PathVariable float montoInicio, 
            @PathVariable float montoFin) {
        facturaService.buscarFacturasPorRangoMontos(montoInicio, montoFin);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/monto/mayor/{monto}")
    public ResponseEntity<String> buscarFacturasConMontoMayorA(@PathVariable float monto) {
        facturaService.buscarFacturasConMontoMayorA(monto);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/monto/menor/{monto}")
    public ResponseEntity<String> buscarFacturasConMontoMenorA(@PathVariable float monto) {
        facturaService.buscarFacturasConMontoMenorA(monto);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/ordenar/fecha")
    public ResponseEntity<String> obtenerFacturasOrdenadasPorFecha() {
        facturaService.obtenerFacturasOrdenadasPorFecha();
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/ordenar/monto")
    public ResponseEntity<String> obtenerFacturasOrdenadasPorMonto() {
        facturaService.obtenerFacturasOrdenadasPorMonto();
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/estadisticas/total/fecha/{fecha}")
    public ResponseEntity<String> calcularTotalPorFecha(@PathVariable String fecha) {
        facturaService.calcularTotalPorFecha(fecha);
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/estadisticas/promedio")
    public ResponseEntity<String> calcularPromedioMontos() {
        facturaService.calcularPromedioMontos();
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/estadisticas/contar/{fechaInicio}/{fechaFin}")
    public ResponseEntity<String> contarFacturasPorRangoFechas(
            @PathVariable String fechaInicio, 
            @PathVariable String fechaFin) {
        facturaService.contarFacturasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok("Operación no disponible");
    }
}
