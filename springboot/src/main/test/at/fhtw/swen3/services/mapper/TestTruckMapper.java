package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTruckMapper {

    Truck truck= new Truck().numberPlate("TEST").regionGeoJson("RGJ");

    @Test
    void fromDTO() {

        truck.setCode("TEST");
        truck.setNumberPlate("TEST");
        truck.setDescription("TEST");
        truck.setHopType("TEST");
        truck.setProcessingDelayMins(60);
        truck.setRegionGeoJson("TEST");
        truck.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        truck.setLocationName("Vienna");

        TruckEntity entity= TruckMapper.INSTANCE.dtoToEntity(truck);

        assertEquals(truck.getCode(), entity.getCode());
        assertEquals(truck.getDescription(), entity.getDescription());
        assertEquals(truck.getHopType(), entity.getHopType());
        assert(truck.getLocationCoordinates().getLon() == entity.getLocationCoordinates().getX());
        assert(truck.getLocationCoordinates().getLat() == entity.getLocationCoordinates().getY());
        assertEquals(truck.getLocationName(), entity.getLocationName());
        assertEquals(truck.getProcessingDelayMins(), entity.getProcessingDelayMins());
        assertEquals(truck.getRegionGeoJson(), entity.getRegionGeoJson());
        assertEquals(truck.getNumberPlate(), entity.getNumberPlate());
    }

    @Test
    void fromEntity() {

        truck.setCode("TEST");
        truck.setNumberPlate("TEST");
        truck.setDescription("TEST");
        truck.setHopType("TEST");
        truck.setProcessingDelayMins(60);
        truck.setRegionGeoJson("TEST");
        truck.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        truck.setLocationName("Vienna");

        TruckEntity entity= TruckMapper.INSTANCE.dtoToEntity(truck);
        Truck newTruck= TruckMapper.INSTANCE.entityToDto(entity);

        assertEquals(entity.getCode(), newTruck.getCode());
        assertEquals(entity.getDescription(), newTruck.getDescription());
        assertEquals(entity.getHopType(), newTruck.getHopType());
        assert(entity.getLocationCoordinates().getX() == newTruck.getLocationCoordinates().getLon());
        assert(entity.getLocationCoordinates().getY() == newTruck.getLocationCoordinates().getLat());
        assertEquals(entity.getLocationName(), newTruck.getLocationName());
        assertEquals(entity.getProcessingDelayMins(), newTruck.getProcessingDelayMins());
        assertEquals(entity.getRegionGeoJson(), newTruck.getRegionGeoJson());
        assertEquals(entity.getNumberPlate(), newTruck.getNumberPlate());
    }
}
