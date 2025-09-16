package com.example.uccexample.application.mapper;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.infraestructure.modelo.Carro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper para convertir entre la entidad Carro y su DTO
 * Utiliza MapStruct para generar automáticamente las implementaciones
 */
@Mapper(componentModel = "spring")
public interface CarroMapper {
    
    CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);
    
    /**
     * Convierte una entidad Carro a CarroDTO
     * @param carro la entidad Carro
     * @return el DTO correspondiente
     */
    @Mapping(source = "cliente.idCliente", target = "clienteId")
    CarroDTO toDTO(Carro carro);
    
    /**
     * Convierte un CarroDTO a entidad Carro
     * @param carroDTO el DTO
     * @return la entidad correspondiente
     */
    @Mapping(target = "cliente", ignore = true) // Se manejará en el servicio
    Carro toEntity(CarroDTO carroDTO);
    
    /**
     * Convierte una lista de entidades Carro a lista de DTOs
     * @param carros lista de entidades
     * @return lista de DTOs
     */
    List<CarroDTO> toDTOList(List<Carro> carros);
    
    /**
     * Convierte una lista de DTOs a lista de entidades Carro
     * @param carroDTOs lista de DTOs
     * @return lista de entidades
     */
    List<Carro> toEntityList(List<CarroDTO> carroDTOs);
}