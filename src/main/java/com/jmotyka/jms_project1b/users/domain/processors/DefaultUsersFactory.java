package com.jmotyka.jms_project1b.users.domain.processors;

import com.jmotyka.jms_project1b.commons.IdGenerator;
import com.jmotyka.jms_project1b.commons.SystemTimeProvider;
import com.jmotyka.jms_project1b.commons.TimeProvider;
import com.jmotyka.jms_project1b.users.domain.ports.UsersFactory;
import com.jmotyka.jms_project1b.users.domain.ports.UsersRepository;
import com.jmotyka.jms_project1b.users.domain.ports.UsersService;

public class DefaultUsersFactory implements UsersFactory {

    private static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();

    @Override
    public UsersService usersService(IdGenerator idGenerator, UsersRepository usersRepository) {
        return new UsersProcessor(idGenerator, usersRepository, TIME_PROVIDER);
    }

}
