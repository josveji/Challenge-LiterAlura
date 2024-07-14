package com.aluracursos.DesafioLibros;

import com.aluracursos.DesafioLibros.principal.Principal;
import com.aluracursos.DesafioLibros.repository.AutorRepository;
import com.aluracursos.DesafioLibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLibrosApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;


	public static void main(String[] args) {
		SpringApplication.run(DesafioLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal =  new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}
}
