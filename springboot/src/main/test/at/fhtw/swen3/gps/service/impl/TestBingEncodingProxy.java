package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.Address;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.exceptions.badexception.BadAddressException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBingEncodingProxy {

    @Test
    public void testBingEncodingProxy() {

        BingEncodingProxy bingEncodingProxy = new BingEncodingProxy();
        GeoCoordinate geoCoordinate = null;

        try {
            geoCoordinate = bingEncodingProxy.encodeAddress(Address.builder()
                    .country("Österreich")
                    .city("Wien")
                    .street("Längenfeldgasse")
                    .postalCode("1120")
                    .build());
        } catch (BadAddressException e) {
            e.printStackTrace();
        }
        assertNotNull(geoCoordinate);
    }

    @Test
    public void testBingEncodingProxyLatLon() {

        BingEncodingProxy bingEncodingProxy = new BingEncodingProxy();
        GeoCoordinate geoCoordinate = null;

        try {
            geoCoordinate = bingEncodingProxy.encodeAddress(Address.builder()
                    .country("Österreich")
                    .city("Wien")
                    .street("Längenfeldgasse")
                    .postalCode("1120")
                    .build());
        } catch (BadAddressException e) {
            e.printStackTrace();
        }

        assertNotNull(geoCoordinate);

        assertEquals(48.1846892, geoCoordinate.getLat());
        assertEquals(16.3358539, geoCoordinate.getLon());
    }

}
