package com.aluracursos.DesafioLibros.repository;

import com.aluracursos.DesafioLibros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
