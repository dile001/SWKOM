package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class WarehouseMapperImpl implements WarehouseMapper {

    @Override
    public Warehouse entityToDto(WarehouseEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.locationCoordinates( CoordinateToPointMapper.pointToGeoCoordinate( entity.getLocationCoordinates() ) );
        warehouse.setNextHops( WarehouseMapper.nextHopsEntityToDto( entity.getNextHops() ) );
        warehouse.hopType( entity.getHopType() );
        warehouse.code( entity.getCode() );
        warehouse.description( entity.getDescription() );
        warehouse.processingDelayMins( entity.getProcessingDelayMins() );
        warehouse.locationName( entity.getLocationName() );
        warehouse.setLevel( entity.getLevel() );

        return warehouse;
    }

    @Override
    public WarehouseEntity dtoToEntity(Warehouse o) {
        if ( o == null ) {
            return null;
        }

        WarehouseEntity.WarehouseEntityBuilder<?, ?> warehouseEntity = WarehouseEntity.builder();

        warehouseEntity.locationCoordinates( CoordinateToPointMapper.geoCoordinateToPoint( o.getLocationCoordinates() ) );
        warehouseEntity.nextHops( WarehouseMapper.nextHopsDtoToEntity( o.getNextHops() ) );
        warehouseEntity.hopType( o.getHopType() );
        warehouseEntity.code( o.getCode() );
        warehouseEntity.description( o.getDescription() );
        warehouseEntity.processingDelayMins( o.getProcessingDelayMins() );
        warehouseEntity.locationName( o.getLocationName() );
        warehouseEntity.level( o.getLevel() );

        return warehouseEntity.build();
    }
}
