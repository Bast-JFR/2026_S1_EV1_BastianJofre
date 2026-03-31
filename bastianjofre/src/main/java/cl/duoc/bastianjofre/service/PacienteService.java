package cl.duoc.bastianjofre.service;

import cl.duoc.bastianjofre.model.Paciente;
import cl.duoc.bastianjofre.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    private final PacienteRepository repository;

    // - inyección de dependencias por constructor
    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    // 1. obtener todos
    public List<Paciente> obtenerTodos() {
        return repository.findAll();
    }

    // 2. obtener por ID
    public Optional<Paciente> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. crear
    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    // 4. actualizar
    public Optional<Paciente> actualizar(Long id, Paciente pacienteActualizado) {
        return repository.findById(id).map(pacienteExistente -> {
            pacienteExistente.setNombre(pacienteActualizado.getNombre());
            pacienteExistente.setRut(pacienteActualizado.getRut());
            pacienteExistente.setEdad(pacienteActualizado.getEdad());
            pacienteExistente.setEmail(pacienteActualizado.getEmail());
            return repository.save(pacienteExistente); // Actualiza en el HashMap
        });
    }

    // 5. eliminar
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // 6. filtrar por edad
    public List<Paciente> filtrarPorEdad(Integer edadMinima) {
        return repository.findAll().stream()
                .filter(paciente -> paciente.getEdad() >= edadMinima)
                .collect(Collectors.toList());
    }
    
}
