package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopMapper{
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    @Mapping(source = "locationCoordinates",target = "locationCoordinates")//TODO proveri
    Hop entityToDto(HopEntity entity);

    @Mapping(source = "locationCoordinates",target = "locationCoordinates")//TODO proveri
    HopEntity dtoToEntity(Hop o);
}