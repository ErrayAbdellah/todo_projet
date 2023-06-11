package com.project_todo.todo.model.entity;


import com.project_todo.todo.model.dto.BacklogDto;
import com.project_todo.todo.model.dto.RealmDto;
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
@Table(name = "backlogs")
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime created_at ;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "backlog",cascade = CascadeType.ALL)
    private List<UserStories> userStoriesList ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
    public static Backlog toDto(BacklogDto backlogDto){
        return builder()
                .id(backlogDto.getId())
                .name(backlogDto.getName())
                .users(User.builder().build())
                .created_at(backlogDto.getCreated_at())
                .updated_at(backlogDto.getUpdated_at())
                .build();
    }

}
