package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWarehouseNextHopsMapper {

    Hop Hop;
    WarehouseNextHops nextHops;

    void setUp(){

        Hop= new Hop();
        Hop.processingDelayMins(2).code("TEST").hopType("TEST").description("TEST").locationName("TEST").locationCoordinates(new GeoCoordinate().lon(10.0).lat(10.0));
        nextHops= new WarehouseNextHops().hop(Hop).traveltimeMins(2);

    }

    @Test
    void fromDTO() {

        setUp();

        WarehouseNextHopsEntity entity= WarehouseNextHopsMapper.INSTANCE.dtoToEntity(nextHops);

        assertEquals(nextHops.getHop(), HopMapper.INSTANCE.entityToDto(entity.getHop()));

    }

    @Test
    void fromEntity() {

        setUp();

        WarehouseNextHopsEntity entity= WarehouseNextHopsMapper.INSTANCE.dtoToEntity(nextHops);
        WarehouseNextHops newNextHops= WarehouseNextHopsMapper.INSTANCE.entityToDto(entity);

        assertEquals(entity.getHop(), HopMapper.INSTANCE.dtoToEntity(newNextHops.getHop()));
        assertEquals(entity.getTravelTimeMinutes(), newNextHops.getTraveltimeMins());
    }
}
