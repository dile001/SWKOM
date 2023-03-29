package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHopMapper {

    Hop hop= new Hop().processingDelayMins(2).code("TEST1").hopType("TEST").description("TEST").locationName("TEST").locationCoordinates(new GeoCoordinate().lon(10.0).lat(10.0));


    @Test
    void fromDTO() {

        HopEntity entity= HopMapper.INSTANCE.dtoToEntity(hop);
        assertEquals(hop.getCode(), entity.getCode());
        assertEquals(hop.getDescription(), entity.getDescription());
        assertEquals(hop.getHopType(), entity.getHopType());
        assert(hop.getLocationCoordinates().getLon() == entity.getLocationCoordinates().getX());
        assert(hop.getLocationCoordinates().getLat() == entity.getLocationCoordinates().getY());
        assertEquals(hop.getLocationName(), entity.getLocationName());
        assertEquals(hop.getProcessingDelayMins(), entity.getProcessingDelayMins());

    }

    @Test
    void fromEntity() {

        HopEntity entity= HopMapper.INSTANCE.dtoToEntity(hop);
        Hop newHop= HopMapper.INSTANCE.entityToDto(entity);
        assertEquals(entity.getCode(), newHop.getCode());
        assertEquals(entity.getDescription(), newHop.getDescription());
        assertEquals(entity.getHopType(), newHop.getHopType());
        assert(entity.getLocationCoordinates().getX() == newHop.getLocationCoordinates().getLon());
        assert(entity.getLocationCoordinates().getY() == newHop.getLocationCoordinates().getLat());
        assertEquals(entity.getLocationName(), newHop.getLocationName());
        assertEquals(entity.getProcessingDelayMins(), newHop.getProcessingDelayMins());

    }
}
