package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestErrorRepository {

    @Autowired
    ErrorRepository repository;

    @Test
    public void testErrorRepository() {

        ErrorEntity entity = repository.save(ErrorMapper.INSTANCE.dtoToEntity(new Error().errorMessage("TEST")));

        Optional<ErrorEntity> optionalErrorEntity = repository.findById(entity.getId());

        assert(optionalErrorEntity.isPresent());

        repository.delete(entity);

    }
}
