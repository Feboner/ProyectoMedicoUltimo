package com.duoc.ms_envios.service;
import com.duoc.ms_envios.dto.EnvioDTO;
import com.duoc.ms_envios.model.Envio;
import com.duoc.ms_envios.repository.EnvioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor @Slf4j
public class EnviosService {
    private final EnvioRepository repo;
    public List<Envio> listar() {
        log.info("Listar ms-envios");
        return repo.findAll();
    }
    public Envio obtener(Long id) {
        log.info("Obtener ms-envios id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Envio no encontrado"));
    }
    public Envio crear(EnvioDTO dto) {
        log.info("Crear ms-envios");
        Envio e = new Envio();
        e.setCodigoSeguimiento(dto.getCodigoSeguimiento());
        e.setPacienteRut(dto.getPacienteRut());
        e.setDireccionDestino(dto.getDireccionDestino());
        e.setEstado(dto.getEstado());
        e.setTransportista(dto.getTransportista());
        return repo.save(e);
    }
    public Envio actualizar(Long id, EnvioDTO dto) {
        log.info("Actualizar ms-envios id: {}", id);
        Envio e = obtener(id);
        e.setEstado(dto.getEstado());
        e.setTransportista(dto.getTransportista());
        return repo.save(e);
    }
    public void eliminar(Long id) {
        log.warn("Eliminar ms-envios id: {}", id);
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Envio no encontrado");
        }
        repo.deleteById(id);
    }
}