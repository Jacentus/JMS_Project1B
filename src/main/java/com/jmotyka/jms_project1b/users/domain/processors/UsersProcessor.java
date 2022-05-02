package com.jmotyka.jms_project1b.users.domain.processors;

import com.jmotyka.jms_project1b.commons.IdGenerator;
import com.jmotyka.jms_project1b.commons.TimeProvider;
import com.jmotyka.jms_project1b.users.domain.entities.User;
import com.jmotyka.jms_project1b.users.domain.ports.UsersRepository;
import com.jmotyka.jms_project1b.users.domain.ports.UsersService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsersProcessor implements UsersService {

    private final IdGenerator idGenerator;
    private final UsersRepository usersRepository;
    private final TimeProvider timeProvider;

    @Override
    public User createUser(String userName) {
        User user = User.builder()
                .id(idGenerator.getNext())
                .userName(userName)
                .timestamp(timeProvider.getTimestamp())
                .build();
        System.out.println("USER FROM USER SERVICE: " + user);
        return usersRepository.save(user); //validate User!!
    }

    @Override
    public Optional<User> getByUsername(String userName){
        return usersRepository.getUserByName(userName);
    }

}