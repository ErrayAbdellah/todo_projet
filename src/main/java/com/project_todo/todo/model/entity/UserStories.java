package com.project_todo.todo.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    private LocalDateTime created_at ;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sprint")
    private Sprint sprint;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_backlog")
    private Backlog backlog;
}
