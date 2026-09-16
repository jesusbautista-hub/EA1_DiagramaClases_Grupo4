package biblioteca.prestamo;

import biblioteca.modelo.Libro;
import biblioteca.modelo.Usuario;
import java.time.LocalDate;

public class Prestamo {

    // Atributos
    private int id;
    private static int contador = 1;
    private Usuario usuario;
    private Libro libro;
    private DetallePrestamo detalle;
    private boolean activo;
    private LocalDate fechaCreacion;

    // Constructor
    public Prestamo(Usuario usuario, Libro libro) {
        if (usuario == null || libro == null) {
            throw new IllegalArgumentException("Un préstamo necesita un usuario y un libro.");
        }
        if (!libro.isDisponible()) {
            throw new IllegalStateException("El libro \"" + libro.getTitulo() + "\" ya está prestado.");
        }
        this.id = contador++;
        this.usuario = usuario;
        this.libro = libro;
        this.activo = true;
        this.fechaCreacion = LocalDate.now();
        this.detalle = new DetallePrestamo(this.fechaCreacion, null); // composicion
        libro.prestar();
        usuario.agregarPrestamo(this);
    }

    // Sobrecarga
    public void registrarDevolucion() {
        registrarDevolucion(LocalDate.now());
    }

    // Sobrecarga con fecha especifica
    public void registrarDevolucion(LocalDate fecha) {
        if (!activo) {
            return; // ya fue devuelto, no se registra dos veces
        }
        detalle.registrarDevolucion(fecha);
        this.activo = false;
        libro.devolver();
    }

    public boolean estaActivo() {
        return activo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public DetallePrestamo getDetalle() {
        return detalle;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
}
