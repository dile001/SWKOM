package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class HopMapperImpl implements HopMapper {

    @Override
    public Hop entityToDto(HopEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Hop hop = new Hop();

        hop.setLocationCoordinates( CoordinateToPointMapper.pointToGeoCoordinate( entity.getLocationCoordinates() ) );
        hop.setHopType( entity.getHopType() );
        hop.setCode( entity.getCode() );
        hop.setDescription( entity.getDescription() );
        hop.setProcessingDelayMins( entity.getProcessingDelayMins() );
        hop.setLocationName( entity.getLocationName() );

        return hop;
    }

    @Override
    public HopEntity dtoToEntity(Hop o) {
        if ( o == null ) {
            return null;
        }

        HopEntity.HopEntityBuilder<?, ?> hopEntity = HopEntity.builder();

        hopEntity.locationCoordinates( CoordinateToPointMapper.geoCoordinateToPoint( o.getLocationCoordinates() ) );
        hopEntity.hopType( o.getHopType() );
        hopEntity.code( o.getCode() );
        hopEntity.description( o.getDescription() );
        hopEntity.processingDelayMins( o.getProcessingDelayMins() );
        hopEntity.locationName( o.getLocationName() );

        return hopEntity.build();
    }
}
