package biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Autor {

    private int id;
    private String nombre;
    private String nacionalidad;
    private List<Libro> libros;

    public Autor(int id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public int getId() {
        return id;
    }
}
    