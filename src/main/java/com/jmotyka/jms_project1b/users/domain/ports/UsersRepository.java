package com.jmotyka.jms_project1b.users.domain.ports;

import com.jmotyka.jms_project1b.users.domain.entities.User;
import java.util.Optional;

public interface UsersRepository {

    User save(User user);

    Optional<User> getUserByName(String name);

}