package springboot.taskflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSession extends JpaRepository<springboot.taskflow.model.TaskSession, Long> {
}
