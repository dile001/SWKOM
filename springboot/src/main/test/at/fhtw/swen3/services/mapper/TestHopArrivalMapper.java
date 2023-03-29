package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.HopArrival;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHopArrivalMapper {

    HopArrival arrival= new HopArrival().code("TEST1").description("TEST").dateTime(OffsetDateTime.MAX);

    @Test
    void fromDTO() {

        HopArrivalEntity entity=HopArrivalMapper.INSTANCE.dtoToEntity(arrival);
        assertEquals(arrival.getCode(), entity.getCode());
        assertEquals(arrival.getDateTime(), entity.getDateTime());
        assertEquals(arrival.getDescription(), entity.getDescription());
    }

    @Test
    void fromEntity() {

        HopArrivalEntity entity=HopArrivalMapper.INSTANCE.dtoToEntity(arrival);
        HopArrival newArrival=HopArrivalMapper.INSTANCE.entityToDto(entity);
        assertEquals(entity.getCode(), newArrival.getCode());
        assertEquals(entity.getDateTime(), newArrival.getDateTime());
        assertEquals(entity.getDescription(), newArrival.getDescription());
    }
}
