package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.TransferWarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransferWarehouseMapper {

    Transferwarehouse transferwarehouse= new Transferwarehouse().logisticsPartner("LP").logisticsPartnerUrl("LPURL").regionGeoJson("RGJ");

    @Test
    void fromDTO() {

        transferwarehouse.setHopType("TEST");
        transferwarehouse.setCode("TEST1234");
        transferwarehouse.setDescription("TEST");
        transferwarehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        transferwarehouse.setProcessingDelayMins(1);
        transferwarehouse.setLocationName("TEST");


        TransferWarehouseEntity entity= TransferWarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);
        assertEquals(transferwarehouse.getCode(), entity.getCode());
        assertEquals(transferwarehouse.getDescription(), entity.getDescription());
        assertEquals(transferwarehouse.getHopType(), entity.getHopType());
        assert(transferwarehouse.getLocationCoordinates().getLon() == entity.getLocationCoordinates().getX());
        assert(transferwarehouse.getLocationCoordinates().getLat() == entity.getLocationCoordinates().getY());
        assertEquals(transferwarehouse.getLocationName(), entity.getLocationName());
        assertEquals(transferwarehouse.getProcessingDelayMins(), entity.getProcessingDelayMins());
        assertEquals(transferwarehouse.getLogisticsPartnerUrl(), entity.getLogisticsPartnerUrl());
        assertEquals(transferwarehouse.getLogisticsPartner(), entity.getLogisticsPartner());
        assertEquals(transferwarehouse.getRegionGeoJson(), entity.getRegionGeoJson());
    }

    @Test
    void fromEntity() {
        transferwarehouse.setHopType("TEST");
        transferwarehouse.setCode("TEST1234");
        transferwarehouse.setDescription("TEST");
        transferwarehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        transferwarehouse.setProcessingDelayMins(1);
        transferwarehouse.setLocationName("TEST");

        TransferWarehouseEntity entity= TransferWarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);
        Transferwarehouse newWarehouse= TransferWarehouseMapper.INSTANCE.entityToDto(entity);

        assertEquals(entity.getCode(), newWarehouse.getCode());
        assertEquals(entity.getDescription(), newWarehouse.getDescription());
        assertEquals(entity.getHopType(), newWarehouse.getHopType());
        assert(entity.getLocationCoordinates().getX() == newWarehouse.getLocationCoordinates().getLon());
        assert(entity.getLocationCoordinates().getY() == newWarehouse.getLocationCoordinates().getLat());
        assertEquals(entity.getLocationName(), newWarehouse.getLocationName());
        assertEquals(entity.getProcessingDelayMins(), newWarehouse.getProcessingDelayMins());
        assertEquals(entity.getLogisticsPartnerUrl(), newWarehouse.getLogisticsPartnerUrl());
        assertEquals(entity.getLogisticsPartner(), newWarehouse.getLogisticsPartner());
        assertEquals(entity.getRegionGeoJson(), newWarehouse.getRegionGeoJson());

    }
}
