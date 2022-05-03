package com.jmotyka.jms_project1b.users.adapters.persistence;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@NamedQuery(name = UserEntity.GET_BY_USERNAME, query = "select u from User u where u.userName = :username")
@Entity(name = "User")
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "USERNAME")})
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    public static final String GET_BY_USERNAME = "userGetByName";

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "TIMESTAMP")
    private Instant timestamp;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}