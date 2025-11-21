package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.application.service.FacturaService;
import com.example.uccexample.infraestructure.modelo.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
    @GetMapping
    public ResponseEntity<List<FacturaDTO>> obtenerTodasLasFacturas() {
        return ResponseEntity.ok(facturaService.obtenerTodasLasFacturas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Long id) {
        Optional<FacturaDTO> factura = facturaService.obtenerFacturaPorId(id);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDTO) {
        try {
            Factura creada = facturaService.crearFactura(facturaDTO.getMonto(), facturaDTO.getFecha());
            facturaDTO.setIdFactura(creada.getIdFactura());
            return ResponseEntity.status(HttpStatus.CREATED).body(facturaDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizarFactura(@PathVariable Long id, @RequestBody FacturaDTO facturaDTO) {
        try {
            Optional<Factura> actualizada = facturaService.actualizarFactura(id, facturaDTO.getMonto(), facturaDTO.getFecha());
            return actualizada.map(f -> {
                facturaDTO.setIdFactura(f.getIdFactura());
                return ResponseEntity.ok(facturaDTO);
            }).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.ok("Factura eliminada exitosamente");
    }
    
    @GetMapping("/buscar/fecha/{fecha}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorFecha(@PathVariable String fecha) {
        return ResponseEntity.ok(facturaService.buscarFacturasPorFecha(fecha));
    }
    
    @GetMapping("/buscar/fecha/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorRangoFechas(
            @PathVariable String fechaInicio, 
            @PathVariable String fechaFin) {
        return ResponseEntity.ok(facturaService.buscarFacturasPorRangoFechas(fechaInicio, fechaFin));
    }
    
    @GetMapping("/buscar/monto/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorMonto(@PathVariable float monto) {
        return ResponseEntity.ok(facturaService.buscarFacturasPorMonto(monto));
    }
    
    @GetMapping("/buscar/monto/{montoInicio}/{montoFin}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorRangoMontos(
            @PathVariable float montoInicio, 
            @PathVariable float montoFin) {
        return ResponseEntity.ok(facturaService.buscarFacturasPorRangoMontos(montoInicio, montoFin));
    }
    
    @GetMapping("/buscar/monto/mayor/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasConMontoMayorA(@PathVariable float monto) {
        return ResponseEntity.ok(facturaService.buscarFacturasConMontoMayorA(monto));
    }
    
    @GetMapping("/buscar/monto/menor/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasConMontoMenorA(@PathVariable float monto) {
        return ResponseEntity.ok(facturaService.buscarFacturasConMontoMenorA(monto));
    }
    
    @GetMapping("/ordenar/fecha")
    public ResponseEntity<List<FacturaDTO>> obtenerFacturasOrdenadasPorFecha() {
        return ResponseEntity.ok(facturaService.obtenerFacturasOrdenadasPorFecha());
    }
    
    @GetMapping("/ordenar/monto")
    public ResponseEntity<List<FacturaDTO>> obtenerFacturasOrdenadasPorMonto() {
        return ResponseEntity.ok(facturaService.obtenerFacturasOrdenadasPorMonto());
    }
    
    @GetMapping("/estadisticas/total/fecha/{fecha}")
    public ResponseEntity<Float> calcularTotalPorFecha(@PathVariable String fecha) {
        return ResponseEntity.ok(facturaService.calcularTotalPorFecha(fecha));
    }
    
    @GetMapping("/estadisticas/promedio")
    public ResponseEntity<Float> calcularPromedioMontos() {
        return ResponseEntity.ok(facturaService.calcularPromedioMontos());
    }
    
    @GetMapping("/estadisticas/contar/{fechaInicio}/{fechaFin}")
    public ResponseEntity<Long> contarFacturasPorRangoFechas(
            @PathVariable String fechaInicio, 
            @PathVariable String fechaFin) {
        return ResponseEntity.ok(facturaService.contarFacturasPorRangoFechas(fechaInicio, fechaFin));
    }
}
