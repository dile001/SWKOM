package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.Optional;

@SpringBootTest
public class TestHopArrivalRepository {

    @Autowired
    HopArrivalRepository repository;

    public HopArrival hopArrival;

    @BeforeEach
    void init() {
        hopArrival = new HopArrival();
        hopArrival.setCode("TEST1234");
        hopArrival.setDateTime(OffsetDateTime.MAX);
        hopArrival.setDescription("TEST");
    }

    @Test
    public void testDb() {

        HopArrivalEntity entity= repository.save(HopArrivalMapper.INSTANCE.dtoToEntity(hopArrival));

        Optional<HopArrivalEntity> optionalErrorEntity= repository.findById(entity.getId());

        assert(optionalErrorEntity.isPresent());

        repository.delete(entity);

    }
}
