package com.duoc.ms_pedidos.service;
import com.duoc.ms_pedidos.dto.PedidoDTO;
import com.duoc.ms_pedidos.model.Pedido;
import com.duoc.ms_pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor @Slf4j
public class PedidoService {
    private final PedidoRepository repo;
    public List<Pedido> listar() {
        log.info("Listar pedidos");
        return repo.findAll();
    }
    public Pedido obtener(Long id) {
        log.info("Obtener pedido id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
    }
    public Pedido crear(PedidoDTO dto) {
        log.info("Crear pedido: {}", dto.getNumeroPedido());
        Pedido p = new Pedido();
        p.setNumeroPedido(dto.getNumeroPedido());
        p.setPacienteRut(dto.getPacienteRut());
        p.setTotal(dto.getTotal());
        p.setEstado(dto.getEstado());
        return repo.save(p);
    }
    public Pedido actualizar(Long id, PedidoDTO dto) {
        log.info("Actualizar pedido id: {}", id);
        Pedido p = obtener(id);
        p.setEstado(dto.getEstado());
        p.setTotal(dto.getTotal());
        return repo.save(p);
    }
    public void eliminar(Long id) {
        log.warn("Eliminar pedido id: {}", id);
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Pedido no encontrado");
        }
        repo.deleteById(id);
    }
}