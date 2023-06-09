package com.project_todo.todo.model.dto;


import com.project_todo.todo.model.entity.Backlog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BacklogDto {
    private long id;
    private String name;
    private Date created_at ;
    private Date updated_at;

    public static BacklogDto toDto(Backlog backlog){
        return builder()
                .id(backlog.getId())
                .name(backlog.getName())
                .build();
    }
}
