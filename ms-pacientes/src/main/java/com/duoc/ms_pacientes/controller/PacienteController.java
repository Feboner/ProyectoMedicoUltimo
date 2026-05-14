package com.duoc.ms_pacientes.controller;

import com.duoc.ms_pacientes.dto.ApiResponse;
import com.duoc.ms_pacientes.dto.DireccionDTO;
import com.duoc.ms_pacientes.dto.PacienteDTO;
import com.duoc.ms_pacientes.model.Direccion;
import com.duoc.ms_pacientes.model.Paciente;
import com.duoc.ms_pacientes.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@Slf4j
public class PacienteController {

    private final PacienteService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Paciente>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.<List<Paciente>>builder()
                        .success(true)
                        .message("Listado obtenido")
                        .data(service.listar())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Paciente>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Paciente obtenido")
                        .data(service.obtener(id))
                        .build()
        );
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ApiResponse<Paciente>> obtenerPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Paciente obtenido")
                        .data(service.obtenerPorRut(rut))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Paciente>> crear(@Valid @RequestBody PacienteDTO dto) {
        Paciente p = service.crear(dto);
        return ResponseEntity.status(201).body(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Paciente creado")
                        .data(p)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Paciente>> actualizar(@PathVariable Long id,
                                                            @Valid @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Paciente actualizado")
                        .data(service.actualizar(id, dto))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Paciente eliminado")
                        .build()
        );
    }

    @PostMapping("/{pacienteId}/direcciones")
    public ResponseEntity<ApiResponse<Direccion>> crearDireccion(@PathVariable Long pacienteId,
                                                                   @Valid @RequestBody DireccionDTO dto) {
        Direccion d = service.crearDireccion(pacienteId, dto);
        return ResponseEntity.status(201).body(
                ApiResponse.<Direccion>builder()
                        .success(true)
                        .message("Direccion creada")
                        .data(d)
                        .build()
        );
    }

    @GetMapping("/{pacienteId}/direcciones")
    public ResponseEntity<ApiResponse<List<Direccion>>> listarDirecciones(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(
                ApiResponse.<List<Direccion>>builder()
                        .success(true)
                        .message("Direcciones obtenidas")
                        .data(service.listarDirecciones(pacienteId))
                        .build()
        );
    }

    @GetMapping("/direcciones/{id}")
    public ResponseEntity<ApiResponse<Direccion>> obtenerDireccion(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<Direccion>builder()
                        .success(true)
                        .message("Direccion obtenida")
                        .data(service.obtenerDireccion(id))
                        .build()
        );
    }

    @PutMapping("/direcciones/{id}")
    public ResponseEntity<ApiResponse<Direccion>> actualizarDireccion(@PathVariable Long id,
                                                                       @Valid @RequestBody DireccionDTO dto) {
        return ResponseEntity.ok(
                ApiResponse.<Direccion>builder()
                        .success(true)
                        .message("Direccion actualizada")
                        .data(service.actualizarDireccion(id, dto))
                        .build()
        );
    }

    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarDireccion(@PathVariable Long id) {
        service.eliminarDireccion(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Direccion eliminada")
                        .build()
        );
    }
}
