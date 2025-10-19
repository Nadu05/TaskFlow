package springboot.taskflow.payload.taskdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.taskflow.model.taskStatus;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDTO {
    private String taskTitle;
    private String taskDescription;
    private taskStatus taskStatus;
    private Timestamp allocatedTime;
    private Timestamp dueDate;
    private boolean completed;
}
