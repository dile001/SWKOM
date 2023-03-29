package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:51:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class HopArrivalMapperImpl implements HopArrivalMapper {

    @Override
    public HopArrival entityToDto(HopArrivalEntity entity) {
        if ( entity == null ) {
            return null;
        }

        HopArrival hopArrival = new HopArrival();

        hopArrival.setCode( entity.getCode() );
        hopArrival.setDescription( entity.getDescription() );
        hopArrival.setDateTime( entity.getDateTime() );

        return hopArrival;
    }

    @Override
    public HopArrivalEntity dtoToEntity(HopArrival o) {
        if ( o == null ) {
            return null;
        }

        HopArrivalEntity.HopArrivalEntityBuilder hopArrivalEntity = HopArrivalEntity.builder();

        hopArrivalEntity.code( o.getCode() );
        hopArrivalEntity.description( o.getDescription() );
        hopArrivalEntity.dateTime( o.getDateTime() );

        return hopArrivalEntity.build();
    }
}
