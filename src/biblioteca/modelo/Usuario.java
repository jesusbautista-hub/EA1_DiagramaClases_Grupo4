package biblioteca.modelo;

import biblioteca.prestamo.Notificable;
import biblioteca.prestamo.Prestamo;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Notificable {
    // Atributos
    private int id;
    private String nombre;
    private String correo;
    private List<Prestamo> prestamos;

    // Constructor
    public Usuario(int id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.prestamos = new ArrayList<>();
    }

    // Metodos
    public Prestamo solicitarPrestamo(Libro libro) {
        if (libro == null || !libro.isDisponible()) {
            enviar("El libro solicitado no está disponible para préstamo.");
            return null;
        }
        // el constructor marca el libro como prestado y lo agrega a esta lista
        return new Prestamo(this, libro);
    }

    public void devolverPrestamo(Prestamo prestamo) {
        if (prestamo == null || !prestamo.estaActivo()) {
            enviar("No hay un préstamo activo que devolver.");
            return;
        }
        prestamo.registrarDevolucion();
        enviar("Devolución registrada del libro \"" + prestamo.getLibro().getTitulo() + "\".");
    }

    // Implementacion de Notificable
    @Override
    public void enviar(String mensaje) {
        System.out.println("[Notificación para " + nombre + " <" + correo + ">] " + mensaje);
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo != null && !prestamos.contains(prestamo)) {
            prestamos.add(prestamo);
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}
