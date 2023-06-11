package com.project_todo.todo.controllers;


import com.project_todo.todo.model.dto.UserDto;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        return services.singUp(userDto);
    }
    @GetMapping(value = "/log-out")
    public ResponseEntity logOut(HttpSession session){

        return services.logOut(session);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity signIn(@RequestBody UserDto userDto,HttpSession session){

       // System.out.println("hello");
        return services.signIn(userDto,session);
        //return  null ;
    }

    @GetMapping(value = "/get-all-data")
    public List<UserDto> getAlldDta(){
        return services.getAllUsers() ;
    }

    @PatchMapping
    public ResponseEntity edit(HttpServletRequest request,@RequestBody UserDto userDto){
        return services.edit(request,userDto);
    }
    @DeleteMapping
    public ResponseEntity delete(HttpServletRequest request){
        return services.delete(request);
    }

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=user.xlsx";
        response.setHeader(headerKey,headerValue);
        services.generateExcel(response);
    }



//    @PostMapping("/destroy")
//    public String destroySession(HttpSession session) {
//       session.setAttribute("name","yassin");
//        return "redirect:/";
//    }

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
