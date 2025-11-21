package com.example.uccexample.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.uccexample.application.dto.CarroDTO;
import com.example.uccexample.infraestructure.modelo.Carro;


@Mapper(componentModel = "spring")
public interface CarroMapper {
    
    CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);
    

    @Mapping(source = "cliente.idCliente", target = "clienteId")
    CarroDTO toDTO(Carro carro);
    

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