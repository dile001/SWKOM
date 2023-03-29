package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferWarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class TransferWarehouseMapperImpl implements TransferWarehouseMapper {

    @Override
    public Transferwarehouse entityToDto(TransferWarehouseEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Transferwarehouse transferwarehouse = new Transferwarehouse();

        transferwarehouse.locationCoordinates( CoordinateToPointMapper.pointToGeoCoordinate( entity.getLocationCoordinates() ) );
        transferwarehouse.hopType( entity.getHopType() );
        transferwarehouse.code( entity.getCode() );
        transferwarehouse.description( entity.getDescription() );
        transferwarehouse.processingDelayMins( entity.getProcessingDelayMins() );
        transferwarehouse.locationName( entity.getLocationName() );
        transferwarehouse.setRegionGeoJson( entity.getRegionGeoJson() );
        transferwarehouse.setLogisticsPartner( entity.getLogisticsPartner() );
        transferwarehouse.setLogisticsPartnerUrl( entity.getLogisticsPartnerUrl() );

        return transferwarehouse;
    }

    @Override
    public TransferWarehouseEntity dtoToEntity(Transferwarehouse o) {
        if ( o == null ) {
            return null;
        }

        TransferWarehouseEntity.TransferWarehouseEntityBuilder<?, ?> transferWarehouseEntity = TransferWarehouseEntity.builder();

        transferWarehouseEntity.locationCoordinates( CoordinateToPointMapper.geoCoordinateToPoint( o.getLocationCoordinates() ) );
        transferWarehouseEntity.hopType( o.getHopType() );
        transferWarehouseEntity.code( o.getCode() );
        transferWarehouseEntity.description( o.getDescription() );
        transferWarehouseEntity.processingDelayMins( o.getProcessingDelayMins() );
        transferWarehouseEntity.locationName( o.getLocationName() );
        transferWarehouseEntity.regionGeoJson( o.getRegionGeoJson() );
        transferWarehouseEntity.logisticsPartner( o.getLogisticsPartner() );
        transferWarehouseEntity.logisticsPartnerUrl( o.getLogisticsPartnerUrl() );

        return transferWarehouseEntity.build();
    }
}
