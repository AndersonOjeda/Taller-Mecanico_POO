package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.service.CarroService;
import com.example.uccexample.infraestructure.modelo.Carro;
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
        return ResponseEntity.ok(carroService.obtenerTodosLosCarrosDTO());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> obtenerCarroPorId(@PathVariable Long id) {
        Optional<CarroDTO> carro = carroService.obtenerCarroPorIdDTO(id);
        return carro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CarroDTO> crearCarro(@RequestBody CarroDTO body) {
        Carro creado = carroService.crearCarroDesdeDTO(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(creado));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> actualizarCarro(@PathVariable Long id, @RequestBody CarroDTO body) {
        Optional<Carro> actualizado = carroService.actualizarCarroDesdeDTO(id, body);
        return actualizado.map(carro -> ResponseEntity.ok(toDto(carro)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarro(@PathVariable Long id) {
        carroService.eliminarCarro(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(carroService.buscarCarrosPorTipoDTO(tipo));
    }
    
    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(carroService.buscarCarrosPorEstadoDTO(estado));
    }
    
    @GetMapping("/buscar/modelo/{modelo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(carroService.buscarCarrosPorModeloDTO(modelo));
    }
    
    @GetMapping("/buscar/tipo/{tipo}/modelo/{modelo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorTipoYModelo(
            @PathVariable String tipo, 
            @PathVariable String modelo) {
        return ResponseEntity.ok(carroService.buscarCarrosPorTipoYModeloDTO(tipo, modelo));
    }
    
    @GetMapping("/buscar/cliente/{clienteId}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(carroService.buscarCarrosPorClienteIdDTO(clienteId));
    }
    
    @GetMapping("/buscar/anio/{anio}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorAnio(@PathVariable int anio) {
        return ResponseEntity.ok(carroService.buscarCarrosPorAnioDTO(anio));
    }
    
    @GetMapping("/buscar/anio/{anioInicio}/{anioFin}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorRangoAnios(
            @PathVariable int anioInicio, 
            @PathVariable int anioFin) {
        return ResponseEntity.ok(carroService.buscarCarrosPorRangoAnioDTO(anioInicio, anioFin));
    }
    
    @GetMapping("/con-cliente/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> obtenerCarrosConClientePorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(carroService.obtenerCarrosConClientePorTipoDTO(tipo));
    }

    private CarroDTO toDto(Carro carro) {
        CarroDTO dto = new CarroDTO();
        dto.setIdAuto(carro.getIdAuto());
        dto.setModelo(carro.getModelo());
        dto.setTipo(carro.getTipo());
        dto.setEstado(carro.getEstado());
        dto.setAnio(carro.getAnio());
        dto.setCostoDano(carro.getCostoDano());
        dto.setClienteId(carro.getCliente() != null ? carro.getCliente().getIdCliente() : null);
        return dto;
    }
}
