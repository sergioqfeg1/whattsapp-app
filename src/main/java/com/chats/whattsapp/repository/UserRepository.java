package com.chats.whattsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chats.whattsapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.fullName LIKE %?1% OR u.email LIKE %?1%")
    List<User> searchUser(String query);

}
