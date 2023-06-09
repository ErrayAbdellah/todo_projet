package com.project_todo.todo.services;


import com.project_todo.todo.model.dto.UserDto;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements IUserServices {

    private final UserRepo userRepo ;

    public ResponseEntity signIn(User users , HttpSession session)  {

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
          try {
                  if (userRepo.existsByEmail(users.getEmail().toLowerCase())){
                      Optional<User> userFind = userRepo.findByEmail(users.getEmail().toLowerCase());
                      if(!userFind.isPresent()) {
                          return ResponseEntity.badRequest().body("repoUser is not present");
                      }
                      User dbUser = userFind.get();

                      if (!bcrypt.matches(users.getPassword(),dbUser.getPassword())) {
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

    public ResponseEntity singUp(User users){

        try{
            if (userRepo.existsByEmail(users.getEmail().toLowerCase())){
                return ResponseEntity.badRequest().body("déja exists ");
            }
            else if(users.getPassword().length() < 8){
                return ResponseEntity.badRequest().body("error password > 8 ");
            }
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

            users.setPassword(bcrypt.encode(users.getPassword()));
            users.setEmail(users.getEmail().toLowerCase());
            userRepo.save(users);
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

    public User edit(User user){
        User users = new User() ;
        users.setLastName(user.getName());
        users.setLastName(user.getLastName());
        users.setPassword(user.getPassword());
        userRepo.save(users);
        return null ;
    }

    @Override
    public ResponseEntity delete(User user) {

        Optional<User> userDlt = userRepo.findById(user.getId());

        System.out.println(userDlt);


        return null;
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
