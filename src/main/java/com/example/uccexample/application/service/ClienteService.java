package com.example.uccexample.application.service;

import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarClientePorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> buscarClientesPorNombreParcial(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public void crearCliente(String nombre, String email) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        // Nota: email no está en el modelo Cliente, solo se usa nombre
        clienteRepository.save(cliente);
    }
    
    public void actualizarCliente(Long id, String nombre, String email) {
        clienteRepository.findById(id)
                .ifPresent(clienteExistente -> {
                    clienteExistente.setNombre(nombre);
                    // Nota: email no está en el modelo Cliente, solo se usa nombre
                    clienteRepository.save(clienteExistente);
                });
    }
    
    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerClientesConCarros() {
        return clienteRepository.findClientesConCarros();
    }
    
    @Transactional(readOnly = true)
    public Long contarCarrosDelCliente(Long clienteId) {
        return clienteRepository.countCarrosByClienteId(clienteId);
    }
    
    @Transactional(readOnly = true)
    public boolean existeCliente(Long id) {
        return clienteRepository.existsById(id);
    }
}