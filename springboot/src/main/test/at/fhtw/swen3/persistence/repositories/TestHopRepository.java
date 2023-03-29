package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Optional;

@SpringBootTest
public class TestHopRepository {

    @Autowired
    HopRepository hopRepository;

    public Hop hop;
    public Warehouse warehouse;
    public Truck truck;
    public Transferwarehouse transferwarehouse;

    @BeforeEach
    void init(){

        hop= new Hop();
        hop.setCode("TEST");
        hop.setHopType("TEST");
        hop.setDescription("TEST");
        hop.setLocationName("TEST");
        hop.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        hop.setProcessingDelayMins(60);

        warehouse= new Warehouse();
        warehouse.setCode("TEST");
        warehouse.setHopType("TEST");
        warehouse.setCode("ABCD1234");
        warehouse.setDescription("TEST");
        warehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        warehouse.setProcessingDelayMins(1);
        warehouse.setLocationName("TEST");
        warehouse.setLevel(1);
        warehouse.setNextHops(new LinkedList<WarehouseNextHops>());

        truck= new Truck();
        truck.setCode("TEST");
        truck.setNumberPlate("TEST");
        truck.setDescription("TEST");
        truck.setHopType("TEST");
        truck.setProcessingDelayMins(60);
        truck.setRegionGeoJson("TEST");
        truck.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        truck.setLocationName("TEST");

        transferwarehouse= new Transferwarehouse();
        transferwarehouse.setLocationName("TEST");
        transferwarehouse.setCode("TEST");
        transferwarehouse.setRegionGeoJson("TEST");
        transferwarehouse.setLogisticsPartner("TEST");
        transferwarehouse.setLogisticsPartnerUrl("TEST");


    }

    @Test
    public void testDB() {


        HopEntity hopEntity= hopRepository.save(HopMapper.INSTANCE.dtoToEntity(hop));
        WarehouseEntity warehouseEntity= hopRepository.save(WarehouseMapper.INSTANCE.dtoToEntity(warehouse));
        TruckEntity truckEntity= hopRepository.save(TruckMapper.INSTANCE.dtoToEntity(truck));
        TransferWarehouseEntity transferWarehouseEntity= hopRepository.save(TransferWarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse));

        Optional<HopEntity> optionalHopEntity= hopRepository.findById(hopEntity.getId());
        Optional<HopEntity> optionalWarehouseEntity= hopRepository.findById(warehouseEntity.getId());
        Optional<HopEntity> optionalTruckEntity= hopRepository.findById(truckEntity.getId());
        Optional<HopEntity> optionalTransferWarehouseEntity= hopRepository.findById(transferWarehouseEntity.getId());


        assert(optionalHopEntity.isPresent());
        assert(optionalWarehouseEntity.isPresent());
        assert(optionalTruckEntity.isPresent());
        assert(optionalTransferWarehouseEntity.isPresent());

        hopRepository.delete(hopEntity);
        hopRepository.delete(warehouseEntity);
        hopRepository.delete(truckEntity);
        hopRepository.delete(transferWarehouseEntity);


    }

}
