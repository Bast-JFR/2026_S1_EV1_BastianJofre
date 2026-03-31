
package cl.duoc.bastianjofre.repository;

import cl.duoc.bastianjofre.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PacienteRepository {
    // aca guardamos los datos simulando una base de datos
    private final Map<Long, Paciente> pacienteStore = new HashMap<>();

    public List<Paciente> findAll() {
        return new ArrayList<>(pacienteStore.values());
    }

    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable(pacienteStore.get(id));
    }

    public Paciente save(Paciente paciente) {
        pacienteStore.put(paciente.getId(), paciente);
        return paciente;
    }

    public void deleteById(Long id) {
        pacienteStore.remove(id);
    }

    public boolean existsById(Long id) {
        return pacienteStore.containsKey(id);
    }

}
