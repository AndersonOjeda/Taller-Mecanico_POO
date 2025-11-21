package com.example.uccexample.application.controller;

import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.application.service.ClienteService;
import com.example.uccexample.infraestructure.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientesDTO();
        return ResponseEntity.ok(clientes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteDTO> cliente = clienteService.obtenerClientePorIdDTO(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO body) {
        Cliente creado = clienteService.crearClienteDesdeDTO(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(creado));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO body) {
        Optional<Cliente> actualizado = clienteService.actualizarClienteDesdeDTO(id, body);
        return actualizado.map(cliente -> ResponseEntity.ok(toDto(cliente)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clienteService.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombre(cliente.getNombre());
        dto.setPresupuesto(cliente.getPresupuesto());
        dto.setEmail(cliente.getEmail());
        dto.setCarros(null); // evitar carga perezosa; el GET usa mapper DTO
        return dto;
    }
}
