package springboot.taskflow.payload.taskdto;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.taskflow.model.taskStatus;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private long id;
    private String taskTitle;
    private String taskDescription;
    private taskStatus taskStatus;
    private Timestamp dueDate;
    private Timestamp allocatedTime;
    private Boolean completed;
    private Boolean timerActive;


}
