package com.duoc.ms_pacientes.service;

import com.duoc.ms_pacientes.dto.DireccionDTO;
import com.duoc.ms_pacientes.dto.PacienteDTO;
import com.duoc.ms_pacientes.model.Direccion;
import com.duoc.ms_pacientes.model.Paciente;
import com.duoc.ms_pacientes.repository.DireccionRepository;
import com.duoc.ms_pacientes.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteService {

    private final PacienteRepository repo;
    private final DireccionRepository direccionRepo;

    public List<Paciente> listar() {
        log.info("Listar pacientes");
        return repo.findAll();
    }

    public Paciente obtener(Long id) {
        log.info("Obtener paciente id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
    }

    public Paciente obtenerPorRut(String rut) {
        log.info("Obtener paciente rut: {}", rut);
        return repo.findByRut(rut)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
    }

    public Paciente crear(PacienteDTO dto) {
        log.info("Crear paciente rut: {}", dto.getRut());

        if (repo.findByRut(dto.getRut()).isPresent()) {
            throw new RuntimeException("Ya existe un paciente con ese RUT");
        }

        Paciente p = new Paciente();
        p.setNombre(dto.getNombre());
        p.setApellido(dto.getApellido());
        p.setRut(dto.getRut());
        p.setEmail(dto.getEmail());
        p.setTelefono(dto.getTelefono());

        return repo.save(p);
    }

    public Paciente actualizar(Long id, PacienteDTO dto) {
        log.info("Actualizar paciente id: {}", id);

        Paciente p = obtener(id);
        p.setNombre(dto.getNombre());
        p.setApellido(dto.getApellido());
        p.setEmail(dto.getEmail());
        p.setTelefono(dto.getTelefono());

        return repo.save(p);
    }

    public void eliminar(Long id) {
        log.warn("Eliminar paciente id: {}", id);
        repo.deleteById(id);
    }

    public Direccion crearDireccion(Long pacienteId, DireccionDTO dto) {
        log.info("Crear direccion para paciente id: {}", pacienteId);

        Paciente p = obtener(pacienteId);

        Direccion d = new Direccion();
        d.setCalle(dto.getCalle());
        d.setCiudad(dto.getCiudad());
        d.setRegion(dto.getRegion());
        d.setCodigoPostal(dto.getCodigoPostal());
        d.setPaciente(p);

        return direccionRepo.save(d);
    }

    public List<Direccion> listarDirecciones(Long pacienteId) {
        log.info("Listar direcciones del paciente id: {}", pacienteId);
        obtener(pacienteId);
        return direccionRepo.findByPacienteId(pacienteId);
    }

    public Direccion obtenerDireccion(Long id) {
        log.info("Obtener direccion id: {}", id);
        return direccionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Direccion no encontrada"));
    }

    public Direccion actualizarDireccion(Long id, DireccionDTO dto) {
        log.info("Actualizar direccion id: {}", id);

        Direccion d = obtenerDireccion(id);
        d.setCalle(dto.getCalle());
        d.setCiudad(dto.getCiudad());
        d.setRegion(dto.getRegion());
        d.setCodigoPostal(dto.getCodigoPostal());

        return direccionRepo.save(d);
    }

    public void eliminarDireccion(Long id) {
        log.warn("Eliminar direccion id: {}", id);
        direccionRepo.deleteById(id);
    }
}
