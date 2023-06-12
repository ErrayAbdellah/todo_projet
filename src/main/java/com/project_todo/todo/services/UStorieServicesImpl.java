package com.project_todo.todo.services;

import com.project_todo.todo.model.dto.UserStoriesDto;
import com.project_todo.todo.model.entity.Sprint;
import com.project_todo.todo.model.entity.User;
import com.project_todo.todo.model.entity.UserStories;
import com.project_todo.todo.repositories.IUStoriesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UStorieServicesImpl implements IUStoriesServices{
    private final IUStoriesRepo repo ;
    @Override
    public List<UserStoriesDto> getSprints() {
        return null;
    }

    @Override
    public ResponseEntity add(HttpServletRequest request, UserStoriesDto userStoriesDto) {
        UserStories userStories = UserStories.toDto(userStoriesDto);
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }
        try{
            User user = (User) request.getSession().getAttribute("USER");
            userStories.setUsers(user);
            repo.save(userStories);
            return ResponseEntity.ok("UserStory added successfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body("error  "+e);
        }
    }

    @Override
    public ResponseEntity update(HttpServletRequest request, UserStoriesDto userStoriesDto, long id) {
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }
        try{
            Optional<UserStories> userStoriesFind = repo.findById(id);
            if (userStoriesFind.isEmpty())
            {
                return ResponseEntity.ok("UserStory not found");
            }

            UserStories userStoriesData = userStoriesFind.get();
            UserStories userStories= UserStories.toDto(userStoriesDto);
            userStoriesData.setName(userStories.getName());
            userStoriesData.setDescription(userStories.getDescription());
            userStoriesData.setBacklog(userStories.getBacklog());

            repo.save(userStoriesData);
            return ResponseEntity.ok("UserStory update is successfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body("error  "+e);
        }
    }

    @Override
    public ResponseEntity delete(HttpServletRequest request, long id) {
        HttpSession session = request.getSession();
        if (session.getAttribute("USER")==null){
            return ResponseEntity.badRequest().body("authenticate !!");
        }
        try{
            repo.deleteById(id);
            return ResponseEntity.ok("UserStory deleted is successfully");
        }catch(Exception e){
            return  ResponseEntity.badRequest().body("error  "+e);
        }
    }

}
