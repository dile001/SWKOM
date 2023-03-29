package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestGeoCoordinateRepository {

    @Autowired
    GeoCoordinateRepository repository;

    @Test
    public void testDb() {

        GeoCoordinateEntity entity= repository.save(GeoCoordinateMapper.INSTANCE.dtoToEntity(new GeoCoordinate().lat(10.0).lon(10.0)));

        Optional<GeoCoordinateEntity> optionalErrorEntity= repository.findById(entity.getId());

        assert(optionalErrorEntity.isPresent());

        repository.delete(entity);
    }
}
