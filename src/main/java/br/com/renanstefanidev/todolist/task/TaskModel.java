package br.com.renanstefanidev.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Modelo de task para referencia
 * 
 * ID
 * Usuário (ID_USUARIO)
 * Descrição
 * Título
 * Data de início
 * Data de término
 * Prioridade
 * 
 */

@Data
@Entity(name = "tb_tasks")

public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    // Modelo de data convencional (letra T utilizada para separação)
    // yyyy--mm-ddTHH:mm:ss
    
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Verificações relacionadas ao title
    // throws Exception vai definir qual método vai executar a exception criada
    public void setTitle(String title) throws Exception{

        // Criamos a exception dessa forma
        if(title.length() > 50) {
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;

    }

}
