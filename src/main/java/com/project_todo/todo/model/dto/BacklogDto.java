package com.project_todo.todo.model.dto;


import com.project_todo.todo.model.entity.Backlog;
import com.project_todo.todo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BacklogDto {
    private long id;
    private String name;
    private LocalDateTime created_at ;
    private LocalDateTime updated_at;


    public static BacklogDto toDto(Backlog backlog){
        return builder()
                .id(backlog.getId())
                .name(backlog.getName())
                .created_at(backlog.getCreated_at())
                .updated_at(backlog.getUpdated_at())
                .build();
    }
}
