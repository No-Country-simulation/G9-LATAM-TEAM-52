package com.HackathonONEG9_52.HackathonG52;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HackathonG52Application {

	public static void main(String[] args) {
		SpringApplication.run(HackathonG52Application.class, args);
	}

	// Abre automáticamente el navegador en http://localhost:8080 al iniciar
	@EventListener(ApplicationReadyEvent.class)
	public void abrirNavegadorAlIniciar() {
		String url = "http://localhost:8080";
		String os = System.getProperty("os.name").toLowerCase();
		try {
			if (os.contains("win")) {
				Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
			} else if (os.contains("mac")) {
				Runtime.getRuntime().exec(new String[]{"open", url});
			} else if (os.contains("nix") || os.contains("nux")) {
				Runtime.getRuntime().exec(new String[]{"xdg-open", url});
			}
		} catch (Exception e) {
			System.out.println("No se pudo abrir el navegador automáticamente: " + e.getMessage());
		}
	}
}

