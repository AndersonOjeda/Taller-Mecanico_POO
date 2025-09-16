package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Carro
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    
    /**
     * Busca carros por tipo
     * @param tipo el tipo del carro
     * @return lista de carros de ese tipo
     */
    List<Carro> findByTipo(String tipo);
    
    /**
     * Busca carros por estado
     * @param estado el estado del carro
     * @return lista de carros de ese estado
     */
    List<Carro> findByEstado(String estado);
    
    /**
     * Busca carros por modelo
     * @param modelo el modelo del carro
     * @return lista de carros de ese modelo
     */
    List<Carro> findByModelo(String modelo);
    
    /**
     * Busca carros por tipo y modelo
     * @param tipo el tipo del carro
     * @param modelo el modelo del carro
     * @return lista de carros que coinciden
     */
    List<Carro> findByTipoAndModelo(String tipo, String modelo);
    
    /**
     * Busca carros por cliente
     * @param cliente el cliente propietario
     * @return lista de carros del cliente
     */
    List<Carro> findByCliente(Cliente cliente);
    
    /**
     * Busca carros por ID del cliente
     * @param clienteId el ID del cliente
     * @return lista de carros del cliente
     */
    List<Carro> findByClienteIdCliente(Long clienteId);
    
    // Método removido - la entidad Carro no tiene campo placa
    
    /**
     * Busca carros por año
     * @param anio el año del carro
     * @return lista de carros de ese año
     */
    List<Carro> findByAnio(int anio);
    
    /**
     * Busca carros por rango de años
     * @param anioInicio año de inicio del rango
     * @param anioFin año de fin del rango
     * @return lista de carros en ese rango de años
     */
    List<Carro> findByAnioBetween(int anioInicio, int anioFin);
    
    /**
     * Consulta personalizada para buscar carros con información del cliente
     * @param tipo el tipo del carro
     * @return lista de carros con información del cliente
     */
    @Query("SELECT c FROM Carro c JOIN FETCH c.cliente WHERE c.tipo = :tipo")
    List<Carro> findCarrosConClienteByTipo(@Param("tipo") String tipo);
}