package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import at.fhtw.swen3.services.mapper.TruckMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.mapper.WarehouseNextHopsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class TestWarehouseNextHopsRepository {

    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;
    @Autowired
    HopRepository hopRepository;

    public Warehouse warehouse;
    public WarehouseNextHops warehouseNextHops;
    public Truck truck;

    @BeforeEach
    void init() {

        warehouse= new Warehouse();
        warehouse.setCode("TEST");
        warehouse.setHopType("TEST");
        warehouse.setCode("ABCD1234");
        warehouse.setDescription("TEST");
        warehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        warehouse.setProcessingDelayMins(1);
        warehouse.setLocationName("TEST");
        warehouse.setLevel(1);
        warehouse.setNextHops(new ArrayList<WarehouseNextHops>());

        truck= new Truck();
        truck.setCode("TEST");
        truck.setNumberPlate("TEST");
        truck.setDescription("TEST");
        truck.setHopType("TEST");
        truck.setProcessingDelayMins(60);
        truck.setRegionGeoJson("TEST");
        truck.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        truck.setLocationName("TEST");

        warehouseNextHops= new WarehouseNextHops();
        warehouseNextHops.setTraveltimeMins(12);
        warehouseNextHops.setHop(truck);
    }

    @Test
    public void testDB() {

        WarehouseEntity warehouseEntity= hopRepository.save(WarehouseMapper.INSTANCE.dtoToEntity(warehouse));
        TruckEntity truckEntity= hopRepository.save(TruckMapper.INSTANCE.dtoToEntity(truck));
        WarehouseNextHopsEntity warehouseNextHopsEntity= WarehouseNextHopsMapper.INSTANCE.dtoToEntity(warehouseNextHops);

        warehouseNextHopsEntity.setHop(warehouseEntity);
        warehouseNextHopsEntity.setHop(truckEntity);

        warehouseNextHopsEntity= warehouseNextHopsRepository.save(warehouseNextHopsEntity);

        Optional<WarehouseNextHopsEntity> optionalWarehouseNextHopsEntity= warehouseNextHopsRepository.findById(warehouseNextHopsEntity.getId());

        assert(optionalWarehouseNextHopsEntity.isPresent());

        warehouseNextHopsRepository.delete(warehouseNextHopsEntity);
        hopRepository.delete(truckEntity);
        hopRepository.delete(warehouseEntity);

    }
}
