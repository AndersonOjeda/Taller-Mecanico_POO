package com.example.uccexample.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.uccexample.application.dto.FacturaDTO;
import com.example.uccexample.infraestructure.modelo.Factura;


@Mapper(componentModel = "spring")
public interface FacturaMapper {
    
    FacturaMapper INSTANCE = Mappers.getMapper(FacturaMapper.class);
    
    /**
     * Convierte una entidad Factura a FacturaDTO
     * @param factura la entidad Factura
     * @return el DTO correspondiente
     */
    FacturaDTO toDTO(Factura factura);
    
    /**
     * Convierte un FacturaDTO a entidad Factura
     * @param facturaDTO el DTO
     * @return la entidad correspondiente
     */
    Factura toEntity(FacturaDTO facturaDTO);
    
    /**
     * Convierte una lista de entidades Factura a lista de DTOs
     * @param facturas lista de entidades
     * @return lista de DTOs
     */
    List<FacturaDTO> toDTOList(List<Factura> facturas);
    
    /**
     * Convierte una lista de DTOs a lista de entidades Factura
     * @param facturaDTOs lista de DTOs
     * @return lista de entidades
     */
    List<Factura> toEntityList(List<FacturaDTO> facturaDTOs);
}