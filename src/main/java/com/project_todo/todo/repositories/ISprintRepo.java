package com.project_todo.todo.repositories;

import com.project_todo.todo.model.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISprintRepo extends JpaRepository<Sprint,Long> {

}
