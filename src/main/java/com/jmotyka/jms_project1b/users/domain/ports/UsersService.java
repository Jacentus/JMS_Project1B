package com.jmotyka.jms_project1b.users.domain.ports;

import com.jmotyka.jms_project1b.users.domain.entities.User;

import java.util.Optional;

public interface UsersService {

    User createUser(String userName);

    Optional<User> getByUsername(String userName);

}
