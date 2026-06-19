package com.duoc.ms_productos;

import com.duoc.ms_productos.dto.ProductoDTO;
import com.duoc.ms_productos.model.Producto;
import com.duoc.ms_productos.repository.ProductoRepository;
import com.duoc.ms_productos.service.ProductoService;
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
class ProductoServiceTest {

    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService service;

    @Test
    void crear_DatosValidos_GuardaProducto() {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Paracetamol");
        dto.setPrecio(990.0);
        dto.setStock(100);
        Producto guardado = new Producto();
        guardado.setId(1L);
        guardado.setNombre("Paracetamol");
        when(repo.save(any(Producto.class))).thenReturn(guardado);
        Producto resultado = service.crear(dto);
        assertNotNull(resultado);
        assertEquals("Paracetamol", resultado.getNombre());
        verify(repo, times(1)).save(any(Producto.class));
    }

    @Test
    void obtener_IdExistente_RetornaProducto() {
        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Ibuprofeno");
        when(repo.findById(1L)).thenReturn(Optional.of(p));
        Producto resultado = service.obtener(1L);
        assertNotNull(resultado);
        assertEquals("Ibuprofeno", resultado.getNombre());
    }

    @Test
    void obtener_IdInexistente_LanzaExcepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.obtener(99L));
    }

    @Test
    void eliminar_IdExistente_EliminaCorrectamente() {
        when(repo.existsById(1L)).thenReturn(true);
        service.eliminar(1L);
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void eliminar_IdInexistente_LanzaExcepcion() {
        when(repo.existsById(88L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> service.eliminar(88L));
    }
}
