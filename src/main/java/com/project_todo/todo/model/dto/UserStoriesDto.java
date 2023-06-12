package com.project_todo.todo.model.dto;


import com.project_todo.todo.model.entity.Backlog;
import com.project_todo.todo.model.entity.Sprint;
import com.project_todo.todo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStoriesDto {
    private long id;

    private String name;

    private String description;

    private User users;

    private Backlog backlog ;

    private Sprint sprint ;

    private LocalDateTime created_at ;

    private LocalDateTime updated_at;

    public static UserStoriesDto toDto(UserStoriesDto userStories){
        return UserStoriesDto.builder()
                .id(userStories.getId())
                .name(userStories.getName())
                .description(userStories.getDescription())
                .users(userStories.getUsers())
                .backlog(userStories.getBacklog())
                .sprint(userStories.getSprint())
                .created_at(userStories.getCreated_at())
                .updated_at(userStories.getUpdated_at())
                .build();
    }
}
