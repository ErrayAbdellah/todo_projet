package com.project_todo.todo.model.entity;


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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_realm")
    private Realm realm ;
}
