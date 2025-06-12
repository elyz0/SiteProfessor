package com.siteprofessor.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteprofessor.backend.model.MensagemContato;
import com.siteprofessor.backend.service.MensagemContatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contato")
@RequiredArgsConstructor
public class MensagemContatoController {

    private final MensagemContatoService service;

    @PostMapping
    public ResponseEntity<MensagemContato> enviar(@RequestBody MensagemContato msg) {
        return ResponseEntity.ok(service.enviar(msg));
    }
}
