package com.example.uccexample.application.repository;

import java.util.List;
import java.util.Optional;

import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.infraestructure.modelo.Cliente;


public interface IClienteRepository {
    
    List<Cliente> findAll();
    
    Optional<Cliente> findById(Long id);
    
    Cliente save(Cliente cliente);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
    
    Optional<Cliente> findByNombre(String nombre);
    
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    
    List<Cliente> findClientesConCarros();
    
    Long countCarrosByClienteId(Long clienteId);
    

    List<ClienteDTO> findAllDTO();
    
    Optional<ClienteDTO> findByIdDTO(Long id);
    
    ClienteDTO saveDTO(ClienteDTO clienteDTO);
    
    void deleteByIdDTO(Long id);
    
    boolean existsByIdDTO(Long id);
    
    Optional<ClienteDTO> findByNombreDTO(String nombre);
    
    List<ClienteDTO> findByNombreContainingIgnoreCaseDTO(String nombre);
    
    List<ClienteDTO> findClientesConCarrosDTO();
    
    Long countCarrosByClienteIdDTO(Long clienteId);
    
    Optional<ClienteDTO> updateDTO(Long id, ClienteDTO clienteDTO);
}