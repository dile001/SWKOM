package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestValidator {

    GeoCoordinate badCoordinate, Coordinate;
    Hop badHop, Hop;
    HopArrival HopArrival, badHopArrival;
    NewParcelInfo NewParcelInfo, badNewParcelInfo;
    Parcel Parcel, badParcel;
    TrackingInformation TrackingInfo, badTrackingInfo;
    Warehouse Warehouse, badWarehouse;
    Recipient Recipient, badRecipient;


    @Test
    void testCoordinateValidation(){

        badCoordinate= new GeoCoordinate();
        Coordinate= new GeoCoordinate();
        Coordinate.setLat(10.0);
        Coordinate.setLon(10.0);


        assertDoesNotThrow(() -> Validator.validate(Coordinate));
        assertThrows(ValidationException.class, () -> Validator.validate(badCoordinate));

    }

    @Test
    void testHopValidation(){

        badHop= new Hop();
        Hop= new Hop();
        Hop.setHopType("TEST1");
        Hop.setCode("TEST1");
        Hop.setDescription("TEST1");
        Hop.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        Hop.setProcessingDelayMins(1);
        Hop.setLocationName("TEST1");

        assertDoesNotThrow(() -> Validator.validate(Hop));
        assertThrows(ValidationException.class, () -> Validator.validate(badHop));

    }

    @Test
    void testHopArrivalValidation(){

        badHopArrival=new HopArrival();
        HopArrival= new HopArrival();
        HopArrival.setCode("TEST1");
        HopArrival.setDescription("TEST1");
        HopArrival.setDateTime(OffsetDateTime.MAX);

        assertDoesNotThrow(() -> Validator.validate(HopArrival));
        assertThrows(ValidationException.class, () -> Validator.validate(badHopArrival));
    }

    @Test
    void testNewParcelInfoValidation(){

        badNewParcelInfo= new NewParcelInfo();
        NewParcelInfo= new NewParcelInfo().trackingId("TEST12345");

        assertDoesNotThrow(() -> Validator.validate(NewParcelInfo));
        //assertThrows(ValidationException.class, () -> Validator.validate(badNewParcelInfo));
    }

    @Test
    void testParcelValidation(){

        badParcel= new Parcel();
        Parcel= new Parcel();
        Recipient testRecipient= new Recipient();
        testRecipient.setName("Stefan");
        testRecipient.setCity("Vienna");
        testRecipient.setCountry("Austria");
        testRecipient.setStreet("Längenfeldgasse");
        testRecipient.setPostalCode("1120");
        Parcel.setRecipient(testRecipient);
        Parcel.setSender(testRecipient);
        Parcel.setWeight(11.2f);

        assertDoesNotThrow(() -> Validator.validate(Parcel));
        assertThrows(ValidationException.class, () -> Validator.validate(badParcel));

    }

    @Test
    void testWarehouseValidation(){

        badWarehouse= new Warehouse();
        Warehouse= new Warehouse();
        Warehouse.setHopType("TEST1");
        Warehouse.setCode("TEST1");
        Warehouse.setDescription("TEST1");
        Warehouse.setLocationCoordinates(new GeoCoordinate().lat(10.0).lon(10.0));
        Warehouse.setProcessingDelayMins(1);
        Warehouse.setLocationName("TEST1");
        Warehouse.setLevel(1);
        Warehouse.setNextHops(new LinkedList<WarehouseNextHops>());

        assertDoesNotThrow(() -> Validator.validate(Warehouse));
        assertThrows(ValidationException.class, () -> Validator.validate(badWarehouse));
    }

    @Test
    void testTrackingInfoValidation(){

        badTrackingInfo= new TrackingInformation();
        TrackingInfo= new TrackingInformation().state(TrackingInformation.StateEnum.DELIVERED).futureHops(new ArrayList<HopArrival>()).visitedHops(new ArrayList<HopArrival>());

        assertDoesNotThrow(() -> Validator.validate(TrackingInfo));
        assertThrows(ValidationException.class, () -> Validator.validate(badTrackingInfo));
    }

    @Test
    void testRecipientValidation(){

        badRecipient= new Recipient();
        Recipient= new Recipient();
        Recipient.setName("Stefan");
        Recipient.setCity("Vienna");
        Recipient.setCountry("Austria");
        Recipient.setStreet("Längenfeldgasse");
        Recipient.setPostalCode("1120");

        assertDoesNotThrow(() -> Validator.validate(Recipient));
        assertThrows(ValidationException.class, () -> Validator.validate(badRecipient));
    }
}
