package com.examen.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;


@SpringBootApplication
public class ExamenChnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenChnApplication.class, args);
	}

	// Aquí agregas el Bean
	@org.springframework.context.annotation.Bean
	public CommandLineRunner runSqlScript(DataSource dataSource) {
		return args -> {
			String path = "src/main/resources/sql/script.sql"; // Ruta del archivo
			String sql = new String(Files.readAllBytes(Paths.get(path)));

			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				stmt.execute(sql);
				System.out.println("✅ Script SQL ejecutado correctamente");
			} catch (Exception e) {
				System.err.println("❌ Error ejecutando el script SQL:");
				e.printStackTrace();
			}
		};
	}
}
