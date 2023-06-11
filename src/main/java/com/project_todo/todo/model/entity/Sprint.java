package com.project_todo.todo.model.entity;


import com.project_todo.todo.model.dto.SprintDto;
import com.project_todo.todo.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    private String name;

    @CreationTimestamp
    private LocalDateTime created_at ;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "sprint",cascade = CascadeType.ALL)
    private List<UserStories> userStoriesList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "id_realm")
    private Realm realm ;

    public static Sprint toDto(SprintDto sprintDto){
        return Sprint.builder()
                .id(sprintDto.getId())
                .name(sprintDto.getName())
                .users(sprintDto.getUsers())
                .realm(sprintDto.getRealm())
                .created_at(sprintDto.getCreated_at())
                .updated_at(sprintDto.getUpdated_at())
                .build();
    }
}
