package com.jmotyka.jms_project1b.users.adapters.persistence;

import com.jmotyka.jms_project1b.users.domain.entities.User;
import com.jmotyka.jms_project1b.users.domain.ports.UsersRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject )
public class JpaUsersRepositoryAdapter implements UsersRepository {

    private final JpaUsersRepository usersRepository;
    private final JpaPersistenceUserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        System.out.println("USER ENTITY Z JPA ADAPTERA: " + entity);
        UserEntity savedEntity = usersRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return usersRepository.getUserByName(name)
                .map(userMapper::toDomain);
    }

}