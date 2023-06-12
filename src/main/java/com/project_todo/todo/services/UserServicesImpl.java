package com.project_todo.todo.services;


import com.project_todo.todo.model.dto.UserDto;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.repositories.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements IUserServices {

    private final IUserRepo userRepo ;

    public ResponseEntity signIn(UserDto userDto , HttpSession session)  {
        User user = User.toDto(userDto);
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
          try {
                  if (userRepo.existsByEmail(user.getEmail().toLowerCase())){
                      Optional<User> userFind = userRepo.findByEmail(user.getEmail().toLowerCase());
                      if(!userFind.isPresent()) {
                          return ResponseEntity.badRequest().body("repoUser is not present");
                      }
                      User dbUser = userFind.get();

                      if (!bcrypt.matches(user.getPassword(),dbUser.getPassword())) {
                          return ResponseEntity.badRequest().body("check your password");
                      }

                      session.setAttribute("USER",dbUser);
                      return ResponseEntity.ok("sign in ^_^ \n");
                  } else {
                      return ResponseEntity.badRequest().body("check your email");
                  }
          }catch (Exception e)
          {
               return ResponseEntity.badRequest().body(e.getMessage());
          }

    }

    public ResponseEntity singUp(UserDto userDto){
        User user = User.toDto(userDto);
        try{
            if (userRepo.existsByEmail(user.getEmail().toLowerCase())){
                return ResponseEntity.badRequest().body("déja exists ");
            }
            else if(user.getPassword().length() < 8){
                return ResponseEntity.badRequest().body("error password > 8 ");
            }
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

            user.setPassword(bcrypt.encode(user.getPassword()));
            user.setEmail(user.getEmail().toLowerCase());
            userRepo.save(user);
            return ResponseEntity.ok("singUp is successfully");

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public List<UserDto> getAllUsers(){
        List<User> userList = new ArrayList<>();
        userList =  userRepo.findAll();

        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (User user:userList){

            if (user.isSetDelated()){
                continue;
            }
            userDtoList.add(UserDto.toDto(user));
        }
        return userDtoList;
    }

    public ResponseEntity edit(HttpServletRequest request ,UserDto userDto){
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }

        try {
            User user = (User) session.getAttribute("USER");
            Optional<User> userDlt = userRepo.findById(user.getId());
            User userDt = User.toDto(userDto);
            User userData = userDlt.get() ;
            userData.setName(userDt.getName());
            userData.setLastName(userDt.getLastName());
//            userData.setPassword(userDt.getPassword());
            userRepo.save(userData);

        }catch (Exception e){
            return ResponseEntity.badRequest().body("error : "+e);
        }
        return null ;
    }

    @Override
    public ResponseEntity delete(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }

        try {
            User user = (User) session.getAttribute("USER");
            Optional<User> userDlt = userRepo.findById(user.getId());
            if(userDlt.isEmpty()){
                return ResponseEntity.badRequest().body("user not found");
            }
            User userDate = userDlt.get();
            userDate.setSetDelated(true);
            userRepo.save(userDate);
            return ResponseEntity.ok("User deleted is successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error : "+e);
        }

    }

    public void generateExcel(HttpServletResponse response) throws Exception {

        List<User> users = userRepo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Courses Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Email");

        int dataRowIndex = 1;

        for (User user : users) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(user.getId());
            dataRow.createCell(1).setCellValue(user.getEmail());
            dataRow.createCell(2).setCellValue(user.getName());
            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    @Override
    public ResponseEntity logOut(HttpSession session) {
        try {
            session.removeAttribute("USER");
            return ResponseEntity.badRequest().body("Bey Bey ");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error"+e);
        }
    }
}
