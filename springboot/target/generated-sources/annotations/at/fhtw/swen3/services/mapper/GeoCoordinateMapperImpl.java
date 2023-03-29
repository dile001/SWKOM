package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class GeoCoordinateMapperImpl implements GeoCoordinateMapper {

    @Override
    public GeoCoordinate entityToDto(GeoCoordinateEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GeoCoordinate geoCoordinate = new GeoCoordinate();

        geoCoordinate.setLat( entity.getLat() );
        geoCoordinate.setLon( entity.getLon() );

        return geoCoordinate;
    }

    @Override
    public GeoCoordinateEntity dtoToEntity(GeoCoordinate o) {
        if ( o == null ) {
            return null;
        }

        GeoCoordinateEntity.GeoCoordinateEntityBuilder geoCoordinateEntity = GeoCoordinateEntity.builder();

        geoCoordinateEntity.lat( o.getLat() );
        geoCoordinateEntity.lon( o.getLon() );

        return geoCoordinateEntity.build();
    }
}
