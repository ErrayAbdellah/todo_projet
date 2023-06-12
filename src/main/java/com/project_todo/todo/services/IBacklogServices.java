package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.BacklogDto;
import com.project_todo.todo.model.entity.Backlog;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IBacklogServices {

    ResponseEntity add(HttpServletRequest request, BacklogDto backlogDto);

    List<Backlog> getbacklogs();
}
