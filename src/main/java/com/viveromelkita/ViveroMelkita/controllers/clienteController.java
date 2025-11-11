package com.viveromelkita.ViveroMelkita.controllers;

import com.viveromelkita.ViveroMelkita.dto.ClienteDTO;
import com.viveromelkita.ViveroMelkita.models.cliente;
import com.viveromelkita.ViveroMelkita.service.clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class clienteController {

    @Autowired
    private clienteService service;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<cliente>> getAll() {
        try {
            List<cliente> clientes = service.findAll();
            return ResponseEntity.ok(clientes); // 200
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            cliente c = service.findById(id);
            return ResponseEntity.ok(c); // 200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado"); // 404
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor"); // 500
        }
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteDTO dto) {
        try {
            cliente c = new cliente();
            c.setNombre(dto.getNombre());
            c.setApellido(dto.getApellido());
            c.setEmail(dto.getEmail());
            cliente nuevoCliente = service.save(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Actualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody cliente cliente) {
        try {
            cliente actualizado = service.update(id, cliente);
            return ResponseEntity.ok(actualizado); // 200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado"); // 404
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor"); // 500
        }
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado"); // 404
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor"); // 500
        }
    }
}
