package br.com.renanstefanidev.todolist.user;

import lombok.Data;

// @Data do lombok vai settar todos os getters e setters da class ou acima do atributo de sua escolha
// @Getter e @Setter para especificar apenas um caso necessário
@Data
public class UserModel {
    
    public String username;
    public String name;
    public String password;

}
