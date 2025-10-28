package com.example.uccexample.application.repository;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el repositorio de Carro
 * Define las operaciones de acceso a datos para la entidad Carro
 */
public interface ICarroRepository {
    
    List<Carro> findAll();
    
    Optional<Carro> findById(Long id);
    
    Carro save(Carro carro);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
    
    List<Carro> findByTipo(String tipo);
    
    List<Carro> findByEstado(String estado);
    
    List<Carro> findByModelo(String modelo);
    
    List<Carro> findByTipoAndModelo(String tipo, String modelo);
    
    List<Carro> findByCliente(Cliente cliente);
    
    List<Carro> findByClienteIdCliente(Long clienteId);
    
    List<Carro> findByAnio(int anio);
    
    List<Carro> findByAnioBetween(int anioInicio, int anioFin);
    
    List<Carro> findCarrosConClienteByTipo(String tipo);
    
    // Métodos DTO
    List<CarroDTO> findAllDTO();
    
    Optional<CarroDTO> findByIdDTO(Long id);
    
    CarroDTO saveDTO(CarroDTO carroDTO);
    
    List<CarroDTO> findByTipoDTO(String tipo);
    
    List<CarroDTO> findByEstadoDTO(String estado);
    
    List<CarroDTO> findByModeloDTO(String modelo);
    
    List<CarroDTO> findByTipoAndModeloDTO(String tipo, String modelo);
    
    List<CarroDTO> findByClienteIdClienteDTO(Long clienteId);
    
    List<CarroDTO> findByAnioDTO(int anio);
    
    List<CarroDTO> findByAnioBetweenDTO(int anioInicio, int anioFin);
    
    List<CarroDTO> findCarrosConClienteByTipoDTO(String tipo);
    
    Optional<CarroDTO> updateDTO(Long id, CarroDTO carroDTO);
}