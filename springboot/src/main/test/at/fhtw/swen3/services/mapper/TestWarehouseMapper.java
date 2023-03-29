package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWarehouseMapper {

    @Test
    void testFromDTO() {

        Warehouse warehouse= new Warehouse();
        warehouse.setHopType("TEST");
        warehouse.setCode("TEST1234");
        warehouse.setDescription("TEST");
        warehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        warehouse.setProcessingDelayMins(1);
        warehouse.setLocationName("TEST");
        warehouse.setLevel(1);
        warehouse.setNextHops(new LinkedList<WarehouseNextHops>());

        Truck truck= new Truck();
        truck.setCode("TEST");
        truck.setNumberPlate("TEST");
        truck.setDescription("TEST");
        truck.setHopType("TEST");
        truck.setProcessingDelayMins(60);
        truck.setRegionGeoJson("TEST");
        truck.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        truck.setLocationName("TEST");

        warehouse.addNextHopsItem(new WarehouseNextHops().hop(truck));

        WarehouseEntity entity= WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        assertEquals(warehouse.getCode(), entity.getCode());
        assertEquals(warehouse.getDescription(), entity.getDescription());
        assertEquals(warehouse.getHopType(), entity.getHopType());
        assert(warehouse.getLocationCoordinates().getLon() == entity.getLocationCoordinates().getX());
        assert(warehouse.getLocationCoordinates().getLat() == entity.getLocationCoordinates().getY());
        assertEquals(warehouse.getLocationName(), entity.getLocationName());
        assertEquals(warehouse.getProcessingDelayMins(), entity.getProcessingDelayMins());
        assertEquals(warehouse.getLevel(), entity.getLevel());
        assertEquals(warehouse.getNextHops().get(0), WarehouseNextHopsMapper.INSTANCE.entityToDto(entity.getNextHops().get(0)));
    }

    @Test
    void fromEntity() {

        Warehouse warehouse= new Warehouse();
        warehouse.setHopType("TEST");
        warehouse.setCode("TEST1234");
        warehouse.setDescription("TEST");
        warehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        warehouse.setProcessingDelayMins(1);
        warehouse.setLocationName("TEST");
        warehouse.setLevel(1);
        warehouse.setNextHops(new LinkedList<WarehouseNextHops>());

        Truck truck= new Truck();
        truck.setCode("TEST1234");
        truck.setNumberPlate("TEST");
        truck.setDescription("TEST");
        truck.setHopType("TEST");
        truck.setProcessingDelayMins(60);
        truck.setRegionGeoJson("TEST");
        truck.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        truck.setLocationName("TEST");

        warehouse.addNextHopsItem(new WarehouseNextHops().traveltimeMins(3).hop(truck));

        WarehouseEntity entity= WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        Warehouse newWarehouse= WarehouseMapper.INSTANCE.entityToDto(entity);

        assertEquals(entity.getCode(), newWarehouse.getCode());
        assertEquals(entity.getDescription(), newWarehouse.getDescription());
        assertEquals(entity.getHopType(), newWarehouse.getHopType());
        assert(entity.getLocationCoordinates().getX() == newWarehouse.getLocationCoordinates().getLon());
        assert(entity.getLocationCoordinates().getY() == newWarehouse.getLocationCoordinates().getLat());
        assertEquals(entity.getLocationName(), newWarehouse.getLocationName());
        assertEquals(entity.getProcessingDelayMins(), newWarehouse.getProcessingDelayMins());
        assertEquals(entity.getLevel(), newWarehouse.getLevel());
        assertEquals(WarehouseNextHopsMapper.INSTANCE.entityToDto(entity.getNextHops().get(0)), newWarehouse.getNextHops().get(0));
    }
}
