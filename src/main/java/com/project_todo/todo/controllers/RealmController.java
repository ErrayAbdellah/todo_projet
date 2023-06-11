package com.project_todo.todo.controllers;

import com.project_todo.todo.model.dto.RealmDto;
import com.project_todo.todo.model.entity.Realm;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.services.IRealmServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/realm")
public class RealmController {

    private final IRealmServices services ;
    @GetMapping
    public RealmDto getRealm(){

        return null;
    }
    @PostMapping(value = "/save")
    public ResponseEntity saveRealm(@RequestBody RealmDto realmDto, HttpServletRequest request){
        return services.addRealm(request,realmDto);
    }


}
