package com.jmotyka.jms_project1b.users.adapters.rest;

import com.jmotyka.jms_project1b.users.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestUserMapper {

    User toDomain(UserDTO dto);

    UserDTO toDto(User user);

}