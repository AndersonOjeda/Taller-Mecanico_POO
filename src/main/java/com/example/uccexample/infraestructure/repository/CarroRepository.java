package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.mapper.CarroMapper;
import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.application.repository.ICarroRepository;
import com.example.uccexample.infraestructure.repository.jpa.CarroJpaRepository;
import com.example.uccexample.infraestructure.repository.jpa.ClienteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class CarroRepository implements ICarroRepository {

    @Autowired
    private CarroJpaRepository carroJpaRepository;

    @Autowired
    private ClienteJpaRepository clienteJpaRepository;

    @Autowired
    private CarroMapper carroMapper;
    
    @Override
    public List<Carro> findAll() {
        return carroJpaRepository.findAll();
    }
    
    @Override
    public List<CarroDTO> findAllDTO() {
        List<Carro> carros = findAll();
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public Optional<Carro> findById(Long id) {
        return carroJpaRepository.findById(id);
    }
    
    @Override
    public Optional<CarroDTO> findByIdDTO(Long id) {
        Optional<Carro> carro = findById(id);
        return carro.map(carroMapper::toDTO);
    }
    
    @Override
    public Carro save(Carro carro) {
        return carroJpaRepository.save(carro);
    }
    
    @Override
    public CarroDTO saveDTO(CarroDTO carroDTO) {
        Carro carro = carroMapper.toEntity(carroDTO);
        
        // Establecer la relación con el cliente si se proporciona un clienteId
        if (carroDTO.getClienteId() != null) {
            Cliente cliente = clienteJpaRepository.findById(carroDTO.getClienteId()).orElse(null);
            if (cliente != null) {
                carro.setCliente(cliente);
            }
        }
        
        Carro carroGuardado = save(carro);
        return carroMapper.toDTO(carroGuardado);
    }
    
    @Override
    public void deleteById(Long id) {
        carroJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return carroJpaRepository.existsById(id);
    }
    
    @Override
    public List<Carro> findByTipo(String tipo) {
        return carroJpaRepository.findByTipo(tipo);
    }
    
    @Override
    public List<CarroDTO> findByTipoDTO(String tipo) {
        List<Carro> carros = findByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByEstado(String estado) {
        return carroJpaRepository.findByEstado(estado);
    }
    
    @Override
    public List<CarroDTO> findByEstadoDTO(String estado) {
        List<Carro> carros = findByEstado(estado);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByModelo(String modelo) {
        return carroJpaRepository.findByModelo(modelo);
    }
    
    @Override
    public List<CarroDTO> findByModeloDTO(String modelo) {
        List<Carro> carros = findByModelo(modelo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByTipoAndModelo(String tipo, String modelo) {
        return carroJpaRepository.findByTipoAndModelo(tipo, modelo);
    }
    
    @Override
    public List<CarroDTO> findByTipoAndModeloDTO(String tipo, String modelo) {
        List<Carro> carros = findByTipoAndModelo(tipo, modelo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByCliente(Cliente cliente) {
        return carroJpaRepository.findByClienteIdCliente(cliente.getIdCliente());
    }
    
    @Override
    public List<Carro> findByClienteIdCliente(Long clienteId) {
        return carroJpaRepository.findByClienteIdCliente(clienteId);
    }
    
    @Override
    public List<CarroDTO> findByClienteIdClienteDTO(Long clienteId) {
        List<Carro> carros = findByClienteIdCliente(clienteId);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByAnio(int anio) {
        return carroJpaRepository.findByAnio(anio);
    }
    
    @Override
    public List<CarroDTO> findByAnioDTO(int anio) {
        List<Carro> carros = findByAnio(anio);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByAnioBetween(int anioInicio, int anioFin) {
        return carroJpaRepository.findByAnioBetween(anioInicio, anioFin);
    }
    
    @Override
    public List<CarroDTO> findByAnioBetweenDTO(int anioInicio, int anioFin) {
        List<Carro> carros = findByAnioBetween(anioInicio, anioFin);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findCarrosConClienteByTipo(String tipo) {
        return carroJpaRepository.findCarrosConClienteByTipo(tipo);
    }
    
    @Override
    public List<CarroDTO> findCarrosConClienteByTipoDTO(String tipo) {
        List<Carro> carros = findCarrosConClienteByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public Optional<CarroDTO> updateDTO(Long id, CarroDTO carroDTO) {
        return findById(id)
                .map(carroExistente -> {
                    carroExistente.setTipo(carroDTO.getTipo());
                    carroExistente.setModelo(carroDTO.getModelo());
                    carroExistente.setAnio(carroDTO.getAnio());
                    carroExistente.setEstado(carroDTO.getEstado());
                    
                    // Actualizar la relación con el cliente si se proporciona un clienteId
                    if (carroDTO.getClienteId() != null) {
                        Cliente cliente = clienteJpaRepository.findById(carroDTO.getClienteId()).orElse(null);
                        if (cliente != null) {
                            carroExistente.setCliente(cliente);
                        }
                    }
                    
                    Carro carroActualizado = save(carroExistente);
                    return carroMapper.toDTO(carroActualizado);
                });
    }
    
    @Override
    public void deleteByIdDTO(Long id) {
        deleteById(id);
    }
    
    @Override
    public boolean existsByIdDTO(Long id) {
        return existsById(id);
    }
}
