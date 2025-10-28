package com.example.uccexample.application.contract;

import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.application.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Contrato (interfaz) CRUD personalizada para Cliente
 * Define las operaciones de negocio específicas para Cliente
 */
public interface IClienteContract {
    
    // Operaciones CRUD básicas
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
    void delete(Cliente cliente);
    
    // Operaciones específicas de negocio
    Optional<Cliente> findByNombre(String nombre);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    List<Cliente> findClientesConCarros();
    Long countCarrosByClienteId(Long clienteId);
    List<Cliente> findByPresupuestoGreaterThanEqual(float presupuestoMinimo);
    
    // Operaciones con DTOs
    List<ClienteDTO> findAllDTO();
    Optional<ClienteDTO> findByIdDTO(Long id);
    ClienteDTO saveDTO(ClienteDTO clienteDTO);
    Optional<ClienteDTO> updateDTO(Long id, ClienteDTO clienteDTO);
}