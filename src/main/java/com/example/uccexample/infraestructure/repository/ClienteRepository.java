package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.infraestructure.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Cliente
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    /**
     * Busca un cliente por su nombre
     * @param nombre el nombre del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> findByNombre(String nombre);
    
    /**
     * Busca clientes que contengan el texto en su nombre (búsqueda parcial)
     * @param nombre parte del nombre a buscar
     * @return lista de clientes que coinciden
     */
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    
    // Métodos removidos - la entidad Cliente no tiene campos email ni telefono
    
    /**
     * Consulta personalizada para buscar clientes con carros
     * @return lista de clientes que tienen al menos un carro
     */
    @Query("SELECT DISTINCT c FROM Cliente c JOIN c.carros")
    List<Cliente> findClientesConCarros();
    
    /**
     * Cuenta el número de carros de un cliente
     * @param clienteId el ID del cliente
     * @return número de carros del cliente
     */
    @Query("SELECT COUNT(car) FROM Cliente c JOIN c.carros car WHERE c.idCliente = :clienteId")
    Long countCarrosByClienteId(@Param("clienteId") Long clienteId);
}