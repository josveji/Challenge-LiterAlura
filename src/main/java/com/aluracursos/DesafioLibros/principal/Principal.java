package com.aluracursos.DesafioLibros.principal;

import com.aluracursos.DesafioLibros.model.Datos;
import com.aluracursos.DesafioLibros.model.DatosLibros;
import com.aluracursos.DesafioLibros.model.Libro;
import com.aluracursos.DesafioLibros.repository.AutorRepository;
import com.aluracursos.DesafioLibros.repository.LibroRepository;
import com.aluracursos.DesafioLibros.service.ConsumoAPi;
import com.aluracursos.DesafioLibros.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPi consumoAPi = new ConsumoAPi();

    private ConvierteDatos conversor = new ConvierteDatos();

    private Scanner teclado = new Scanner(System.in);

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    //var json = consumoAPi.obtenerDatos(URL_BASE);

    public void muestraElMenu() {

        //System.out.println(json);
        //var datos = conversor.obtenerDatos(json, Datos.class);
        //System.out.println(datos);
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    =======================================
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    =======================================
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    ListarLibrosRegistrados();
                    break;
                case 3:
                    ListarAutoresRegistrados();
                    break;

                case 4:
                    ListarAutoresVivosEnUnDeterminadoAno();
                    break;

                case 5:
                    ListarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        Optional <DatosLibros> datosLibros = getLibroWeb();


        if (datosLibros.isPresent()){

            Libro libro = new Libro(datosLibros.get());
            libroRepository.save(libro);
            String string = libro.toString();
            System.out.println(string);
        } else {
            System.out.println("Libro no encontrado");
        }

    }

    // Case 1 (buscador)
    private Optional<DatosLibros> getLibroWeb () {
        //System.out.println("Ingrese el nombre del libro que desea buscar: ");
//        var tituloLibro = teclado.nextLine();
//        var json = consumoAPi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
//        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
//        return datosBusqueda.resultados().stream()
//                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
//                .findFirst();



        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var titulolibro = teclado.nextLine();
        var json = consumoAPi.obtenerDatos(URL_BASE + "?search=" + titulolibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);




        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(titulolibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            //System.out.println(libroBuscado.get().titulo());
            System.out.println(
                    "---- LIBRO ----\n"+
                    "Titulo: " + libroBuscado.get().titulo()+ "\n" +
                    "Autor: " + libroBuscado.get().autor() + "\n" +
                    "Idiomas: " + libroBuscado.get().idiomas() + "\n" +
                    "Numero de descargas: " + libroBuscado.get().numeroDeDescargas() + "\n" +
                    "--------------");

            /**/
        } else {
            System.out.println("Libro no econtrado");
        }
        return libroBuscado;

    }

    private void ListarLibrosRegistrados() {
    }

    private void ListarAutoresRegistrados() {
    }

    private void ListarAutoresVivosEnUnDeterminadoAno() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar: ");
        Integer buscarAno = teclado.nextInt();
        teclado.nextLine();
    }

    private void ListarLibrosPorIdioma() {
        System.out.println("""
                            Ingrese el idioma buscar los libros:
                            es - Español
                            en - Inglés
                            fr - Francés
                            pt - Portugués
                            """);
        String buscarIdioma = teclado.nextLine();
    }
}