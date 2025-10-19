package springboot.taskflow.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springboot.taskflow.model.UserEntity;
import springboot.taskflow.payload.taskdto.TaskDTO;
import springboot.taskflow.payload.taskdto.TaskResponse;
import springboot.taskflow.payload.taskdto.UpdateTaskDTO;
import springboot.taskflow.services.TaskService;

import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/test")
    public String test() {
        return "tested" ;
    }


    @PostMapping("/taskAdd")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO task , @AuthenticationPrincipal UserEntity user){
        taskService.createTask(task ,user);
        return new ResponseEntity<>("new task Added Successfully" ,HttpStatus.CREATED);
    }
    @GetMapping("/getAllTask")
    public ResponseEntity<List<TaskResponse>> getAllTasks(@AuthenticationPrincipal UserDetails currentUser ){
        List<TaskResponse>  task =taskService.getAllTask( currentUser );
        return  new ResponseEntity<>(task, HttpStatus.OK);
    }
    @PutMapping("/update/{ID}")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody UpdateTaskDTO task , @PathVariable long ID){
        TaskResponse Updated =taskService.updateTask(ID ,task);
        return new ResponseEntity<>(Updated, HttpStatus.OK);

    }

    @DeleteMapping("delete/{ID}")
    public ResponseEntity<String> deleteTask(@PathVariable long ID){

        taskService.deleteTask(ID);
        return new ResponseEntity<>("delete task Successfully",HttpStatus.OK);

    }
}
