package com.project_todo.todo.controllers;

import com.project_todo.todo.model.dto.SprintDto;
import com.project_todo.todo.model.dto.UserStoriesDto;
import com.project_todo.todo.services.IUStoriesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/us")
public class UserStoriesController {
    private final IUStoriesServices services ;

    @PostMapping
    public ResponseEntity add(HttpServletRequest request, @RequestBody UserStoriesDto userStoriesDto){
        return services.add(request,userStoriesDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(HttpServletRequest request , @RequestBody UserStoriesDto userStoriesDto, @PathVariable long id){
        return services.update(request,userStoriesDto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(HttpServletRequest request,@PathVariable long id){
        return services.delete(request,id);
    }

}
