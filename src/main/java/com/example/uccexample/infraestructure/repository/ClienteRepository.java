package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.application.mapper.ClienteMapper;
import com.example.uccexample.application.repository.IClienteRepository;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.infraestructure.repository.jpa.ClienteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio concreto de Cliente que implementa la interfaz IClienteRepository
 * Inyecta ClienteJpa para operaciones de base de datos
 */
@Repository
public class ClienteRepository implements IClienteRepository {

    @Autowired
    private ClienteJpaRepository clienteJpaRepository;

    @Autowired
    private ClienteMapper clienteMapper;
    
    @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll();
    }
    
    @Override
    public List<ClienteDTO> findAllDTO() {
        List<Cliente> clientes = findAll();
        return clienteMapper.toDTOList(clientes);
    }
    
    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id);
    }
    
    @Override
    public Optional<ClienteDTO> findByIdDTO(Long id) {
        Optional<Cliente> cliente = findById(id);
        return cliente.map(clienteMapper::toDTO);
    }
    
    @Override
    public Cliente save(Cliente cliente) {
        return clienteJpaRepository.save(cliente);
    }
    
    @Override
    public ClienteDTO saveDTO(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }
    
    @Override
    public void deleteById(Long id) {
        clienteJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return clienteJpaRepository.existsById(id);
    }
    
    @Override
    public Optional<Cliente> findByNombre(String nombre) {
        return clienteJpaRepository.findByNombre(nombre);
    }
    
    @Override
    public Optional<ClienteDTO> findByNombreDTO(String nombre) {
        Optional<Cliente> cliente = findByNombre(nombre);
        return cliente.map(clienteMapper::toDTO);
    }
    
    @Override
    public List<Cliente> findByNombreContainingIgnoreCase(String nombre) {
        return clienteJpaRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    public List<ClienteDTO> findByNombreContainingIgnoreCaseDTO(String nombre) {
        List<Cliente> clientes = findByNombreContainingIgnoreCase(nombre);
        return clienteMapper.toDTOList(clientes);
    }
    
    @Override
    public List<Cliente> findClientesConCarros() {
        return clienteJpaRepository.findClientesConCarros();
    }
    
    @Override
    public List<ClienteDTO> findClientesConCarrosDTO() {
        List<Cliente> clientes = findClientesConCarros();
        return clienteMapper.toDTOList(clientes);
    }
    
    @Override
    public Long countCarrosByClienteId(Long clienteId) {
        return clienteJpaRepository.countCarrosByClienteId(clienteId);
    }
    
    @Override
    public Optional<ClienteDTO> updateDTO(Long id, ClienteDTO clienteDTO) {
        return findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteDTO.getNombre());
                    clienteExistente.setPresupuesto(clienteDTO.getPresupuesto());
                    
                    Cliente clienteActualizado = save(clienteExistente);
                    return clienteMapper.toDTO(clienteActualizado);
                });
    }
    
    @Override
    public void deleteByIdDTO(Long id) {
        deleteById(id);
    }
    
    @Override
    public boolean existsByIdDTO(Long id) {
        return existsById(id);
    }
    
    @Override
    public Long countCarrosByClienteIdDTO(Long clienteId) {
        return countCarrosByClienteId(clienteId);
    }
}
