package com.project_todo.todo.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.List;

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
    @OneToMany(mappedBy = "users")
    private List<Backlog> backlogsList;
    @OneToMany(mappedBy = "users")
    private List<Realm> realmList ;
    @OneToMany(mappedBy = "users")
    private List<Sprint> sprintList ;
    @OneToMany(mappedBy = "users")
    private List<UserStories> userStoriesList ;

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
