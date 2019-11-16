package com.wolfpack.vridgeapi.repository;

import com.wolfpack.vridgeapi.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById (int userId);
}
