package com.viveromelkita.ViveroMelkita.service;

import com.viveromelkita.ViveroMelkita.dto.VentaDTO;
import com.viveromelkita.ViveroMelkita.interfaces.CRUDInterface;
import com.viveromelkita.ViveroMelkita.models.cliente;
import com.viveromelkita.ViveroMelkita.models.producto;
import com.viveromelkita.ViveroMelkita.models.venta;
import com.viveromelkita.ViveroMelkita.models.ventaProducto;
import com.viveromelkita.ViveroMelkita.repository.ClienteRepository;
import com.viveromelkita.ViveroMelkita.repository.ProductoRepository;
import com.viveromelkita.ViveroMelkita.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ventaService implements CRUDInterface<venta, Long> {

    private final String messageVenta = "Venta no encontrada";
    private final String messageCliente = "Cliente no encontrado";

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public List<venta> findAll() {
        return ventaRepo.findAll();
    }

    @Override
    public venta findById(Long id) {
        return ventaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(messageVenta));
    }

    @Override
    @Transactional
    public venta save(venta nuevaVenta) {
        // Método general de guardado, útil si ya tienes venta con relaciones
        return saveVentaInterna(nuevaVenta);
    }

    @Override
    @Transactional
    public venta update(Long id, venta ventaActualizada) {
        venta existente = findById(id);

        if (ventaActualizada.getCliente() != null) {
            cliente c = clienteRepo.findById(ventaActualizada.getCliente().getId())
                    .orElseThrow(() -> new IllegalArgumentException(messageCliente));
            existente.setCliente(c);
        }

        if (ventaActualizada.getProductos() != null) {
            existente.getProductos().clear();
            existente.getProductos().addAll(ventaActualizada.getProductos());

            // recalcular total
            BigDecimal total = BigDecimal.ZERO;
            for (ventaProducto vp : existente.getProductos()) {
                vp.setVenta(existente);
                total = total.add(vp.getSubtotal());
            }
            existente.setTotal(total);
        }

        return ventaRepo.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!ventaRepo.existsById(id)) {
            throw new IllegalArgumentException(messageVenta);
        }
        ventaRepo.deleteById(id);
    }

    // ===========================
    // Métodos con DTO
    // ===========================

    @Transactional
    public venta createFromDTO(VentaDTO dto) {
        venta nuevaVenta = new venta();

        // Cliente
        cliente c = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException(messageCliente));
        nuevaVenta.setCliente(c);
        nuevaVenta.setFecha(LocalDate.now());

        // Productos
        List<ventaProducto> productos = new ArrayList<>();
        if (dto.getProductosIds() != null) {
            for (Long productoId : dto.getProductosIds()) {
                producto p = productoRepo.findById(productoId)
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));

                ventaProducto vp = new ventaProducto();
                vp.setProducto(p);
                vp.setVenta(nuevaVenta);
                vp.setCantidad(1); // Por ahora 1, podrías usar dto si agregas cantidad
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

    @Transactional
    public venta updateFromDTO(Long id, VentaDTO dto) {
        venta existente = findById(id);

        // Cliente
        if (dto.getClienteId() != null) {
            cliente c = clienteRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException(messageCliente));
            existente.setCliente(c);
        }

        // Productos
        if (dto.getProductosIds() != null) {
            existente.getProductos().clear();
            List<ventaProducto> productos = new ArrayList<>();
            for (Long productoId : dto.getProductosIds()) {
                producto p = productoRepo.findById(productoId)
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));

                ventaProducto vp = new ventaProducto();
                vp.setProducto(p);
                vp.setVenta(existente);
                vp.setCantidad(1);
                vp.setSubtotal(p.getPrecio());
                productos.add(vp);
            }
            existente.getProductos().addAll(productos);

            // recalcular total
            BigDecimal total = productos.stream()
                    .map(ventaProducto::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            existente.setTotal(total);
        }

        return ventaRepo.save(existente);
    }

    // ===========================
    // Método interno de guardado reutilizable
    // ===========================
    @Transactional
    private venta saveVentaInterna(venta nuevaVenta) {
        if (nuevaVenta.getCliente() == null || nuevaVenta.getCliente().getId() == null) {
            throw new IllegalArgumentException("Debe asignarse un cliente a la venta");
        }

        cliente c = clienteRepo.findById(nuevaVenta.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException(messageCliente));
        nuevaVenta.setCliente(c);
        nuevaVenta.setFecha(LocalDate.now());

        BigDecimal total = BigDecimal.ZERO;
        if (nuevaVenta.getProductos() != null) {
            for (ventaProducto vp : nuevaVenta.getProductos()) {
                vp.setVenta(nuevaVenta);
                total = total.add(vp.getSubtotal());
            }
        }
        nuevaVenta.setTotal(total);

        return ventaRepo.save(nuevaVenta);
    }
}
