package br.com.renanstefanidev.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.renanstefanidev.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Toda class que precisamos que o Spring controle precisamos utilizar a devida annotation ("@Component, @RestController por exemplo")
@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                // Resgatar dados da autenticação (usuário e senha)
                var authorization = request.getHeader("Authorization");
                
                // substring "recorta" a string, nesse caso apontamos a length de Basic e com trim() removemos espaços
                var authEncoded = authorization.substring("Basic".length()).trim();
                
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                
                var authString = new String(authDecode);
                
                // Dividindo resultado do decode
                // ["Renan S", "111111"]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];

                // Validar usuário
                var user = this.userRepository.findByUsername(username);

                if (user == null) {

                    response.sendError(401);

                } else {
                    
                    // Validar senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    // verified abaixo retorna um boolean
                    if(passwordVerify.verified) {
                        filterChain.doFilter(request, response);

                    } else {
                        response.sendError(401);
                    }
                    // Continuaremos
    
                }

    }

    
}
