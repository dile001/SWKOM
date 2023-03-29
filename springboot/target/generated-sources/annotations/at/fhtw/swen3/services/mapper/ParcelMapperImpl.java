package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public TrackingInformation trackingInformationEntityToDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TrackingInformation trackingInformation = new TrackingInformation();

        trackingInformation.setState( entity.getState() );
        trackingInformation.setVisitedHops( ParcelMapper.hopArrivalListEntityToDto( entity.getVisitedHops() ) );
        trackingInformation.setFutureHops( ParcelMapper.hopArrivalListEntityToDto( entity.getFutureHops() ) );

        return trackingInformation;
    }

    @Override
    public Parcel entityToDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Parcel parcel = new Parcel();

        parcel.setSender( recipientEntityToRecipient( entity.getSender() ) );
        parcel.setRecipient( recipientEntityToRecipient( entity.getRecipient() ) );
        parcel.setWeight( entity.getWeight() );

        return parcel;
    }

    @Override
    public ParcelEntity dtoToEntity(NewParcelInfo newParcelInfo, Parcel parcel, TrackingInformation trackingInformation) {
        if ( newParcelInfo == null && parcel == null && trackingInformation == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        if ( newParcelInfo != null ) {
            parcelEntity.trackingId( newParcelInfo.getTrackingId() );
        }
        if ( parcel != null ) {
            parcelEntity.weight( parcel.getWeight() );
            parcelEntity.recipient( recipientToRecipientEntity( parcel.getRecipient() ) );
            parcelEntity.sender( recipientToRecipientEntity( parcel.getSender() ) );
        }
        if ( trackingInformation != null ) {
            parcelEntity.state( trackingInformation.getState() );
            parcelEntity.visitedHops( ParcelMapper.hopArrivalListDtoToEntity( trackingInformation.getVisitedHops() ) );
            parcelEntity.futureHops( ParcelMapper.hopArrivalListDtoToEntity( trackingInformation.getFutureHops() ) );
        }

        return parcelEntity.build();
    }

    protected Recipient recipientEntityToRecipient(RecipientEntity recipientEntity) {
        if ( recipientEntity == null ) {
            return null;
        }

        Recipient recipient = new Recipient();

        recipient.setName( recipientEntity.getName() );
        recipient.setStreet( recipientEntity.getStreet() );
        recipient.setPostalCode( recipientEntity.getPostalCode() );
        recipient.setCity( recipientEntity.getCity() );
        recipient.setCountry( recipientEntity.getCountry() );

        return recipient;
    }

    protected RecipientEntity recipientToRecipientEntity(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }

        RecipientEntity.RecipientEntityBuilder recipientEntity = RecipientEntity.builder();

        recipientEntity.name( recipient.getName() );
        recipientEntity.street( recipient.getStreet() );
        recipientEntity.postalCode( recipient.getPostalCode() );
        recipientEntity.city( recipient.getCity() );
        recipientEntity.country( recipient.getCountry() );

        return recipientEntity.build();
    }
}
