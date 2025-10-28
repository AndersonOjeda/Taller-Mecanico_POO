package com.example.uccexample.application.controller;

import com.example.uccexample.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<String> obtenerTodosLosClientes() {
        clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok("Operación completada");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerClientePorId(@PathVariable Long id) {
        clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok("Operación completada");
    }
    
    @PostMapping
    public ResponseEntity<String> crearCliente(@RequestParam String nombre, @RequestParam String email) {
        clienteService.crearCliente(nombre, email);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id, @RequestParam String nombre, @RequestParam String email) {
        clienteService.actualizarCliente(id, nombre, email);
        return ResponseEntity.ok("Cliente actualizado");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clienteService.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}