package com.duoc.msauth;

import com.duoc.msauth.model.Usuario;
import com.duoc.msauth.repository.UsuarioRepository;
import com.duoc.msauth.security.JwtUtil;
import com.duoc.msauth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    void registrar_UsuarioNuevo_GuardaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsername("apvallejos");
        usuario.setPassword("123456");
        when(usuarioRepository.findByUsername("apvallejos")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("123456")).thenReturn("hash123");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario resultado = authService.registrar(usuario);
        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void registrar_UsuarioDuplicado_LanzaExcepcion() {
        Usuario usuario = new Usuario();
        usuario.setUsername("apvallejos");
        when(usuarioRepository.findByUsername("apvallejos")).thenReturn(Optional.of(usuario));
        assertThrows(RuntimeException.class, () -> authService.registrar(usuario));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void login_CredencialesValidas_RetornaToken() {
        Usuario usuario = new Usuario();
        usuario.setUsername("apvallejos");
        usuario.setPassword("hash123");
        when(usuarioRepository.findByUsername("apvallejos")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("123456", "hash123")).thenReturn(true);
        when(jwtUtil.generateToken("apvallejos")).thenReturn("token123");
        String token = authService.login("apvallejos", "123456");
        assertEquals("token123", token);
    }

    @Test
    void login_CredencialesInvalidas_RetornaNull() {
        Usuario usuario = new Usuario();
        usuario.setUsername("apvallejos");
        usuario.setPassword("hash123");
        when(usuarioRepository.findByUsername("apvallejos")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrongpass", "hash123")).thenReturn(false);
        String token = authService.login("apvallejos", "wrongpass");
        assertNull(token);
        
    }
}
