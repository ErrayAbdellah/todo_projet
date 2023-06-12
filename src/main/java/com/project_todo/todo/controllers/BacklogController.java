package com.project_todo.todo.controllers;

import com.project_todo.todo.model.dto.BacklogDto;
import com.project_todo.todo.model.entity.Backlog;
import com.project_todo.todo.services.IBacklogServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backlog")
public class BacklogController {
    private final IBacklogServices services;

    @PostMapping
    public ResponseEntity add(HttpServletRequest request, @RequestBody BacklogDto backlogDto){
        return services.add(request,backlogDto);
    }

    @GetMapping
    public List<Backlog> getbacklog(){
        return services.getbacklogs();
    }
}
