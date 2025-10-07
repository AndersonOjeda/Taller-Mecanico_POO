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

@Service
@Transactional
public class CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private CarroMapper carroMapper;
    
    @Transactional(readOnly = true)
    public List<CarroDTO> obtenerTodosLosCarros() {
        List<Carro> carros = carroRepository.findAll();
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public Optional<CarroDTO> obtenerCarroPorId(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(carroMapper::toDTO);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorTipo(String tipo) {
        List<Carro> carros = carroRepository.findByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorEstado(String estado) {
        List<Carro> carros = carroRepository.findByEstado(estado);
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorModelo(String modelo) {
        List<Carro> carros = carroRepository.findByModelo(modelo);
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorTipoYModelo(String tipo, String modelo) {
        List<Carro> carros = carroRepository.findByTipoAndModelo(tipo, modelo);
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorClienteId(Long clienteId) {
        List<Carro> carros = carroRepository.findByClienteIdCliente(clienteId);
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorAnio(int anio) {
        List<Carro> carros = carroRepository.findByAnio(anio);
        return carroMapper.toDTOList(carros);
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> buscarCarrosPorRangoAnios(int anioInicio, int anioFin) {
        List<Carro> carros = carroRepository.findByAnioBetween(anioInicio, anioFin);
        return carroMapper.toDTOList(carros);
    }
    
    public CarroDTO crearCarro(CarroDTO carroDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(carroDTO.getClienteId());
        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un cliente con ID: " + carroDTO.getClienteId());
        }
        
        Carro carro = carroMapper.toEntity(carroDTO);
        carro.setCliente(clienteOpt.get());
        
        Carro carroGuardado = carroRepository.save(carro);
        return carroMapper.toDTO(carroGuardado);
    }
    
    public Optional<CarroDTO> actualizarCarro(Long id, CarroDTO carroDTO) {
        return carroRepository.findById(id)
                .map(carroExistente -> {
                    carroExistente.setModelo(carroDTO.getModelo());
                    carroExistente.setAnio(carroDTO.getAnio());
                    carroExistente.setEstado(carroDTO.getEstado());
                    carroExistente.setTipo(carroDTO.getTipo());
                    carroExistente.setCostoDano(carroDTO.getCostoDano());
                    
                    if (carroDTO.getClienteId() != null) {
                        Optional<Cliente> clienteOpt = clienteRepository.findById(carroDTO.getClienteId());
                        clienteOpt.ifPresent(carroExistente::setCliente);
                    }
                    
                    Carro carroActualizado = carroRepository.save(carroExistente);
                    return carroMapper.toDTO(carroActualizado);
                });
    }
    
    public boolean eliminarCarro(Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = true)
    public List<CarroDTO> obtenerCarrosConClientePorTipo(String tipo) {
        List<Carro> carros = carroRepository.findCarrosConClienteByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
}