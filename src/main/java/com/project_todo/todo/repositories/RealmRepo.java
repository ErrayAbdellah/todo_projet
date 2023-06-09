package com.project_todo.todo.repositories;


import com.project_todo.todo.model.entity.Realm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmRepo extends JpaRepository<Realm,Long> {
}
