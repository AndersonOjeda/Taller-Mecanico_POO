package com.example.uccexample.application.service;

import com.example.uccexample.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Transactional(readOnly = true)
    public void obtenerTodosLosClientes() {
        clienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public void obtenerClientePorId(Long id) {
        clienteRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public void buscarClientePorNombre(String nombre) {
        clienteRepository.findByNombre(nombre);
    }
    
    @Transactional(readOnly = true)
    public void buscarClientesPorNombreParcial(String nombre) {
        clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public void crearCliente(String nombre, String email) {
        // Solo operaciones de repositorio
    }
    
    public void actualizarCliente(Long id, String nombre, String email) {
        clienteRepository.findById(id);
    }
    
    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = true)
    public void obtenerClientesConCarros() {
        clienteRepository.findClientesConCarros();
    }
    
    @Transactional(readOnly = true)
    public Long contarCarrosDelCliente(Long clienteId) {
        return clienteRepository.countCarrosByClienteId(clienteId);
    }
}