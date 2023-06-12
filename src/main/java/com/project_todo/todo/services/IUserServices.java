package com.project_todo.todo.services;


import com.project_todo.todo.model.dto.UserDto;
import com.project_todo.todo.model.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface IUserServices {
    ResponseEntity singUp(UserDto userDto);
    ResponseEntity signIn(UserDto userDto, HttpSession session);

    List<UserDto> getAllUsers();
    ResponseEntity edit(HttpServletRequest request,UserDto userDto);

    ResponseEntity delete(HttpServletRequest request);
    void generateExcel(HttpServletResponse response) throws Exception;

    ResponseEntity logOut(HttpSession session);

    List<User> listAll();
}
