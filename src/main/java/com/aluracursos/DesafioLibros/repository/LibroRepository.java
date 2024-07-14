package com.aluracursos.DesafioLibros.repository;

import com.aluracursos.DesafioLibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
