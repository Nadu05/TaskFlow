package springboot.taskflow.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( nullable = false)
    private String taskTitle;

    @Column
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private taskStatus taskStatus;

    @Column
    private Timestamp dueDate;

    @Column
    private Timestamp allocatedTime;

    @Column
    private Boolean completed;

    @Column
    private Boolean timerActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;


}

