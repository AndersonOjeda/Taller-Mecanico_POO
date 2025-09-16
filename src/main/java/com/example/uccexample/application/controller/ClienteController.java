package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de clientes
 * Expone endpoints para operaciones CRUD de clientes
 */
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    /**
     * Obtiene todos los clientes
     * @return lista de clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }
    
    /**
     * Obtiene un cliente por ID
     * @param id ID del cliente
     * @return cliente encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteDTO> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo cliente
     * @param clienteDTO datos del cliente
     * @return cliente creado
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoCliente = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }
    
    /**
     * Actualiza un cliente existente
     * @param id ID del cliente
     * @param clienteDTO datos actualizados
     * @return cliente actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);
        return clienteActualizado.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Elimina un cliente
     * @param id ID del cliente
     * @return respuesta sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clienteService.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Busca clientes por nombre parcial
     * @param nombre parte del nombre
     * @return lista de clientes encontrados
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDTO>> buscarClientesPorNombre(@RequestParam String nombre) {
        List<ClienteDTO> clientes = clienteService.buscarClientesPorNombreParcial(nombre);
        return ResponseEntity.ok(clientes);
    }
    
    /**
     * Obtiene clientes que tienen carros
     * @return lista de clientes con carros
     */
    @GetMapping("/con-carros")
    public ResponseEntity<List<ClienteDTO>> obtenerClientesConCarros() {
        List<ClienteDTO> clientes = clienteService.obtenerClientesConCarros();
        return ResponseEntity.ok(clientes);
    }
}