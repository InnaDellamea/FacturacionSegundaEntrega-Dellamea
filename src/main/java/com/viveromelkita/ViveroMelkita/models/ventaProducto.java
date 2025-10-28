package com.viveromelkita.ViveroMelkita.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Venta_Producto")
public class ventaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    public ventaProducto() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public venta getVenta() { return venta; }
    public void setVenta(venta venta) { this.venta = venta; }

    public producto getProducto() { return producto; }
    public void setProducto(producto producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "VentaProducto{" +
                "id=" + id +
                ", producto=" + (producto != null ? producto.getNombre() : "null") +
                ", cantidad=" + cantidad +
                '}';
    }
}