package springboot.taskflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.taskflow.model.TaskEntity;
import springboot.taskflow.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllByUser(UserEntity user);
    TaskEntity findById(long id);


}
