package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    
    List<Carro> findByTipo(String tipo);
    
    List<Carro> findByEstado(String estado);
    
    List<Carro> findByModelo(String modelo);
    
    List<Carro> findByTipoAndModelo(String tipo, String modelo);
    
    List<Carro> findByCliente(Cliente cliente);
    
    List<Carro> findByClienteIdCliente(Long clienteId);
    
    List<Carro> findByAnio(int anio);
    
    List<Carro> findByAnioBetween(int anioInicio, int anioFin);
    
    @Query("SELECT c FROM Carro c JOIN FETCH c.cliente WHERE c.tipo = :tipo")
    List<Carro> findCarrosConClienteByTipo(@Param("tipo") String tipo);
}