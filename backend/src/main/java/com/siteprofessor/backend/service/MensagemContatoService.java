package com.siteprofessor.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.MensagemContato;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.repository.MensagemContatoRepository;

@Service
public class MensagemContatoService {

    private final MensagemContatoRepository repository;
 
    public MensagemContatoService(MensagemContatoRepository repository) {
        this.repository = repository;
    }
 
    public List<MensagemContato> listar() {
        return repository.findAll();
    } 
    
    public MensagemContato enviar(MensagemContato mensagem) { //qnd existir um usuario autenticado, ele ja atribui, front nao precisaria mandar usuario : id : tal
        var auth = SecurityContextHolder.getContext().getAuthentication(); 
        boolean isAnonimo = (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())); //temos q detectatr se é anonimo ou nao pq ele n pode mandar feedback so perguntas
         
        if (mensagem.getTipoMensagem().equalsIgnoreCase("Feedback") && isAnonimo) {
            throw new RuntimeException("Usuários anônimos não podem enviar feedback.");
        }   

        mensagem.setDataEnvio(LocalDateTime.now());

        if (auth != null && auth.getPrincipal() instanceof Usuario usuario) {
            mensagem.setUsuario(usuario);
        }

        return repository.save(mensagem);
    }
}
