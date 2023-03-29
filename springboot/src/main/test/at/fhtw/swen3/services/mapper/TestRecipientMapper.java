package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecipientMapper {

    Recipient recipient = new Recipient().city("Vienna").name("Stefan").country("Austria").postalCode("1120").street("Längenfeldgasse");

    @Test
    void fromDTO() {

        RecipientEntity entity= RecipientMapper.INSTANCE.dtoToEntity(recipient);

        assertEquals(recipient.getCity(),entity.getCity());
        assertEquals(recipient.getName(),entity.getName());
        assertEquals(recipient.getCountry(),entity.getCountry());
        assertEquals(recipient.getPostalCode(),entity.getPostalCode());
        assertEquals(recipient.getStreet(),entity.getStreet());

    }

    @Test
    void fromEntity() {

        RecipientEntity entity= RecipientMapper.INSTANCE.dtoToEntity(recipient);
        Recipient newRecipient= RecipientMapper.INSTANCE.entityToDto(entity);

        assertEquals(entity.getCity(), newRecipient.getCity());
        assertEquals(entity.getName(), newRecipient.getName());
        assertEquals(entity.getCountry(), newRecipient.getCountry());
        assertEquals(entity.getPostalCode(), newRecipient.getPostalCode());
        assertEquals(entity.getStreet(), newRecipient.getStreet());

    }
}
