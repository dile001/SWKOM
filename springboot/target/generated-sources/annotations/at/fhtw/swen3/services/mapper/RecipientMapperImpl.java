package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T12:25:43+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class RecipientMapperImpl implements RecipientMapper {

    @Override
    public Recipient entityToDto(RecipientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Recipient recipient = new Recipient();

        recipient.setName( entity.getName() );
        recipient.setStreet( entity.getStreet() );
        recipient.setPostalCode( entity.getPostalCode() );
        recipient.setCity( entity.getCity() );
        recipient.setCountry( entity.getCountry() );

        return recipient;
    }

    @Override
    public RecipientEntity dtoToEntity(Recipient o) {
        if ( o == null ) {
            return null;
        }

        RecipientEntity.RecipientEntityBuilder recipientEntity = RecipientEntity.builder();

        recipientEntity.name( o.getName() );
        recipientEntity.street( o.getStreet() );
        recipientEntity.postalCode( o.getPostalCode() );
        recipientEntity.city( o.getCity() );
        recipientEntity.country( o.getCountry() );

        return recipientEntity.build();
    }
}
