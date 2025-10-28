package com.viveromelkita.ViveroMelkita.DaoFactory;

import org.springframework.stereotype.Service;

import com.viveromelkita.ViveroMelkita.models.cliente;
import com.viveromelkita.ViveroMelkita.models.producto;
import com.viveromelkita.ViveroMelkita.models.venta;
import com.viveromelkita.ViveroMelkita.models.ventaProducto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {

    @PersistenceContext
    private EntityManager em;

    // Persistir cliente
    @Transactional
    public void persistirCliente(cliente cliente) {
        em.persist(cliente);
    }

    // Persistir producto
    @Transactional
    public void persistirProducto(producto producto) {
        em.persist(producto);
    }

    // Persistir venta
    @Transactional
    public void persistirVenta(venta venta) {
        em.persist(venta);
    }

    // Persistir ventaProducto
    @Transactional
    public void persistirVentaProducto(ventaProducto ventaproducto) {
        em.persist(ventaproducto);
    }
}
