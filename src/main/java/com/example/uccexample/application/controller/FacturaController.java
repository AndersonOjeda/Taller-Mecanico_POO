package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.application.service.FacturaService;
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
        List<FacturaDTO> facturas = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Long id) {
        Optional<FacturaDTO> factura = facturaService.obtenerFacturaPorId(id);
        return factura.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDTO) {
        try {
            FacturaDTO nuevaFactura = facturaService.crearFactura(facturaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizarFactura(@PathVariable Long id, @RequestBody FacturaDTO facturaDTO) {
        Optional<FacturaDTO> facturaActualizada = facturaService.actualizarFactura(id, facturaDTO);
        return facturaActualizada.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        boolean eliminado = facturaService.eliminarFactura(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/buscar/fecha/{fecha}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorFecha(@PathVariable String fecha) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorFecha(fecha);
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/buscar/fecha/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorRangoFechas(
            @PathVariable String fechaInicio, 
            @PathVariable String fechaFin) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/buscar/monto/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorMonto(@PathVariable float monto) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorMonto(monto);
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/buscar/monto/{montoInicio}/{montoFin}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasPorRangoMontos(
            @PathVariable float montoInicio, 
            @PathVariable float montoFin) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasPorRangoMontos(montoInicio, montoFin);
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/buscar/monto/mayor/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasConMontoMayorA(@PathVariable float monto) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasConMontoMayorA(monto);
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/buscar/monto/menor/{monto}")
    public ResponseEntity<List<FacturaDTO>> buscarFacturasConMontoMenorA(@PathVariable float monto) {
        List<FacturaDTO> facturas = facturaService.buscarFacturasConMontoMenorA(monto);
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/ordenar/fecha")
    public ResponseEntity<List<FacturaDTO>> obtenerFacturasOrdenadasPorFecha() {
        List<FacturaDTO> facturas = facturaService.obtenerFacturasOrdenadasPorFecha();
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/ordenar/monto")
    public ResponseEntity<List<FacturaDTO>> obtenerFacturasOrdenadasPorMonto() {
        List<FacturaDTO> facturas = facturaService.obtenerFacturasOrdenadasPorMonto();
        return ResponseEntity.ok(facturas);
    }
    
    @GetMapping("/estadisticas/total/fecha/{fecha}")
    public ResponseEntity<Float> calcularTotalPorFecha(@PathVariable String fecha) {
        Float total = facturaService.calcularTotalPorFecha(fecha);
        return ResponseEntity.ok(total);
    }
    
    @GetMapping("/estadisticas/promedio")
    public ResponseEntity<Float> calcularPromedioMontos() {
        Float promedio = facturaService.calcularPromedioMontos();
        return ResponseEntity.ok(promedio);
    }
    
    @GetMapping("/estadisticas/contar/{fechaInicio}/{fechaFin}")
    public ResponseEntity<Long> contarFacturasPorRangoFechas(
            @PathVariable String fechaInicio, 
            @PathVariable String fechaFin) {
        Long cantidad = facturaService.contarFacturasPorRangoFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(cantidad);
    }
}
