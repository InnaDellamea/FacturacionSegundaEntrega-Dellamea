package com.viveromelkita.ViveroMelkita.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
}
