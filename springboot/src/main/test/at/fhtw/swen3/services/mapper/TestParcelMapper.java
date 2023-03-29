package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParcelMapper {

    NewParcelInfo newParcelInfo;
    Recipient recipient;
    Parcel parcel;
    TrackingInformation trackingInformation;

    void setUp(){
        newParcelInfo= new NewParcelInfo().trackingId("TEST");

        recipient= new Recipient();
        recipient.setName("Hallo");
        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setStreet("Längenfeldgasse");
        recipient.setPostalCode("1120");
        parcel= new Parcel().weight(10.3f).sender(recipient).recipient(recipient);

        trackingInformation= new TrackingInformation().state(TrackingInformation.StateEnum.DELIVERED).visitedHops(new ArrayList<HopArrival>()).futureHops(new ArrayList<HopArrival>());
        trackingInformation.addFutureHopsItem(new HopArrival().code("TEST"));
        trackingInformation.addVisitedHopsItem(new HopArrival().code("TEST"));
    }

    @Test
    void testFrom() {

        setUp();

        ParcelEntity entity= ParcelMapper.INSTANCE.dtoToEntity(newParcelInfo, parcel, trackingInformation);

        assertEquals(newParcelInfo.getTrackingId(), entity.getTrackingId());
        assert(parcel.getWeight()==entity.getWeight());
        assertEquals(parcel.getSender(), RecipientMapper.INSTANCE.entityToDto(entity.getSender()));
        assertEquals(parcel.getRecipient(), RecipientMapper.INSTANCE.entityToDto(entity.getRecipient()));
        assertEquals(trackingInformation.getState(), entity.getState());
        assertEquals(trackingInformation.getVisitedHops().get(0), HopArrivalMapper.INSTANCE.entityToDto(entity.getVisitedHops().get(0)));
        assertEquals(trackingInformation.getFutureHops().get(0), HopArrivalMapper.INSTANCE.entityToDto(entity.getVisitedHops().get(0)));


    }
}
