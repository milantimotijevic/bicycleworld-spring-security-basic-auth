package com.bicycleworld.dao;

import com.bicycleworld.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User getOneByUsername(String username);
}
