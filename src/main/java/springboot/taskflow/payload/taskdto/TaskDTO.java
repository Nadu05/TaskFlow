package springboot.taskflow.payload.taskdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.taskflow.model.taskStatus;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String taskTitle;
    private String taskDescription;
    private taskStatus taskStatus;
    private Timestamp allocatedTime;
    private Timestamp dueDate;

}
