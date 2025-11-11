package com.viveromelkita.ViveroMelkita.repository;

import com.viveromelkita.ViveroMelkita.models.cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<cliente, Long> {
}