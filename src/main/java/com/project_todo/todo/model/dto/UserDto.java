package com.project_todo.todo.model.dto;


import com.project_todo.todo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long id ;
    private String name ;
    private String lastName ;
    private boolean setDelated = false;
    private String email ;
    private String password ;
    private LocalDateTime created_at ;
    private LocalDateTime updated_at;

    public static UserDto toDto(User users){
        return UserDto.builder()
                .id(users.getId())
                .name(users.getName())
                .lastName(users.getLastName())
                .setDelated(users.isSetDelated())
                .email(users.getEmail())
                .password(users.getPassword())
                .created_at(users.getCreated_at())
                .updated_at(users.getUpdated_at())
                .build();
    }
}
