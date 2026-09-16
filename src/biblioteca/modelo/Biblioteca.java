package biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // Atributos
    private String nombre;
    private String direccion;
    private List<Libro> libros;
    private List<Usuario> usuarios;

    // Constructor
    public Biblioteca(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    // Metodos
    public void agregarLibro(Libro libro) {
        if (libro != null && !libros.contains(libro)) {
            libros.add(libro);
        }
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario != null && !usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    public Libro buscarLibro(String titulo) {
        if (titulo == null) {
            return null;
        }
        for (Libro libro : libros) {
            if (titulo.equalsIgnoreCase(libro.getTitulo())) {
                return libro;
            }
        }
        return null; // no se encontro ningun libro con ese titulo
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
