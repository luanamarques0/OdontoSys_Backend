package com.ifpe.br.odontosys.security.filter;

import java.io.IOException;

import com.ifpe.br.odontosys.repository.UsuarioRepository;
import com.ifpe.br.odontosys.security.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService; // Serviço de tokens JWT, será usado para validar o token e retornar o username.

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositório para buscar informações do usuário no banco de dados

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request); // Recupera o token da requisição(o metodo recoverToken está no final da classe)
        if (token != null) { // Se houver um token presente
            String username = jwtService.validateToken(token); // Valida o token e extrai o nome de usuário
            UserDetails user = usuarioRepository.findByEmail(username); // Busca o usuário no banco de dados

            // Cria um objeto de autenticação do Spring Security com o usuário autenticado
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            // Define o usuário autenticado no contexto de segurança da aplicação
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua a cadeia de filtros, permitindo que a requisição prossiga
        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization"); // Obtém o cabeçalho "Authorization" da requisição
        if (authHeader == null) return null; // Se não houver cabeçalho, retorna null
        return authHeader.replace("Bearer ", ""); // Remove o prefixo "Bearer " e retorna apenas o token
    }
}
