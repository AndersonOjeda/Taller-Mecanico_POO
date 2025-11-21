package com.example.uccexample.application.service;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.application.mapper.CarroMapper;
import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private CarroMapper carroMapper;
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerTodosLosClientesDTO() {
        return clienteRepository.findAllDTO();
    }
    
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> obtenerClientePorIdDTO(Long id) {
        return clienteRepository.findByIdDTO(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarClientePorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> buscarClientesPorNombreParcial(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public Cliente crearClienteDesdeDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPresupuesto(clienteDTO.getPresupuesto());

        if (clienteDTO.getCarros() != null) {
            List<Carro> carros = new ArrayList<>();
            for (CarroDTO carroDTO : clienteDTO.getCarros()) {
                Carro carro = carroMapper.toEntity(carroDTO);
                carro.setCliente(cliente);
                carros.add(carro);
            }
            cliente.setCarros(carros);
        }

        return clienteRepository.save(cliente);
    }
    
    public Optional<Cliente> actualizarClienteDesdeDTO(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteDTO.getNombre());
                    clienteExistente.setEmail(clienteDTO.getEmail());
                    clienteExistente.setPresupuesto(clienteDTO.getPresupuesto());

                    if (clienteDTO.getCarros() != null) {
                        if (clienteExistente.getCarros() == null) {
                            clienteExistente.setCarros(new ArrayList<>());
                        } else {
                            clienteExistente.getCarros().clear();
                        }
                        for (CarroDTO carroDTO : clienteDTO.getCarros()) {
                            Carro carro = carroMapper.toEntity(carroDTO);
                            carro.setCliente(clienteExistente);
                            clienteExistente.getCarros().add(carro);
                        }
                    }

                    return clienteRepository.save(clienteExistente);
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
