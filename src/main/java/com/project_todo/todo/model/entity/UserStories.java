package com.project_todo.todo.model.entity;


import com.project_todo.todo.model.dto.SprintDto;
import com.project_todo.todo.model.dto.UserStoriesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_stories")
public class UserStories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime created_at ;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "id_sprint")
    private Sprint sprint;

    @ManyToOne
    @JoinColumn(name = "id_backlog")
    private Backlog backlog;

//    @ManyToMany
//    @JoinColumn(name = "user_sprint_id")
//    private List<User> user_list ;
//    @ManyToMany
//    @JoinTable(
//        name = "user_user_story",
//        joinColumns = @JoinColumn(name = "user_story_id"),
//        inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> usersSprint = new HashSet<>();

    public static UserStories toDto(UserStoriesDto userStoriesDto){
        return UserStories.builder()
                .id(userStoriesDto.getId())
                .name(userStoriesDto.getName())
                .description(userStoriesDto.getDescription())
                .users(userStoriesDto.getUsers())
                .backlog(userStoriesDto.getBacklog())
                .sprint(userStoriesDto.getSprint())
                .created_at(userStoriesDto.getCreated_at())
                .updated_at(userStoriesDto.getUpdated_at())
                .build();
    }
}
