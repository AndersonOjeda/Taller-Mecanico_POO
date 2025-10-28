package com.example.uccexample.application.service;

import com.example.uccexample.infraestructure.repository.CarroRepository;
import com.example.uccexample.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Transactional(readOnly = true)
    public void obtenerTodosLosCarros() {
        carroRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public void obtenerCarroPorId(Long id) {
        carroRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorTipo(String tipo) {
        carroRepository.findByTipo(tipo);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorEstado(String estado) {
        carroRepository.findByEstado(estado);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorModelo(String modelo) {
        carroRepository.findByModelo(modelo);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorTipoYModelo(String tipo, String modelo) {
        carroRepository.findByTipoAndModelo(tipo, modelo);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorClienteId(Long clienteId) {
        carroRepository.findByClienteIdCliente(clienteId);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorAnio(int anio) {
        carroRepository.findByAnio(anio);
    }
    
    @Transactional(readOnly = true)
    public void buscarCarrosPorRangoAnios(int anioInicio, int anioFin) {
        carroRepository.findByAnioBetween(anioInicio, anioFin);
    }
    
    public void crearCarro(Long clienteId, String marca, String modelo, String tipo) {
        // Validar que el cliente existe
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("No existe un cliente con ID: " + clienteId);
        }
        // Solo operaciones de repositorio
    }
    
    public void actualizarCarro(Long id, Long clienteId, String marca, String modelo, String tipo) {
        // Validar que el cliente existe si se proporciona un clienteId
        if (clienteId != null && !clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("No existe un cliente con ID: " + clienteId);
        }
        
        carroRepository.findById(id);
    }
    
    public void eliminarCarro(Long id) {
        carroRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public void obtenerCarrosConClientePorTipo(String tipo) {
        carroRepository.findCarrosConClienteByTipo(tipo);
    }
}