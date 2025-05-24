package org.market.pricecomparator.mapper;

import org.mapstruct.Mapper;
import org.market.pricecomparator.dto.UserDTO;
import org.market.pricecomparator.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
