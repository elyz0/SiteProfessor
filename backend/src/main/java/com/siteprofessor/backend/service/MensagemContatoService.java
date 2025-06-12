package com.siteprofessor.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.siteprofessor.backend.model.MensagemContato;
import com.siteprofessor.backend.repository.MensagemContatoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensagemContatoService {

    private final MensagemContatoRepository repository;

    public MensagemContato enviar(MensagemContato mensagem) {
        mensagem.setDataEnvio(LocalDateTime.now());
        return repository.save(mensagem);
    }
}
