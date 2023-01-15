package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity.GeoCoordinateEntityBuilder;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T18:58:48+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
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

        GeoCoordinateEntityBuilder geoCoordinateEntity = GeoCoordinateEntity.builder();

        geoCoordinateEntity.lat( o.getLat() );
        geoCoordinateEntity.lon( o.getLon() );

        return geoCoordinateEntity.build();
    }
}
