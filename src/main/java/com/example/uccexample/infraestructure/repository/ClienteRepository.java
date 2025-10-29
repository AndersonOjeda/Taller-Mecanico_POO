package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.application.repository.IClienteRepository;
import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.application.mapper.ClienteMapper;
import com.example.uccexample.infraestructure.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio concreto de Cliente que implementa la interfaz IClienteRepository
 * Inyecta ClienteJpa para operaciones de base de datos
 */
@Repository
public class ClienteRepository implements IClienteRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private ClienteMapper clienteMapper;
    
    @Override
    public List<Cliente> findAll() {
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }
    
    @Override
    public List<ClienteDTO> findAllDTO() {
        List<Cliente> clientes = findAll();
        return clienteMapper.toDTOList(clientes);
    }
    
    @Override
    public Optional<Cliente> findById(Long id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        return Optional.ofNullable(cliente);
    }
    
    @Override
    public Optional<ClienteDTO> findByIdDTO(Long id) {
        Optional<Cliente> cliente = findById(id);
        return cliente.map(clienteMapper::toDTO);
    }
    
    @Override
    public Cliente save(Cliente cliente) {
        if (cliente.getIdCliente() == null) {
            entityManager.persist(cliente);
            return cliente;
        } else {
            return entityManager.merge(cliente);
        }
    }
    
    @Override
    public ClienteDTO saveDTO(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }
    
    @Override
    public void deleteById(Long id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        if (cliente != null) {
            entityManager.remove(cliente);
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        return cliente != null;
    }
    
    @Override
    public Optional<Cliente> findByNombre(String nombre) {
        TypedQuery<Cliente> query = entityManager.createQuery(
            "SELECT c FROM Cliente c WHERE c.nombre = :nombre", Cliente.class);
        query.setParameter("nombre", nombre);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    @Override
    public Optional<ClienteDTO> findByNombreDTO(String nombre) {
        Optional<Cliente> cliente = findByNombre(nombre);
        return cliente.map(clienteMapper::toDTO);
    }
    
    @Override
    public List<Cliente> findByNombreContainingIgnoreCase(String nombre) {
        TypedQuery<Cliente> query = entityManager.createQuery(
            "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)", Cliente.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }
    
    @Override
    public List<ClienteDTO> findByNombreContainingIgnoreCaseDTO(String nombre) {
        List<Cliente> clientes = findByNombreContainingIgnoreCase(nombre);
        return clienteMapper.toDTOList(clientes);
    }
    
    @Override
    public List<Cliente> findClientesConCarros() {
        TypedQuery<Cliente> query = entityManager.createQuery(
            "SELECT DISTINCT c FROM Cliente c JOIN c.carros", Cliente.class);
        return query.getResultList();
    }
    
    @Override
    public List<ClienteDTO> findClientesConCarrosDTO() {
        List<Cliente> clientes = findClientesConCarros();
        return clienteMapper.toDTOList(clientes);
    }
    
    @Override
    public Long countCarrosByClienteId(Long clienteId) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(car) FROM Cliente c JOIN c.carros car WHERE c.idCliente = :clienteId", Long.class);
        query.setParameter("clienteId", clienteId);
        return query.getSingleResult();
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