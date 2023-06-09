package com.project_todo.todo.model.dto;


import com.project_todo.todo.model.entity.Sprint;
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
public class SprintDto {
    private long id;

    private String name;

    private LocalDateTime created_at ;

    private LocalDateTime updated_at;

    public static SprintDto toDto(Sprint sprint){
        return SprintDto.builder()
                .id(sprint.getId())
                .name(sprint.getName())
                .created_at(sprint.getCreated_at())
                .updated_at(sprint.getUpdated_at())
                .build();
    }

}
