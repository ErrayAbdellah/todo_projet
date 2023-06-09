package com.project_todo.todo.controllers;


import com.project_todo.todo.model.dto.UserDto;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class UserController {

    private final IUserServices services;
    @GetMapping(value = "/home")
    public String home(){
        return "Page home";
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity signup(@RequestBody UserDto userDto){
        User users = User.toDto(userDto) ;

        return services.singUp(users);
    }
    @GetMapping(value = "/log-out")
    public ResponseEntity logOut(HttpSession session){

        return services.logOut(session);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity signIn(@RequestBody UserDto userDto,HttpSession session){
        User user = User.toDto(userDto);
       // System.out.println("hello");
        return services.signIn(user,session);
        //return  null ;
    }

    @GetMapping(value = "/get-all-data")
    public List<UserDto> getAlldDta(){
        return services.getAllUsers() ;
    }

    @PutMapping(value = "/edit/{id}")
    public String edit(){
        return null;
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody UserDto userDto){
        User user = User.toDto(userDto);
        return services.delete(user);
    }

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=user.xlsx";
        response.setHeader(headerKey,headerValue);
        services.generateExcel(response);
    }



    @PostMapping("/destroy")
    public String destroySession(HttpSession session) {
       session.setAttribute("name","yassin");
        return "redirect:/";
    }

    @PostMapping("/session")
    public void session(HttpSession  session){
        System.out.println(session.getAttribute("name"));
    }

    @PostMapping("/invalidate")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "Session invalidated";
    }

}
