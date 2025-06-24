package com.ifpe.br.odontosys.security.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.ifpe.br.odontosys.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secret;

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
    }

    public String generateToken(UsuarioModel usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // Define o algoritmo de assinatura HMAC256 usando a chave secreta

            String token = JWT.create()
                    .withIssuer("odontosys-api") // Define o emissor do token (quem gerou)
                    .withSubject(usuario.getEmail()) // Define o assunto do token (geralmente o identificador do usuário)
                    .withExpiresAt(generateExpirationDate()) // Define a data de expiração do token
                    .sign(algorithm); // Assina o token com o algoritmo definido

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token", e); // Lança uma exceção em caso de erro na geração do token
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // Define o algoritmo de assinatura HMAC256 usando a chave secreta

            return JWT.require(algorithm) // Inicia a construção do verificador do token
                    .withIssuer("odontosys-api") // Define que o token deve ter sido emitido pelo "my-app"
                    .build() // Constrói o verificador JWT
                    .verify(token) // Verifica se o token é válido e assinado corretamente
                    .getSubject(); // Retorna o "subject" do token (geralmente o identificador do usuário)

        } catch (JWTVerificationException e) {
            return ""; // Retorna uma string vazia em caso de erro na validação do token
        }
    }

}
