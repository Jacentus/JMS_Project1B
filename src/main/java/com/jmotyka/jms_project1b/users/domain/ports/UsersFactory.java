package com.jmotyka.jms_project1b.users.domain.ports;

import com.jmotyka.jms_project1b.commons.IdGenerator;

public interface UsersFactory {

    UsersService usersService(IdGenerator idGenerator, UsersRepository usersRepository);

}