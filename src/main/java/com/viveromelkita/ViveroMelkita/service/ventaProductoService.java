package com.viveromelkita.ViveroMelkita.service;

import com.viveromelkita.ViveroMelkita.dto.VentaDTO;
import com.viveromelkita.ViveroMelkita.interfaces.CRUDInterface;
import com.viveromelkita.ViveroMelkita.models.cliente;
import com.viveromelkita.ViveroMelkita.models.producto;
import com.viveromelkita.ViveroMelkita.models.venta;
import com.viveromelkita.ViveroMelkita.models.ventaProducto;
import com.viveromelkita.ViveroMelkita.repository.ClienteRepository;
import com.viveromelkita.ViveroMelkita.repository.ProductoRepository;
import com.viveromelkita.ViveroMelkita.repository.VentaProductoRepository;
import com.viveromelkita.ViveroMelkita.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ventaProductoService implements CRUDInterface<ventaProducto, Long> {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Transactional
    public venta createFromDTO(VentaDTO dto) {
        venta nuevaVenta = new venta();

        // Cliente
        cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        nuevaVenta.setCliente(cliente);
        nuevaVenta.setFecha(LocalDate.now());

        // Productos
        List<ventaProducto> productos = new ArrayList<>();
        if (dto.getProductosIds() != null) {
            for (Long productoId : dto.getProductosIds()) {
                // Traer producto real
                producto p = productoRepo.findById(productoId)
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));

                ventaProducto vp = new ventaProducto();
                vp.setProducto(p);
                vp.setVenta(nuevaVenta);
                vp.setCantidad(1);
                vp.setSubtotal(p.getPrecio());
                productos.add(vp);
            }
        }
        nuevaVenta.setProductos(productos);

        // Total
        BigDecimal total = productos.stream()
                .map(ventaProducto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        nuevaVenta.setTotal(total);

        return ventaRepo.save(nuevaVenta);
    }

    @Override
    public List<ventaProducto> findAll() {
        return List.of();
    }

    @Override
    public ventaProducto findById(Long aLong) {
        return null;
    }

    @Override
    public ventaProducto save(ventaProducto entity) {
        return null;
    }

    @Override
    public ventaProducto update(Long aLong, ventaProducto entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}