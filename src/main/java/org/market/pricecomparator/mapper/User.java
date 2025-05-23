package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface User {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
