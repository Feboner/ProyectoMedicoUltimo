package com.duoc.ms_pedidos;

import com.duoc.ms_pedidos.model.Pedido;
import com.duoc.ms_pedidos.repository.PedidoRepository;
import com.duoc.ms_pedidos.service.PedidoService;
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
class PedidoServiceTest {

    @Mock
    private PedidoRepository repo;

    @InjectMocks
    private PedidoService service;

    @Test
    void obtener_IdExistente_RetornaEntidad() {
        Pedido entidad = new Pedido();
        entidad.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));
        Pedido resultado = service.obtener(1L);
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