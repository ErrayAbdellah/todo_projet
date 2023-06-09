package com.project_todo.todo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStoriesDto {
    private long id;
    private String name;

    private Date created_at ;

    private Date updated_at;

    public static UserStoriesDto toDto(UserStoriesDto userStories){
        return UserStoriesDto.builder()
                .id(userStories.getId())
                .name(userStories.getName())
                .build();
    }
}
