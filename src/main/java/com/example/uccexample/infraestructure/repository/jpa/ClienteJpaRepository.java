package com.example.uccexample.infraestructure.repository.jpa;

import com.example.uccexample.infraestructure.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNombre(String nombre);

    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT DISTINCT c FROM Cliente c JOIN FETCH c.carros")
    List<Cliente> findClientesConCarros();

    @Query("SELECT COUNT(car) FROM Cliente c JOIN c.carros car WHERE c.idCliente = :clienteId")
    Long countCarrosByClienteId(@Param("clienteId") Long clienteId);
}
