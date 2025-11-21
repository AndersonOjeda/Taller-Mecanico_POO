package com.example.uccexample.application.controller;

import com.example.uccexample.application.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin(origins = "*")
public class CarroController {
    
    @Autowired
    private CarroService carroService;
    
    @GetMapping
    public ResponseEntity<String> obtenerTodosLosCarros() {
        carroService.obtenerTodosLosCarros();
        return ResponseEntity.ok("Operación completada");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerCarroPorId(@PathVariable Long id) {
        carroService.obtenerCarroPorId(id);
        return ResponseEntity.ok("Operación completada");
    }
    
    @PostMapping
    public ResponseEntity<String> crearCarro(@RequestBody Map<String, Object> body) {
        Long clienteId = body.get("clienteId") != null ? Long.valueOf(body.get("clienteId").toString()) : null;
        String marca = body.get("marca") != null ? body.get("marca").toString() : null;
        String modelo = body.get("modelo") != null ? body.get("modelo").toString() : null;
        String tipo = body.get("tipo") != null ? body.get("tipo").toString() : null;
        carroService.crearCarro(marca, modelo, tipo, clienteId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carro creado");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCarro(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Long clienteId = body.get("clienteId") != null ? Long.valueOf(body.get("clienteId").toString()) : null;
        String marca = body.get("marca") != null ? body.get("marca").toString() : null;
        String modelo = body.get("modelo") != null ? body.get("modelo").toString() : null;
        String tipo = body.get("tipo") != null ? body.get("tipo").toString() : null;
        carroService.actualizarCarro(id, marca, modelo, tipo, clienteId);
        return ResponseEntity.ok("Carro actualizado");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCarro(@PathVariable Long id) {
        carroService.eliminarCarro(id);
        return ResponseEntity.ok("Carro eliminado exitosamente");
    }
    
    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<String> buscarCarrosPorTipo(@PathVariable String tipo) {
        carroService.buscarCarrosPorTipo(tipo);
        return ResponseEntity.ok("Operación completada");
    }
    
    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<String> buscarCarrosPorEstado(@PathVariable String estado) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/modelo/{modelo}")
    public ResponseEntity<String> buscarCarrosPorModelo(@PathVariable String modelo) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/tipo/{tipo}/modelo/{modelo}")
    public ResponseEntity<String> buscarCarrosPorTipoYModelo(
            @PathVariable String tipo, 
            @PathVariable String modelo) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/cliente/{clienteId}")
    public ResponseEntity<String> buscarCarrosPorClienteId(@PathVariable Long clienteId) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/anio/{anio}")
    public ResponseEntity<String> buscarCarrosPorAnio(@PathVariable int anio) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/buscar/anio/{anioInicio}/{anioFin}")
    public ResponseEntity<String> buscarCarrosPorRangoAnios(
            @PathVariable int anioInicio, 
            @PathVariable int anioFin) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
    
    @GetMapping("/con-cliente/tipo/{tipo}")
    public ResponseEntity<String> obtenerCarrosConClientePorTipo(@PathVariable String tipo) {
        // Método no disponible en CarroService actualizado
        return ResponseEntity.ok("Operación no disponible");
    }
}
