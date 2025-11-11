package com.viveromelkita.ViveroMelkita.service;

import com.viveromelkita.ViveroMelkita.interfaces.CRUDInterface;
import com.viveromelkita.ViveroMelkita.models.cliente;
import com.viveromelkita.ViveroMelkita.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class clienteService implements CRUDInterface<cliente, Long> {

    @Autowired
    private ClienteRepository repo;

    private final String message = "Cliente no encontrado";

    @Override
    public List<cliente> findAll() {
        return repo.findAll();
    }

    @Override
    public cliente findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException(message));
    }

    @Override
    public cliente save(cliente entity) {
        return repo.save(entity);
    }

    @Override
    @Transactional
    public cliente update(Long id, cliente entity) {
        cliente cliente = findById(id);

        if (entity.getNombre() != null) cliente.setNombre(entity.getNombre());
        if (entity.getEmail() != null) cliente.setEmail(entity.getEmail());

        return repo.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException(message);
        repo.deleteById(id);
    }
}
