package com.example.uccexample;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestConexion implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/taller_mecanico";
        String user = "root";
        String pass = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            if (conn != null) {
                System.out.println("Conexión exitosa a MySQL!");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
