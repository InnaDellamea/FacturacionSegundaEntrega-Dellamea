package com.viveromelkita.ViveroMelkita.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class errorResponse {
    private String message;
    private String detail;
}
