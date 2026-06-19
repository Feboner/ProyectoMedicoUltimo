package com.duoc.ms_inventario.service;
import com.duoc.ms_inventario.dto.InventarioDTO;
import com.duoc.ms_inventario.model.InventarioItem;
import com.duoc.ms_inventario.repository.InventarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor @Slf4j
public class InventarioService {
    private final InventarioRepository repo;
    public List<InventarioItem> listar() {
        log.info("Listar ms-inventario");
        return repo.findAll();
    }
    public InventarioItem obtener(Long id) {
        log.info("Obtener ms-inventario id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado en inventario"));
    }
    public InventarioItem crear(InventarioDTO dto) {
        log.info("Crear ms-inventario");
        InventarioItem item = new InventarioItem();
        item.setCodigoProducto(dto.getCodigoProducto());
        item.setNombre(dto.getNombre());
        item.setStockActual(dto.getStockActual());
        item.setStockMinimo(dto.getStockMinimo());
        item.setUbicacionBodega(dto.getUbicacionBodega());
        return repo.save(item);
    }
    public InventarioItem actualizar(Long id, InventarioDTO dto) {
        log.info("Actualizar ms-inventario id: {}", id);
        InventarioItem item = obtener(id);
        item.setNombre(dto.getNombre());
        item.setStockActual(dto.getStockActual());
        item.setStockMinimo(dto.getStockMinimo());
        item.setUbicacionBodega(dto.getUbicacionBodega());
        return repo.save(item);
    }
    public void eliminar(Long id) {
        log.warn("Eliminar ms-inventario id: {}", id);
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Item no encontrado en inventario");
        }
        repo.deleteById(id);
    }
}