package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.SprintDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ISprintServices {
    List<SprintDto> getSprints();
    ResponseEntity add(HttpServletRequest request,SprintDto sprintDto);
    ResponseEntity update(HttpServletRequest request,SprintDto sprintDto,long id);
    ResponseEntity delete(HttpServletRequest request, long id );
}
