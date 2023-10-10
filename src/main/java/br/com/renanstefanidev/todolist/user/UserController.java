package br.com.renanstefanidev.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    /**
     * String (texto)
     * Integer (int)
     * Double (double) Números com casas decimais
     * Float (float) Similar ao double, com diferença em precisão
     * Char (A C) Caracteres
     * Date (data)
     * void () Quando o método não retorna algo
     */

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel) {
        // @RequestBody aponta que UserModel está no body da requisição
        System.out.println(userModel.getUsername());
    }
    
}
