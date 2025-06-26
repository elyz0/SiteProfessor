package com.siteprofessor.backend.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.siteprofessor.backend.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct; 
import java.util.Base64;


@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expirationMillis;

    private Key key;
 
    //se a chave muda enqt está em produção, todos os usuários serão deslogados

    @PostConstruct
    public void init() { 
        if (secret == null || secret.getBytes().length < 32) {
            // Gera automaticamente uma chave segura se a configurada for inválida
            SecretKey generatedKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            this.secret = Base64.getEncoder().encodeToString(generatedKey.getEncoded());
            System.out.println("AVISO: Gerada nova chave JWT segura automaticamente");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("perfil", usuario.getPerfil())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmail(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean isValido(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    } 
     
    
}
