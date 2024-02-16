package br.com.alura.ScreenMatch;

import br.com.alura.ScreenMatch.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal serie = new Principal();
		serie.obterDadosCompletos();
	}
}