package com.example.uccexample.application.service;

import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.application.mapper.ClienteMapper;
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
    
    @Autowired
    private ClienteMapper clienteMapper;
    
    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }
    
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::toDTO);
    }
    
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> buscarClientePorNombre(String nombre) {
        Optional<Cliente> cliente = clienteRepository.findByNombre(nombre);
        return cliente.map(clienteMapper::toDTO);
    }
    
    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarClientesPorNombreParcial(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombreContainingIgnoreCase(nombre);
        return clienteMapper.toDTOList(clientes);
    }
    
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }
    
    public Optional<ClienteDTO> actualizarCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteDTO.getNombre());
                    clienteExistente.setPresupuesto(clienteDTO.getPresupuesto());
                    
                    Cliente clienteActualizado = clienteRepository.save(clienteExistente);
                    return clienteMapper.toDTO(clienteActualizado);
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
    public List<ClienteDTO> obtenerClientesConCarros() {
        List<Cliente> clientes = clienteRepository.findClientesConCarros();
        return clienteMapper.toDTOList(clientes);
    }
    
    @Transactional(readOnly = true)
    public Long contarCarrosDelCliente(Long clienteId) {
        return clienteRepository.countCarrosByClienteId(clienteId);
    }
}