package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.BacklogDto;
import com.project_todo.todo.model.entity.Backlog;
import com.project_todo.todo.model.entity.Realm;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.repositories.IBacklogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class BacklogServicesImpl implements IBacklogServices{
    private final IBacklogRepo repo ;
    @Override
    public ResponseEntity add(HttpServletRequest request,BacklogDto backlogDto) {
        Backlog realm = Backlog.toDto(backlogDto);
        String message ;
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }

        User user = (User) request.getSession().getAttribute("USER");
        realm.setUsers(user);
        repo.save(realm);
        return ResponseEntity.ok("Realm added successfully");
    }
}
