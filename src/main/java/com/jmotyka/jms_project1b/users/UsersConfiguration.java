package com.jmotyka.jms_project1b.users;

import com.jmotyka.jms_project1b.commons.IdGenerator;
import com.jmotyka.jms_project1b.users.domain.ports.UsersFactory;
import com.jmotyka.jms_project1b.users.domain.ports.UsersRepository;
import com.jmotyka.jms_project1b.users.domain.ports.UsersService;
import com.jmotyka.jms_project1b.users.domain.processors.DefaultUsersFactory;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class UsersConfiguration {

    private static final UsersFactory USERS_FACTORY = new DefaultUsersFactory();

/*    @Singleton
    @Produces
    public JpaPersistenceUserMapper jpaPersistenceUserMapper(){
        return Mappers.getMapper(JpaPersistenceUserMapper.class);
    }*/

    @Singleton
    @Produces
    public UsersService usersService(IdGenerator idGenerator, UsersRepository usersRepository){
        return USERS_FACTORY.usersService(idGenerator, usersRepository);
    }

/*    @Singleton
    @Produces
    public RestUserMapper restUserMapper(){
        return Mappers.getMapper(RestUserMapper.class);
    }*/

}
