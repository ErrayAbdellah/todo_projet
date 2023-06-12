package com.project_todo.todo.model.entity;

import com.project_todo.todo.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id ;

    @Column(nullable = false)
    private String name ;

    @Column(nullable = false)
    private String lastName ;

    private boolean setDelated = false;

    @Email(message = "error",regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email ;

    @Column(nullable = false)

    private String password ;
    @CreationTimestamp
    private LocalDateTime created_at ;
    @UpdateTimestamp
    private LocalDateTime updated_at;

//    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
//    private List<Backlog> backlogsList ;
    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Sprint> sprintList ;
    @OneToMany(mappedBy = "users")
    private List<UserStories> userStoriesList ;
    @OneToMany(mappedBy = "users")
    private List<Realm> realmList ;
    @OneToMany(mappedBy = "users")
    private List<Backlog> backlogsList;
//    @ManyToMany(mappedBy = "usersSprint")
//    private Set<UserStories> userStories = new HashSet<>();

    public static User toDto(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .setDelated(userDto.isSetDelated())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .created_at(userDto.getCreated_at())
                .updated_at(userDto.getUpdated_at())
                .build();
    }
}
