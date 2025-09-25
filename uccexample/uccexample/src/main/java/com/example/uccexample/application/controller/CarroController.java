package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin(origins = "*")
public class CarroController {
    
    @Autowired
    private CarroService carroService;
    
    @GetMapping
    public ResponseEntity<List<CarroDTO>> obtenerTodosLosCarros() {
        List<CarroDTO> carros = carroService.obtenerTodosLosCarros();
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> obtenerCarroPorId(@PathVariable Long id) {
        Optional<CarroDTO> carro = carroService.obtenerCarroPorId(id);
        return carro.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CarroDTO> crearCarro(@RequestBody CarroDTO carroDTO) {
        CarroDTO nuevoCarro = carroService.crearCarro(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarro);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> actualizarCarro(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        Optional<CarroDTO> carroActualizado = carroService.actualizarCarro(id, carroDTO);
        return carroActualizado.map(ResponseEntity::ok)
                              .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarro(@PathVariable Long id) {
        boolean eliminado = carroService.eliminarCarro(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorTipo(@PathVariable String tipo) {
        List<CarroDTO> carros = carroService.buscarCarrosPorTipo(tipo);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorEstado(@PathVariable String estado) {
        List<CarroDTO> carros = carroService.buscarCarrosPorEstado(estado);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/buscar/modelo/{modelo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorModelo(@PathVariable String modelo) {
        List<CarroDTO> carros = carroService.buscarCarrosPorModelo(modelo);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/buscar/tipo/{tipo}/modelo/{modelo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorTipoYModelo(
            @PathVariable String tipo, 
            @PathVariable String modelo) {
        List<CarroDTO> carros = carroService.buscarCarrosPorTipoYModelo(tipo, modelo);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/buscar/cliente/{clienteId}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorClienteId(@PathVariable Long clienteId) {
        List<CarroDTO> carros = carroService.buscarCarrosPorClienteId(clienteId);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/buscar/anio/{anio}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorAnio(@PathVariable int anio) {
        List<CarroDTO> carros = carroService.buscarCarrosPorAnio(anio);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/buscar/anio/{anioInicio}/{anioFin}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorRangoAnios(
            @PathVariable int anioInicio, 
            @PathVariable int anioFin) {
        List<CarroDTO> carros = carroService.buscarCarrosPorRangoAnios(anioInicio, anioFin);
        return ResponseEntity.ok(carros);
    }
    
    @GetMapping("/con-cliente/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> obtenerCarrosConClientePorTipo(@PathVariable String tipo) {
        List<CarroDTO> carros = carroService.obtenerCarrosConClientePorTipo(tipo);
        return ResponseEntity.ok(carros);
    }
}