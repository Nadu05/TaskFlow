package springboot.taskflow.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import springboot.taskflow.model.TaskEntity;
import springboot.taskflow.model.UserEntity;
import springboot.taskflow.payload.taskdto.TaskDTO;
import springboot.taskflow.payload.taskdto.TaskResponse;

public class TaskMappers {
    public static TaskEntity toTaskEntity(TaskDTO taskDTO, UserEntity user) {
    return TaskEntity.builder()
            .taskTitle(taskDTO.getTaskTitle())
            .taskDescription(taskDTO.getTaskDescription())
            .taskStatus(taskDTO.getTaskStatus())
            .dueDate(taskDTO.getDueDate())
            .completed(false)
            .timerActive(false)
            .user(user)
            .build();
}

    public static TaskResponse toTaskResponse(TaskEntity taskEntity) {
        return TaskResponse.builder()
                .id(taskEntity.getId())
                .taskTitle(taskEntity.getTaskTitle())
                .taskDescription(taskEntity.getTaskDescription())
                .taskStatus(taskEntity.getTaskStatus())
                .dueDate(taskEntity.getDueDate())
                .allocatedTime(taskEntity.getAllocatedTime())
                .completed(taskEntity.getCompleted())
                .timerActive(taskEntity.getTimerActive())
                .build();
    }
}
