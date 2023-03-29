package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestRecipientRepository {

    @Autowired
    RecipientRepository repository;

    public Recipient TestRecipient;

    @BeforeEach
    void init() {

        TestRecipient= new Recipient().city("Vienna").name("Stefan").country("Austria").postalCode("1120").street("Längenfeldgasse");

    }

    @Test
    public void testDB() {

        RecipientEntity recipientEntity= repository.save(RecipientMapper.INSTANCE.dtoToEntity(TestRecipient));
        Optional<RecipientEntity> optionalRecipientEntity= repository.findById(recipientEntity.getId());

        assert(optionalRecipientEntity.isPresent());

        repository.delete(recipientEntity);

    }
}
