package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.SprintDto;
import com.project_todo.todo.model.entity.Sprint;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.repositories.ISprintRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SprintServicesImpl implements ISprintServices{

    private final ISprintRepo sprintRepo ;

    @Override
    public List<SprintDto> getSprints() {
        System.out.println(sprintRepo.findAll());
        return null;
    }

    @Override
    public ResponseEntity add(HttpServletRequest request, SprintDto sprintDto) {
        Sprint sprint = Sprint.toDto(sprintDto);
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }
        try{
            User user = (User) request.getSession().getAttribute("USER");
            sprint.setUsers(user);
            sprintRepo.save(sprint);
            return ResponseEntity.ok("Sprint added successfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body("error  "+e);
        }
    }

    @Override
    public ResponseEntity update(HttpServletRequest request ,SprintDto sprintDto,long id) {
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }
        try{
            Optional<Sprint> sprintFind = sprintRepo.findById(id);
            if (sprintFind.isEmpty())
            {
                return ResponseEntity.ok("Sprint not found");
            }
            //User user = (User) request.getSession().getAttribute("USER");

            Sprint sprintData = sprintFind.get();
            Sprint sprint = Sprint.toDto(sprintDto);
            sprintData.setName(sprint.getName());
            sprintData.setRealm(sprint.getRealm());

            sprintRepo.save(sprintData);
            return ResponseEntity.ok("Sprint update is successfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body("error  "+e);
        }
    }

    @Override
    public ResponseEntity delete(HttpServletRequest request ,long  id) {
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }
        try{
            sprintRepo.deleteById(id);
            return ResponseEntity.ok("Sprint deleted is successfully");
        }catch(Exception e){
            return  ResponseEntity.badRequest().body("error  "+e);
        }
    }
}
