package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de carros
 * Expone endpoints para operaciones CRUD de carros
 */
@RestController
@RequestMapping("/api/carros")
@CrossOrigin(origins = "*")
public class CarroController {
    
    @Autowired
    private CarroService carroService;
    
    /**
     * Obtiene todos los carros
     * @return lista de carros
     */
    @GetMapping
    public ResponseEntity<List<CarroDTO>> obtenerTodosLosCarros() {
        List<CarroDTO> carros = carroService.obtenerTodosLosCarros();
        return ResponseEntity.ok(carros);
    }
    
    /**
     * Obtiene un carro por ID
     * @param id ID del carro
     * @return carro encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> obtenerCarroPorId(@PathVariable Long id) {
        Optional<CarroDTO> carro = carroService.obtenerCarroPorId(id);
        return carro.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo carro
     * @param carroDTO datos del carro
     * @return carro creado
     */
    @PostMapping
    public ResponseEntity<CarroDTO> crearCarro(@RequestBody CarroDTO carroDTO) {
        CarroDTO nuevoCarro = carroService.crearCarro(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarro);
    }
    
    /**
     * Actualiza un carro existente
     * @param id ID del carro
     * @param carroDTO datos actualizados
     * @return carro actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> actualizarCarro(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        Optional<CarroDTO> carroActualizado = carroService.actualizarCarro(id, carroDTO);
        return carroActualizado.map(ResponseEntity::ok)
                              .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Elimina un carro
     * @param id ID del carro
     * @return respuesta sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarro(@PathVariable Long id) {
        boolean eliminado = carroService.eliminarCarro(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Busca carros por tipo
     * @param tipo tipo del carro
     * @return lista de carros encontrados
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorTipo(@PathVariable String tipo) {
        List<CarroDTO> carros = carroService.buscarCarrosPorTipo(tipo);
        return ResponseEntity.ok(carros);
    }
    
    /**
     * Busca carros por estado
     * @param estado estado del carro
     * @return lista de carros encontrados
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorEstado(@PathVariable String estado) {
        List<CarroDTO> carros = carroService.buscarCarrosPorEstado(estado);
        return ResponseEntity.ok(carros);
    }
    
    /**
     * Busca carros por modelo
     * @param modelo modelo del carro
     * @return lista de carros encontrados
     */
    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<CarroDTO>> buscarCarrosPorModelo(@PathVariable String modelo) {
        List<CarroDTO> carros = carroService.buscarCarrosPorModelo(modelo);
        return ResponseEntity.ok(carros);
    }
    
    /**
     * Obtiene carros de un cliente específico
     * @param clienteId ID del cliente
     * @return lista de carros del cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CarroDTO>> obtenerCarrosPorCliente(@PathVariable Long clienteId) {
        List<CarroDTO> carros = carroService.buscarCarrosPorClienteId(clienteId);
        return ResponseEntity.ok(carros);
    }
}