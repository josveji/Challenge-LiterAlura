package com.aluracursos.DesafioLibros.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    //private List<DatosAutor> autor;
    @ElementCollection
    private List<String> idiomas;
    private Double numeroDeDescargas;

//    @ManyToMany
//    @JoinTable(
//            name = "libro_autores",
//            joinColumns = @JoinColumn(name = "libro_id"),
//            inverseJoinColumns = @JoinColumn(name = "autor_id")
//    )
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    //@Transient
    //@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)


    public Libro(){}

    public Libro(DatosLibros datosLibro){
        this.titulo = datosLibro.titulo();
        this.idiomas = datosLibro.idiomas();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();


    }

    public Libro(Libro libro) {
    }

//    public Libro(Optional<DatosLibros> datosLibros) {
//        this.titulo = datosLibros.titulo();
//        this.idiomas = datosLibros.idiomas();
//        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "---- LIBRO ----\n"+
                "Titulo: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNombre() : "N/A") + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Numero de descargas: " + numeroDeDescargas + "\n" +
                "--------------"
                ;
    }
}
