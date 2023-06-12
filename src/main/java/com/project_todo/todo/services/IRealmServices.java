package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.RealmDto;
import com.project_todo.todo.model.entity.Realm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IRealmServices {
    ResponseEntity addRealm(HttpServletRequest request, RealmDto realmDto);
}