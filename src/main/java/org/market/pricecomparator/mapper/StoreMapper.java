package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.StoreDTO;
import org.market.pricecomparator.model.entity.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreDTO toStoreDTO(Store store);
    Store toStore(StoreDTO storeDTO);
}
