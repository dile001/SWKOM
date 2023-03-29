package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import org.junit.jupiter.api.Test;
import at.fhtw.swen3.services.dto.Error;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestErrorMapper {

    private Error error= new Error().errorMessage("TEST");

    @Test
    void fromDTO() {

        ErrorEntity entity= ErrorMapper.INSTANCE.dtoToEntity(error);
        assertEquals(error.getErrorMessage(), entity.getErrorMessage());
    }

    @Test
    void fromEntity() {

        ErrorEntity entity= ErrorMapper.INSTANCE.dtoToEntity(error);
        Error newError= ErrorMapper.INSTANCE.entityToDto(entity);
        assertEquals(entity.getErrorMessage(), newError.getErrorMessage());
    }
}
