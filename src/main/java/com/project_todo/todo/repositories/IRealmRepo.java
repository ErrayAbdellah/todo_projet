package com.project_todo.todo.repositories;


import com.project_todo.todo.model.entity.Realm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRealmRepo extends JpaRepository<Realm,Long> {
}
