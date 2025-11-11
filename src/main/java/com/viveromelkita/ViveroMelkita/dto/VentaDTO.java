package com.viveromelkita.ViveroMelkita.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class VentaDTO {
    private Long clienteId;
    private List<Long> productosIds;
}
