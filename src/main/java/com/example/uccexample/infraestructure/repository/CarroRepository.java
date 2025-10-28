package com.example.uccexample.infraestructure.repository;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.application.mapper.CarroMapper;
import com.example.uccexample.infraestructure.modelo.Carro;
import com.example.uccexample.infraestructure.modelo.Cliente;
import com.example.uccexample.application.repository.ICarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CarroRepository implements ICarroRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private CarroMapper carroMapper;
    
    @Override
    public List<Carro> findAll() {
        return entityManager.createQuery("SELECT c FROM Carro c", Carro.class).getResultList();
    }
    
    @Override
    public List<CarroDTO> findAllDTO() {
        List<Carro> carros = findAll();
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public Optional<Carro> findById(Long id) {
        Carro carro = entityManager.find(Carro.class, id);
        return Optional.ofNullable(carro);
    }
    
    @Override
    public Optional<CarroDTO> findByIdDTO(Long id) {
        Optional<Carro> carro = findById(id);
        return carro.map(carroMapper::toDTO);
    }
    
    @Override
    public Carro save(Carro carro) {
        if (carro.getIdAuto() == null) {
            entityManager.persist(carro);
            return carro;
        } else {
            return entityManager.merge(carro);
        }
    }
    
    @Override
    public CarroDTO saveDTO(CarroDTO carroDTO) {
        Carro carro = carroMapper.toEntity(carroDTO);
        
        // Establecer la relación con el cliente si se proporciona un clienteId
        if (carroDTO.getClienteId() != null) {
            Cliente cliente = entityManager.find(Cliente.class, carroDTO.getClienteId());
            if (cliente != null) {
                carro.setCliente(cliente);
            }
        }
        
        Carro carroGuardado = save(carro);
        return carroMapper.toDTO(carroGuardado);
    }
    
    @Override
    public void deleteById(Long id) {
        Carro carro = entityManager.find(Carro.class, id);
        if (carro != null) {
            entityManager.remove(carro);
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        Long count = entityManager.createQuery("SELECT COUNT(c) FROM Carro c WHERE c.idAuto = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }
    
    @Override
    public List<Carro> findByTipo(String tipo) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.tipo = :tipo", Carro.class)
                .setParameter("tipo", tipo)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByTipoDTO(String tipo) {
        List<Carro> carros = findByTipo(tipo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByEstado(String estado) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.estado = :estado", Carro.class)
                .setParameter("estado", estado)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByEstadoDTO(String estado) {
        List<Carro> carros = findByEstado(estado);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByModelo(String modelo) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.modelo = :modelo", Carro.class)
                .setParameter("modelo", modelo)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByModeloDTO(String modelo) {
        List<Carro> carros = findByModelo(modelo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByTipoAndModelo(String tipo, String modelo) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.tipo = :tipo AND c.modelo = :modelo", Carro.class)
                .setParameter("tipo", tipo)
                .setParameter("modelo", modelo)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByTipoAndModeloDTO(String tipo, String modelo) {
        List<Carro> carros = findByTipoAndModelo(tipo, modelo);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByCliente(Cliente cliente) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.cliente = :cliente", Carro.class)
                .setParameter("cliente", cliente)
                .getResultList();
    }
    
    @Override
    public List<Carro> findByClienteIdCliente(Long clienteId) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.cliente.idCliente = :clienteId", Carro.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByClienteIdClienteDTO(Long clienteId) {
        List<Carro> carros = findByClienteIdCliente(clienteId);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByAnio(int anio) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.anio = :anio", Carro.class)
                .setParameter("anio", anio)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByAnioDTO(int anio) {
        List<Carro> carros = findByAnio(anio);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findByAnioBetween(int anioInicio, int anioFin) {
        return entityManager.createQuery("SELECT c FROM Carro c WHERE c.anio BETWEEN :anioInicio AND :anioFin", Carro.class)
                .setParameter("anioInicio", anioInicio)
                .setParameter("anioFin", anioFin)
                .getResultList();
    }
    
    @Override
    public List<CarroDTO> findByAnioBetweenDTO(int anioInicio, int anioFin) {
        List<Carro> carros = findByAnioBetween(anioInicio, anioFin);
        return carroMapper.toDTOList(carros);
    }
    
    @Override
    public List<Carro> findCarrosConClienteByTipo(String tipo) {
        return entityManager.createQuery("SELECT c FROM Carro c JOIN FETCH c.cliente WHERE c.tipo = :tipo", Carro.class)
                .setParameter("tipo", tipo)
                .getResultList();
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
                        Cliente cliente = entityManager.find(Cliente.class, carroDTO.getClienteId());
                        if (cliente != null) {
                            carroExistente.setCliente(cliente);
                        }
                    }
                    
                    Carro carroActualizado = save(carroExistente);
                    return carroMapper.toDTO(carroActualizado);
                });
    }
}