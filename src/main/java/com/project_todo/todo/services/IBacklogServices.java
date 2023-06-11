package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.BacklogDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IBacklogServices {

    ResponseEntity add(HttpServletRequest request, BacklogDto backlogDto);

}
