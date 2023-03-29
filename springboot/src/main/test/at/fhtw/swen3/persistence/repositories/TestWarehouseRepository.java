package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestWarehouseRepository {

    @Autowired
    WarehouseRepository repository;

    public Warehouse warehouse;

    @BeforeEach
    void init() {
        warehouse= new Warehouse().level(1);
        warehouse.setCode("TEST");
        warehouse.setHopType("TEST");
        warehouse.setCode("ABCD1234");
        warehouse.setDescription("TEST");
        warehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        warehouse.setProcessingDelayMins(1);
        warehouse.setLocationName("TEST");
        warehouse.setLevel(1);
    }

    @Test
    public void testDB() {

        WarehouseEntity warehouseEntity= repository.save(WarehouseMapper.INSTANCE.dtoToEntity(warehouse));

        Optional<WarehouseEntity> optionalWarehouseEntity= repository.findById(warehouseEntity.getId());

        assert(optionalWarehouseEntity.isPresent());

        repository.delete(warehouseEntity);
    }
}
