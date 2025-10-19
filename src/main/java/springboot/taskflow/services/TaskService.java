package springboot.taskflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import springboot.taskflow.mapper.TaskMappers;
import springboot.taskflow.model.TaskEntity;
import springboot.taskflow.model.UserEntity;
import springboot.taskflow.payload.taskdto.TaskDTO;
import springboot.taskflow.payload.taskdto.TaskResponse;
import springboot.taskflow.payload.taskdto.UpdateTaskDTO;
import springboot.taskflow.repositories.TaskRepository;
import springboot.taskflow.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private  final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public void createTask(TaskDTO task , UserDetails user) {
        UserEntity userEntity =findUserByDetails(user) ;
        TaskEntity newTask = TaskMappers.toTaskEntity(task ,userEntity);
        taskRepository.save(newTask);

    }


    public List<TaskResponse> getAllTask(UserDetails currentUser) {
        UserEntity user=(UserEntity)currentUser;

       return taskRepository.findAllByUser(user)
               .stream()
               .map(TaskMappers ::toTaskResponse)
               .collect(Collectors.toList());
    }

    public TaskResponse updateTask(long id, UpdateTaskDTO request) {

        TaskEntity existingTask= findTaskByID(id);

        existingTask.setTaskTitle(request.getTaskTitle());
        existingTask.setTaskDescription(request.getTaskDescription());
        existingTask.setTaskStatus(request.getTaskStatus());
        existingTask.setDueDate(request.getDueDate());
        existingTask.setCompleted(request.isCompleted());

        TaskEntity updatedTask = taskRepository.save(existingTask);
        return TaskMappers.toTaskResponse(updatedTask);
    }
    private TaskEntity findTaskByID(long id) {
        return taskRepository.findById(id);
    }


    private UserEntity findUserByDetails(UserDetails userDetails) {
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + userDetails.getUsername()));
    }

    public void deleteTask(long id) {
        TaskEntity deleteTask = taskRepository.findById(id);
        taskRepository.delete(deleteTask);
    }
}
