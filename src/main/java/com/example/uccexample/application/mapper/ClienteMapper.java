package com.example.uccexample.application.mapper;

import com.example.uccexample.application.dto.ClienteDTO;
import com.example.uccexample.infraestructure.modelo.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper para convertir entre la entidad Cliente y su DTO
 * Utiliza MapStruct para generar automáticamente las implementaciones
 */
@Mapper(componentModel = "spring", uses = {CarroMapper.class})
public interface ClienteMapper {
    
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    
    /**
     * Convierte una entidad Cliente a ClienteDTO
     * @param cliente la entidad Cliente
     * @return el DTO correspondiente
     */
    ClienteDTO toDTO(Cliente cliente);
    
    /**
     * Convierte un ClienteDTO a entidad Cliente
     * @param clienteDTO el DTO
     * @return la entidad correspondiente
     */
    Cliente toEntity(ClienteDTO clienteDTO);
    
    /**
     * Convierte una lista de entidades Cliente a lista de DTOs
     * @param clientes lista de entidades
     * @return lista de DTOs
     */
    List<ClienteDTO> toDTOList(List<Cliente> clientes);
    
    /**
     * Convierte una lista de DTOs a lista de entidades Cliente
     * @param clienteDTOs lista de DTOs
     * @return lista de entidades
     */
    List<Cliente> toEntityList(List<ClienteDTO> clienteDTOs);
}