package com.jmotyka.jms_project1b.users.adapters.persistence;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton //w beans.xml dałem annotated
@RequiredArgsConstructor
public class JpaUsersRepository { // to do poprawki wszystko po zajęciach z hibernate'a

    @Setter
    @PersistenceContext(unitName = "projectBdb")
    private EntityManager entityManager;

    @Transactional // dodałem
    public UserEntity save(UserEntity userEntity){
        //entityManager.getTransaction().begin();
        System.out.println("USER ENTITY Z REPOSITORY: " + userEntity);
        entityManager.persist(userEntity);
        //entityManager.flush();
        return userEntity;
    }

    public Optional<UserEntity> getUserByName(String username){
        UserEntity userEntity = entityManager.createQuery("select u from User u where u.userName = :name ", UserEntity.class)
                .setParameter("name", username)
                .getSingleResult();
        return Optional.of(userEntity);
    }

}
