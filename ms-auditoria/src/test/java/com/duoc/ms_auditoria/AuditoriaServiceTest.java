package com.duoc.ms_auditoria;

import com.duoc.ms_auditoria.model.Auditoria;
import com.duoc.ms_auditoria.repository.AuditoriaRepository;
import com.duoc.ms_auditoria.service.AuditoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuditoriaServiceTest {

    @Mock
    private AuditoriaRepository repo;

    @InjectMocks
    private AuditoriaService service;

    @Test
    void obtener_IdExistente_RetornaEntidad() {
        Auditoria entidad = new Auditoria();
        entidad.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));
        Auditoria resultado = service.obtener(1L);
        assertNotNull(resultado);
    }

    @Test
    void obtener_IdInexistente_LanzaExcepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.obtener(99L));
    }

    @Test
    void eliminar_IdInexistente_LanzaExcepcion() {
        when(repo.existsById(88L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> service.eliminar(88L));
    }

    @Test
    void eliminar_IdExistente_EliminaCorrectamente() {
        when(repo.existsById(1L)).thenReturn(true);
        service.eliminar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}