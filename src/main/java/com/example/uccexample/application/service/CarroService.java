package com.example.uccexample.application.service;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.mapper.CarroMapper;
import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.infraestructure.repository.CarroRepository;
import com.example.uccexample.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de carros
 * Utiliza el patrón de capas para separar la lógica de negocio
 */
@Service
@Transactional
public class CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private CarroMapper carroMapper;
    
    /**
     * Obtiene todos los carros
     * @return lista de DTOs de carros
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> obtenerTodosLosCarros() {
        List<Carro> carros = carroRepository.findAll();
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Obtiene un carro por su ID
     * @param id el ID del carro
     * @return Optional con el DTO del carro si existe
     */
    @Transactional(readOnly = true)
    public Optional<CarroDTO> obtenerCarroPorId(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(carroMapper::toDTO);
    }
    
    /**
     * Busca carros por tipo
     * @param tipo el tipo del carro
     * @return lista de DTOs de carros de ese tipo
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorTipo(String tipo) {
        List<Carro> carros = carroRepository.findByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Busca carros por estado
     * @param estado el estado del carro
     * @return lista de DTOs de carros en ese estado
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorEstado(String estado) {
        List<Carro> carros = carroRepository.findByEstado(estado);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Busca carros por modelo
     * @param modelo el modelo del carro
     * @return lista de DTOs de carros de ese modelo
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorModelo(String modelo) {
        List<Carro> carros = carroRepository.findByModelo(modelo);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Busca carros por tipo y modelo
     * @param tipo el tipo del carro
     * @param modelo el modelo del carro
     * @return lista de DTOs de carros que coinciden
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorTipoYModelo(String tipo, String modelo) {
        List<Carro> carros = carroRepository.findByTipoAndModelo(tipo, modelo);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Busca carros por ID del cliente
     * @param clienteId el ID del cliente
     * @return lista de DTOs de carros del cliente
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorClienteId(Long clienteId) {
        List<Carro> carros = carroRepository.findByClienteIdCliente(clienteId);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Busca carros por año
     * @param anio el año del carro
     * @return lista de DTOs de carros de ese año
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorAnio(int anio) {
        List<Carro> carros = carroRepository.findByAnio(anio);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Busca carros por rango de años
     * @param anioInicio año de inicio del rango
     * @param anioFin año de fin del rango
     * @return lista de DTOs de carros en ese rango
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorRangoAnios(int anioInicio, int anioFin) {
        List<Carro> carros = carroRepository.findByAnioBetween(anioInicio, anioFin);
        return carroMapper.toDTOList(carros);
    }
    
    /**
     * Crea un nuevo carro
     * @param carroDTO el DTO del carro a crear
     * @return el DTO del carro creado
     * @throws IllegalArgumentException si el cliente no existe
     */
    public CarroDTO crearCarro(CarroDTO carroDTO) {
        // Validar que el cliente exista
        Optional<Cliente> clienteOpt = clienteRepository.findById(carroDTO.getClienteId());
        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un cliente con ID: " + carroDTO.getClienteId());
        }
        
        Carro carro = carroMapper.toEntity(carroDTO);
        carro.setCliente(clienteOpt.get());
        
        Carro carroGuardado = carroRepository.save(carro);
        return carroMapper.toDTO(carroGuardado);
    }
    
    /**
     * Actualiza un carro existente
     * @param id el ID del carro a actualizar
     * @param carroDTO el DTO con los nuevos datos
     * @return Optional con el DTO del carro actualizado
     */
    public Optional<CarroDTO> actualizarCarro(Long id, CarroDTO carroDTO) {
        return carroRepository.findById(id)
                .map(carroExistente -> {
                    // Actualizar campos
                    carroExistente.setModelo(carroDTO.getModelo());
                    carroExistente.setAnio(carroDTO.getAnio());
                    carroExistente.setEstado(carroDTO.getEstado());
                    carroExistente.setTipo(carroDTO.getTipo());
                    carroExistente.setCostoDano(carroDTO.getCostoDano());
                    
                    // Actualizar cliente si es necesario
                    if (carroDTO.getClienteId() != null) {
                        Optional<Cliente> clienteOpt = clienteRepository.findById(carroDTO.getClienteId());
                        clienteOpt.ifPresent(carroExistente::setCliente);
                    }
                    
                    Carro carroActualizado = carroRepository.save(carroExistente);
                    return carroMapper.toDTO(carroActualizado);
                });
    }
    
    /**
     * Elimina un carro por su ID
     * @param id el ID del carro a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarCarro(Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene carros con información del cliente por tipo
     * @param tipo el tipo del carro
     * @return lista de DTOs de carros con información del cliente
     */
    @Transactional(readOnly = true)
    public List<CarroDTO> obtenerCarrosConClientePorTipo(String tipo) {
        List<Carro> carros = carroRepository.findCarrosConClienteByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
}