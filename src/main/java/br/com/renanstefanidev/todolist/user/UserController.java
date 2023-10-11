package br.com.renanstefanidev.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modificadores:
 * public
 * private
 * protected
 */

@RestController
// Define o tipo de controller no spring (@Controller ou @RestController)

@RequestMapping("/user")
// Define a rota do método

public class UserController {
    
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // @RequestBody aponta que UserModel está no body da requisição
        
        var user = this.userRepository.findByUsername(userModel.getUsername());
        
        if(user != null) {
            System.out.println("Usuário já existe");
            // Mensagem de erro
            // Status Code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        // Criando Hash para senha com Bcrypt Lib
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        
        userModel.setPassword(passwordHashred);

        // User criado
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }




    /**
     * String (texto)
     * Integer (int)
     * Double (double) Números com casas decimais
     * Float (float) Similar ao double, com diferença em precisão
     * Char (A C) Caracteres
     * Date (data)
     * void () Quando o método não retorna algo
     */
    
    
}
