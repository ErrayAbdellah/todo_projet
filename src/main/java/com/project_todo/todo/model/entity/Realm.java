package com.project_todo.todo.model.entity;


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
@Table(name = "realm")
public class Realm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime created_at ;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "realm",cascade = CascadeType.ALL)
    private List<Sprint> sprintList ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    public static Realm toDto(RealmDto realmDto){
        return builder()
                .id(realmDto.getId())
                .name(realmDto.getName())
                .users(User.builder().build())
                .created_at(realmDto.getCreated_at())
                .updated_at(realmDto.getUpdated_at())
                .build();
    }
}