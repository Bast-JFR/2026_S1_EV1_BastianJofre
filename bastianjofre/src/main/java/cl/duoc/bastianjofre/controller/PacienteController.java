package cl.duoc.bastianjofre.controller;

import cl.duoc.bastianjofre.model.Paciente;
import cl.duoc.bastianjofre.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    private final PacienteService service;

    // Inyección de dependencias por constructor 
    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos()); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok) // 200 OK si lo encuentra
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found si no existe
    }

    @PostMapping
    public ResponseEntity<Paciente> crear(@Valid @RequestBody Paciente paciente) {
        // @Valid activa las validaciones que pusiste en el Model
        Paciente nuevoPaciente = service.guardar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        return service.actualizar(id, paciente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build() // 204 No Content si se borra
                : ResponseEntity.notFound().build(); // 404 si no existía
    }

    // Endpoint de la TRANSFORMACIÓN (Filtro por edad mínima)
    @GetMapping("/filtrar/edad/{edadMinima}")
    public ResponseEntity<List<Paciente>> filtrarPorEdad(@PathVariable Integer edadMinima) {
        return ResponseEntity.ok(service.filtrarPorEdad(edadMinima));
    }

}
