package com.siteprofessor.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemContato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emailRemetente;

    @Column(nullable = false)
    private String tipoMensagem; // Pergunta ou Feedback

    private String assunto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    private LocalDateTime dataEnvio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
