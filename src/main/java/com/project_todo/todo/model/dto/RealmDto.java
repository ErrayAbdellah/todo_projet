package com.project_todo.todo.model.dto;


import com.project_todo.todo.model.entity.Realm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealmDto {
    private long id;
    private String name;
    private LocalDateTime created_at ;
    private LocalDateTime updated_at;

    public static RealmDto toDto(Realm realm){
        return builder()
                .id(realm.getId())
                .name(realm.getName())
                .created_at(realm.getCreated_at())
                .updated_at(realm.getUpdated_at())
                .build();
    }

}
