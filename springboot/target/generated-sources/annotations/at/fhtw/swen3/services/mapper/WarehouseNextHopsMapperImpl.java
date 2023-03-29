package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class WarehouseNextHopsMapperImpl implements WarehouseNextHopsMapper {

    @Override
    public WarehouseNextHops entityToDto(WarehouseNextHopsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();

        warehouseNextHops.setHop( WarehouseNextHopsMapper.hopEntityToHop( entity.getHop() ) );

        return warehouseNextHops;
    }

    @Override
    public WarehouseNextHopsEntity dtoToEntity(WarehouseNextHops o) {
        if ( o == null ) {
            return null;
        }

        WarehouseNextHopsEntity.WarehouseNextHopsEntityBuilder warehouseNextHopsEntity = WarehouseNextHopsEntity.builder();

        warehouseNextHopsEntity.hop( WarehouseNextHopsMapper.hopToHopEntity( o.getHop() ) );

        return warehouseNextHopsEntity.build();
    }
}
