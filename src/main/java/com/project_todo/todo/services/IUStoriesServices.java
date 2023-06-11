package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.SprintDto;
import com.project_todo.todo.model.dto.UserStoriesDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUStoriesServices {
    List<UserStoriesDto> getSprints();
    ResponseEntity add(HttpServletRequest request, UserStoriesDto userStoriesDto);
    ResponseEntity update(HttpServletRequest request,UserStoriesDto userStoriesDto,long id);
    ResponseEntity delete(HttpServletRequest request, long id );
}
