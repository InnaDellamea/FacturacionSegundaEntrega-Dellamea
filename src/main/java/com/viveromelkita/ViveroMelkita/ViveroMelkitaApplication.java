package com.viveromelkita.ViveroMelkita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viveromelkita.ViveroMelkita.DaoFactory.DaoFactory;
import com.viveromelkita.ViveroMelkita.models.cliente;
import com.viveromelkita.ViveroMelkita.models.producto;

@SpringBootApplication
public class ViveroMelkitaApplication implements CommandLineRunner {

    @Autowired
    private DaoFactory dao;

    public static void main(String[] args) {
        SpringApplication.run(ViveroMelkitaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        try {
            // ===== Clientes =====
            cliente cliente1 = new cliente();
            cliente1.setNombre("Valentina");
            cliente1.setApellido("Martínez");
            cliente1.setEmail("valentina2@gmail.com");

            cliente cliente2 = new cliente();
            cliente2.setNombre("Federico");
            cliente2.setApellido("Ramírez");
            cliente2.setEmail("fede.ramirez@gmail.com");

            cliente cliente3 = new cliente();
            cliente3.setNombre("Lucía");
            cliente3.setApellido("Gómez");
            cliente3.setEmail("lucia@gmail.com");

            cliente cliente4 = new cliente();
            cliente4.setNombre("Mateo");
            cliente4.setApellido("Fernández");
            cliente4.setEmail("mateo@gmail.com");

            dao.persistirCliente(cliente1);
            dao.persistirCliente(cliente2);
            dao.persistirCliente(cliente3);
            dao.persistirCliente(cliente4);

            // Imprimir clientes en consola
            System.out.println("----- Clientes -----");
            System.out.println(cliente1);
            System.out.println(cliente2);
            System.out.println(cliente3);
            System.out.println(cliente4);

            // ===== Productos =====
            producto planta1 = new producto();
            planta1.setNombre("Cactus variedad mix 10cm");
            planta1.setPrecio(1200.0);
            planta1.setStock(50);

            producto planta2 = new producto();
            planta2.setNombre("Helecho decorativo grande");
            planta2.setPrecio(3500.0);
            planta2.setStock(15);

            producto planta3 = new producto();
            planta3.setNombre("Orquídea mini blanca");
            planta3.setPrecio(2200.0);
            planta3.setStock(20);

            producto planta4 = new producto();
            planta4.setNombre("Ficus robusta 50cm");
            planta4.setPrecio(4000.0);
            planta4.setStock(10);

            producto planta5 = new producto();
            planta5.setNombre("Lavanda aromática en maceta 12cm");
            planta5.setPrecio(1500.0);
            planta5.setStock(30);

            producto tierra1 = new producto();
            tierra1.setNombre("Tierra fértil premium 5kg");
            tierra1.setPrecio(900.0);
            tierra1.setStock(100);

            producto fertilizante1 = new producto();
            fertilizante1.setNombre("Fertilizante orgánico 1kg");
            fertilizante1.setPrecio(800.0);
            fertilizante1.setStock(60);

            producto fertilizante2 = new producto();
            fertilizante2.setNombre("Abono líquido universal 500ml");
            fertilizante2.setPrecio(700.0);
            fertilizante2.setStock(40);

            producto maceta1 = new producto();
            maceta1.setNombre("Maceta grande de cerámica");
            maceta1.setPrecio(2500.0);
            maceta1.setStock(20);

            producto maceta2 = new producto();
            maceta2.setNombre("Maceta pequeña de plástico");
            maceta2.setPrecio(500.0);
            maceta2.setStock(40);

            dao.persistirProducto(planta1);
            dao.persistirProducto(planta2);
            dao.persistirProducto(planta3);
            dao.persistirProducto(planta4);
            dao.persistirProducto(planta5);
            dao.persistirProducto(tierra1);
            dao.persistirProducto(fertilizante1);
            dao.persistirProducto(fertilizante2);
            dao.persistirProducto(maceta1);
            dao.persistirProducto(maceta2);

            // Imprimir productos en consola
            System.out.println("----- Productos -----");
            System.out.println(planta1);
            System.out.println(planta2);
            System.out.println(planta3);
            System.out.println(planta4);
            System.out.println(planta5);
            System.out.println(tierra1);
            System.out.println(fertilizante1);
            System.out.println(fertilizante2);
            System.out.println(maceta1);
            System.out.println(maceta2);

        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
