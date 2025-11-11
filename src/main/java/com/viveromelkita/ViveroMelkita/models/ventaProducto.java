package com.viveromelkita.ViveroMelkita.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venta_producto")
public class ventaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private producto producto;

    private int cantidad;

    private BigDecimal subtotal;
}
