package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class TruckMapperImpl implements TruckMapper {

    @Override
    public Truck entityToDto(TruckEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Truck truck = new Truck();

        truck.locationCoordinates( CoordinateToPointMapper.pointToGeoCoordinate( entity.getLocationCoordinates() ) );
        truck.hopType( entity.getHopType() );
        truck.code( entity.getCode() );
        truck.description( entity.getDescription() );
        truck.processingDelayMins( entity.getProcessingDelayMins() );
        truck.locationName( entity.getLocationName() );
        truck.setRegionGeoJson( entity.getRegionGeoJson() );
        truck.setNumberPlate( entity.getNumberPlate() );

        return truck;
    }

    @Override
    public TruckEntity dtoToEntity(Truck o) {
        if ( o == null ) {
            return null;
        }

        TruckEntity.TruckEntityBuilder<?, ?> truckEntity = TruckEntity.builder();

        truckEntity.locationCoordinates( CoordinateToPointMapper.geoCoordinateToPoint( o.getLocationCoordinates() ) );
        truckEntity.hopType( o.getHopType() );
        truckEntity.code( o.getCode() );
        truckEntity.description( o.getDescription() );
        truckEntity.processingDelayMins( o.getProcessingDelayMins() );
        truckEntity.locationName( o.getLocationName() );
        truckEntity.regionGeoJson( o.getRegionGeoJson() );
        truckEntity.numberPlate( o.getNumberPlate() );

        return truckEntity.build();
    }
}
