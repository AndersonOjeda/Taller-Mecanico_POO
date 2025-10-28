package com.example.uccexample.application.contract;

import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.application.dto.CarroDTO;

import java.util.List;
import java.util.Optional;

/**
 * Contrato (interfaz) CRUD personalizada para Carro
 * Define las operaciones de negocio específicas para Carro
 */
public interface ICarroContract {
    
    // Operaciones CRUD básicas
    List<Carro> findAll();
    Optional<Carro> findById(Long id);
    Carro save(Carro carro);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
    void delete(Carro carro);
    
    // Operaciones específicas de negocio
    List<Carro> findByTipo(String tipo);
    List<Carro> findByEstado(String estado);
    List<Carro> findByModelo(String modelo);
    List<Carro> findByTipoAndModelo(String tipo, String modelo);
    List<Carro> findByCliente(Cliente cliente);
    List<Carro> findByClienteIdCliente(Long clienteId);
    List<Carro> findByAnio(int anio);
    List<Carro> findByAnioBetween(int anioInicio, int anioFin);
    List<Carro> findCarrosConClienteByTipo(String tipo);
    List<Carro> findByDanoMayorA(float costoMinimo);
    List<Carro> findCarrosReparadosByClienteId(Long clienteId);
    Long countByEstado(String estado);
    
    // Operaciones con DTOs
    List<CarroDTO> findAllDTO();
    Optional<CarroDTO> findByIdDTO(Long id);
    CarroDTO saveDTO(CarroDTO carroDTO);
    Optional<CarroDTO> updateDTO(Long id, CarroDTO carroDTO);
    List<CarroDTO> findByTipoDTO(String tipo);
    List<CarroDTO> findByEstadoDTO(String estado);
}