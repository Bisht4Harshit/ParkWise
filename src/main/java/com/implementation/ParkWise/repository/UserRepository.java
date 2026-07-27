package com.implementation.ParkWise.repository;

import com.implementation.ParkWise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<User,Integer>{
    Integer id(Integer id);
    Optional<User> findByUsername(String username);
}
