package com.duoc.ms_pacientes;

import com.duoc.ms_pacientes.dto.PacienteDTO;
import com.duoc.ms_pacientes.model.Paciente;
import com.duoc.ms_pacientes.repository.PacienteRepository;
import com.duoc.ms_pacientes.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository repo;

    @InjectMocks
    private PacienteService service;

    @Test
    void crear_RutDuplicado_LanzaExcepcion() {
        PacienteDTO dto = new PacienteDTO();
        dto.setRut("12345678-9");
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setEmail("juan@mail.com");
        when(repo.findByRut("12345678-9")).thenReturn(Optional.of(new Paciente()));
        assertThrows(RuntimeException.class, () -> service.crear(dto));
        verify(repo, never()).save(any());
    }

    @Test
    void crear_DatosValidos_GuardaPaciente() {
        PacienteDTO dto = new PacienteDTO();
        dto.setRut("12345678-9");
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setEmail("juan@mail.com");
        dto.setTelefono("912345678");
        Paciente pacienteGuardado = new Paciente();
        pacienteGuardado.setId(1L);
        pacienteGuardado.setRut("12345678-9");
        when(repo.findByRut("12345678-9")).thenReturn(Optional.empty());
        when(repo.save(any(Paciente.class))).thenReturn(pacienteGuardado);
        Paciente resultado = service.crear(dto);
        assertNotNull(resultado);
        assertEquals("12345678-9", resultado.getRut());
        verify(repo, times(1)).save(any(Paciente.class));
    }

    @Test
    void obtener_IdExistente_RetornaPaciente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Juan");
        when(repo.findById(1L)).thenReturn(Optional.of(paciente));
        Paciente resultado = service.obtener(1L);
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
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
