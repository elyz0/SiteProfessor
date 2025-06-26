package com.siteprofessor.backend.service;

import com.siteprofessor.backend.model.Perfil;
import com.siteprofessor.backend.model.Usuario;
import com.siteprofessor.backend.repository.UsuarioRepository;

import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import com.siteprofessor.backend.model.UsuarioBuilder;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        usuario = new UsuarioBuilder() 
                .comId(1L)
                .comNome("Admin")
                .comEmail("admin@siteprofessor.com")
                .comSenha("senha")
                .comPerfil(Perfil.ADMINISTRADOR)
                .construir();
    }

    @Test
    void deveCarregarUsuarioPorEmail() {
        when(usuarioRepository.findByEmail("admin@siteprofessor.com")).thenReturn(Optional.of(usuario));

        UserDetails user = usuarioService.loadUserByUsername("admin@siteprofessor.com");

        assertEquals("admin@siteprofessor.com", user.getUsername());
        assertTrue(user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRADOR")));
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarUsuario() {
        when(usuarioRepository.findByEmail("nao@existe.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            usuarioService.loadUserByUsername("nao@existe.com");
        });
    }
}
