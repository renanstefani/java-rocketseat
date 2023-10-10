package br.com.renanstefanidev.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// @Data do lombok vai settar todos os getters e setters da class ou acima do atributo de sua escolha
// @Getter e @Setter para especificar apenas um caso necessário
@Data
@Entity(name="tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(unique = true)
    public String username;
    public String name;
    public String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
