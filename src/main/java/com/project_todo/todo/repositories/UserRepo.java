package com.project_todo.todo.repositories;


import com.project_todo.todo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {

    boolean existsByEmail(String lowerCase);
    Optional<User> findByEmail(String email);
}
