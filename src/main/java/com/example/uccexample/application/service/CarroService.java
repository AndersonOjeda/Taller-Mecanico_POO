package com.example.uccexample.application.service;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.infraestructure.repository.CarroRepository;
import com.example.uccexample.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Carro> obtenerTodosLosCarros() {
        return carroRepository.findAll();
    }

    public List<CarroDTO> obtenerTodosLosCarrosDTO() {
        return carroRepository.findAllDTO();
    }
    
    public Optional<Carro> obtenerCarroPorId(Long id) {
        return carroRepository.findById(id);
    }

    public Optional<CarroDTO> obtenerCarroPorIdDTO(Long id) {
        return carroRepository.findByIdDTO(id);
    }
    
    public List<Carro> buscarCarrosPorTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }

    public List<CarroDTO> buscarCarrosPorTipoDTO(String tipo) {
        return carroRepository.findByTipoDTO(tipo);
    }
    
    public List<Carro> buscarCarrosPorEstado(String estado) {
        return carroRepository.findByEstado(estado);
    }

    public List<CarroDTO> buscarCarrosPorEstadoDTO(String estado) {
        return carroRepository.findByEstadoDTO(estado);
    }
    
    public List<Carro> buscarCarrosPorModelo(String modelo) {
        return carroRepository.findByModelo(modelo);
    }

    public List<CarroDTO> buscarCarrosPorModeloDTO(String modelo) {
        return carroRepository.findByModeloDTO(modelo);
    }
    
    public List<Carro> buscarCarrosPorTipoYModelo(String tipo, String modelo) {
        return carroRepository.findByTipoAndModelo(tipo, modelo);
    }

    public List<CarroDTO> buscarCarrosPorTipoYModeloDTO(String tipo, String modelo) {
        return carroRepository.findByTipoAndModeloDTO(tipo, modelo);
    }
    
    public List<Carro> buscarCarrosPorClienteId(Long clienteId) {
        return carroRepository.findByClienteIdCliente(clienteId);
    }

    public List<CarroDTO> buscarCarrosPorClienteIdDTO(Long clienteId) {
        return carroRepository.findByClienteIdClienteDTO(clienteId);
    }
    
    public List<Carro> buscarCarrosPorAnio(int anio) {
        return carroRepository.findByAnio(anio);
    }

    public List<CarroDTO> buscarCarrosPorAnioDTO(int anio) {
        return carroRepository.findByAnioDTO(anio);
    }
    
    public List<Carro> buscarCarrosPorRangoAnio(int anioInicio, int anioFin) {
        return carroRepository.findByAnioBetween(anioInicio, anioFin);
    }

    public List<CarroDTO> buscarCarrosPorRangoAnioDTO(int anioInicio, int anioFin) {
        return carroRepository.findByAnioBetweenDTO(anioInicio, anioFin);
    }
    
    public List<Carro> obtenerCarrosConClientePorTipo(String tipo) {
        return carroRepository.findCarrosConClienteByTipo(tipo);
    }

    public List<CarroDTO> obtenerCarrosConClientePorTipoDTO(String tipo) {
        return carroRepository.findCarrosConClienteByTipoDTO(tipo);
    }
    
    public Carro crearCarroDesdeDTO(CarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setModelo(carroDTO.getModelo());
        carro.setTipo(carroDTO.getTipo());
        carro.setEstado(carroDTO.getEstado());
        carro.setAnio(carroDTO.getAnio());
        carro.setCostoDano(carroDTO.getCostoDano());

        if (carroDTO.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(carroDTO.getClienteId()).orElse(null);
            carro.setCliente(cliente);
        }

        return carroRepository.save(carro);
    }
    
    public Optional<Carro> actualizarCarroDesdeDTO(Long id, CarroDTO carroDTO) {
        return carroRepository.findById(id)
                .map(carroExistente -> {
                    carroExistente.setModelo(carroDTO.getModelo());
                    carroExistente.setTipo(carroDTO.getTipo());
                    carroExistente.setEstado(carroDTO.getEstado());
                    carroExistente.setAnio(carroDTO.getAnio());
                    carroExistente.setCostoDano(carroDTO.getCostoDano());

                    if (carroDTO.getClienteId() != null) {
                        Cliente cliente = clienteRepository.findById(carroDTO.getClienteId()).orElse(null);
                        carroExistente.setCliente(cliente);
                    }

                    return carroRepository.save(carroExistente);
                });
    }
    
    public void eliminarCarro(Long id) {
        carroRepository.deleteById(id);
    }
    
    public boolean existeCarro(Long id) {
        return carroRepository.existsById(id);
    }
}
