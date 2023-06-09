package com.project_todo.todo.services;


import com.project_todo.todo.model.dto.UserDto;
import com.project_todo.todo.model.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface IUserServices {
    ResponseEntity singUp(User users);
    ResponseEntity signIn(User users, HttpSession session);

    List<UserDto> getAllUsers();
    User edit(User users);

    ResponseEntity delete(User user);
    void generateExcel(HttpServletResponse response) throws Exception;

    ResponseEntity logOut(HttpSession session);
}
