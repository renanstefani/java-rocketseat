package br.com.renanstefanidev.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    
    // Resgatar Tasks somente do usuário
    List<TaskModel> findByIdUser(UUID idUser);
}
