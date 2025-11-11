package com.viveromelkita.ViveroMelkita.repository;

import com.viveromelkita.ViveroMelkita.models.producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<producto, Long> {
}
