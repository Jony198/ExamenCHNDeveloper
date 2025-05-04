package com.examen.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication
public class ExamenChnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenChnApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public CommandLineRunner runSqlScript(DataSource dataSource) {
		return args -> {
			// Cargar archivo desde el classpath
			try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/script.sql")) {

				if (inputStream == null) {
					throw new RuntimeException("❌ No se encontró el archivo sql/script.sql en el classpath");
				}

				String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

				try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
					stmt.execute(sql);
					System.out.println("✅ Script SQL ejecutado correctamente");
				}

			} catch (Exception e) {
				System.err.println("❌ Error ejecutando el script SQL:");
				e.printStackTrace();
			}
		};
	}
}
