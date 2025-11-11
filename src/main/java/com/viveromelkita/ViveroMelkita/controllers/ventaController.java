package com.viveromelkita.ViveroMelkita.controllers;

import com.viveromelkita.ViveroMelkita.dto.VentaDTO;
import com.viveromelkita.ViveroMelkita.models.venta;
import com.viveromelkita.ViveroMelkita.service.ventaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class ventaController {

    @Autowired
    private ventaService service;

    // ========================
    // Consultar todas las ventas
    // ========================
    @GetMapping
    public ResponseEntity<List<venta>> getAll() {
        List<venta> ventas = service.findAll();
        return ResponseEntity.ok(ventas);
    }

    // ========================
    // Consultar una venta por ID
    // ========================
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            venta v = service.findById(id);
            return ResponseEntity.ok(v);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ========================
    // Crear venta
    // ========================
    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaDTO dto) {
        try {
            venta nuevaVenta = service.createFromDTO(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ========================
    // Actualizar venta
    // ========================
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VentaDTO dto) {
        try {
            venta actualizada = service.updateFromDTO(id, dto);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ========================
    // Eliminar venta
    // ========================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Venta eliminada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
