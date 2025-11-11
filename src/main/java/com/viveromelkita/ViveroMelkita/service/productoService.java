package com.viveromelkita.ViveroMelkita.service;

import com.viveromelkita.ViveroMelkita.models.producto;
import com.viveromelkita.ViveroMelkita.dto.ProductoDTO;
import com.viveromelkita.ViveroMelkita.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productoService {

    @Autowired
    private ProductoRepository repository;

    public List<producto> findAll() {
        return repository.findAll();
    }

    public producto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    public producto save(producto p) {
        return repository.save(p);
    }

    // ✅ Método update que recibe un DTO
    public producto update(Long id, ProductoDTO dto) {
        producto existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        // Actualizamos los campos con los valores del DTO
        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock());

        return repository.save(existente);
    }

    public void deleteById(Long id) {
        producto existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        repository.delete(existente);
    }
}
