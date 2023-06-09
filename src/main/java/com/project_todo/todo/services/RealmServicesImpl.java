package com.project_todo.todo.services;


import com.project_todo.todo.enums.Response;
import com.project_todo.todo.model.dto.RealmDto;
import com.project_todo.todo.model.entity.Realm;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.repositories.RealmRepo;
import com.project_todo.todo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RealmServicesImpl implements IRealmServices{
    private final RealmRepo realmRepo ;
    private EntityManagerFactory entityManagerFactory;



    public ResponseEntity addRealm(HttpServletRequest request, RealmDto realmDto) {
        Realm realm = Realm.toDto(realmDto);
        String message ;
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }

        User user = (User) request.getSession().getAttribute("USER");
        realm.setUsers(user);
        realmRepo.save(realm);
        return ResponseEntity.ok("Realm added successfully");
    }
}
