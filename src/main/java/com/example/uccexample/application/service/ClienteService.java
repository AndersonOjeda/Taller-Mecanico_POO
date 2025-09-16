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

/**
 * Servicio para la gestión de clientes
 * Utiliza el patrón de capas para separar la lógica de negocio
 */
@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteMapper clienteMapper;
    
    /**
     * Obtiene todos los clientes
     * @return lista de DTOs de clientes
     */
    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }
    
    /**
     * Obtiene un cliente por su ID
     * @param id el ID del cliente
     * @return Optional con el DTO del cliente si existe
     */
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::toDTO);
    }
    
    /**
     * Busca un cliente por su nombre
     * @param nombre el nombre del cliente
     * @return Optional con el DTO del cliente si existe
     */
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> buscarClientePorNombre(String nombre) {
        Optional<Cliente> cliente = clienteRepository.findByNombre(nombre);
        return cliente.map(clienteMapper::toDTO);
    }
    
    // Método removido - la entidad Cliente no tiene campo email
    
    /**
     * Busca clientes que contengan el texto en su nombre
     * @param nombre parte del nombre a buscar
     * @return lista de DTOs de clientes que coinciden
     */
    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarClientesPorNombreParcial(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombreContainingIgnoreCase(nombre);
        return clienteMapper.toDTOList(clientes);
    }
    
    /**
     * Crea un nuevo cliente
     * @param clienteDTO el DTO del cliente a crear
     * @return el DTO del cliente creado
     */
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }
    
    /**
     * Actualiza un cliente existente
     * @param id el ID del cliente a actualizar
     * @param clienteDTO el DTO con los nuevos datos
     * @return Optional con el DTO del cliente actualizado
     */
    public Optional<ClienteDTO> actualizarCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    // Actualizar campos
                    clienteExistente.setNombre(clienteDTO.getNombre());
                    clienteExistente.setPresupuesto(clienteDTO.getPresupuesto());
                    
                    Cliente clienteActualizado = clienteRepository.save(clienteExistente);
                    return clienteMapper.toDTO(clienteActualizado);
                });
    }
    
    /**
     * Elimina un cliente por su ID
     * @param id el ID del cliente a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene clientes que tienen carros
     * @return lista de DTOs de clientes con carros
     */
    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerClientesConCarros() {
        List<Cliente> clientes = clienteRepository.findClientesConCarros();
        return clienteMapper.toDTOList(clientes);
    }
    
    /**
     * Cuenta el número de carros de un cliente
     * @param clienteId el ID del cliente
     * @return número de carros del cliente
     */
    @Transactional(readOnly = true)
    public Long contarCarrosDelCliente(Long clienteId) {
        return clienteRepository.countCarrosByClienteId(clienteId);
    }
}