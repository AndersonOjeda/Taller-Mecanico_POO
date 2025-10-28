package com.example.uccexample.application.service;

import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    public List<Carro> obtenerTodosLosCarros() {
        return carroRepository.findAll();
    }
    
    public Optional<Carro> obtenerCarroPorId(Long id) {
        return carroRepository.findById(id);
    }
    
    public List<Carro> buscarCarrosPorTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }
    
    public List<Carro> buscarCarrosPorEstado(String estado) {
        return carroRepository.findByEstado(estado);
    }
    
    public List<Carro> buscarCarrosPorModelo(String modelo) {
        return carroRepository.findByModelo(modelo);
    }
    
    public List<Carro> buscarCarrosPorTipoYModelo(String tipo, String modelo) {
        return carroRepository.findByTipoAndModelo(tipo, modelo);
    }
    
    public List<Carro> buscarCarrosPorClienteId(Long clienteId) {
        return carroRepository.findByClienteIdCliente(clienteId);
    }
    
    public List<Carro> buscarCarrosPorAnio(int anio) {
        return carroRepository.findByAnio(anio);
    }
    
    public List<Carro> buscarCarrosPorRangoAnio(int anioInicio, int anioFin) {
        return carroRepository.findByAnioBetween(anioInicio, anioFin);
    }
    
    public List<Carro> obtenerCarrosConClientePorTipo(String tipo) {
        return carroRepository.findCarrosConClienteByTipo(tipo);
    }
    
    public void crearCarro(String marca, String modelo, String tipo, Long clienteId) {
        Carro carro = new Carro();
        // Nota: marca no existe en el modelo, se usa modelo en su lugar
        carro.setModelo(modelo);
        carro.setTipo(tipo);
        // Nota: clienteId se usaría para establecer la relación con Cliente si fuera necesario
        carroRepository.save(carro);
    }
    
    public void actualizarCarro(Long id, String marca, String modelo, String tipo, Long clienteId) {
        carroRepository.findById(id)
                .ifPresent(carroExistente -> {
                    // Nota: marca no existe en el modelo, se usa modelo en su lugar
                    carroExistente.setModelo(modelo);
                    carroExistente.setTipo(tipo);
                    // Nota: clienteId se usaría para establecer la relación con Cliente si fuera necesario
                    carroRepository.save(carroExistente);
                });
    }
    
    public void eliminarCarro(Long id) {
        carroRepository.deleteById(id);
    }
    
    public boolean existeCarro(Long id) {
        return carroRepository.existsById(id);
    }
}
