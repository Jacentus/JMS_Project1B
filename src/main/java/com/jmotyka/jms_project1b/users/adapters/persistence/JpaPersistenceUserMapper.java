package com.jmotyka.jms_project1b.users.adapters.persistence;

import com.jmotyka.jms_project1b.users.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceUserMapper {

    UserEntity toEntity(User user);
    User toDomain(UserEntity entity);

}
