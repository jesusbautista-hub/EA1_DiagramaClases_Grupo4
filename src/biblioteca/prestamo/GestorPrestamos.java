package biblioteca.prestamo;

import biblioteca.modelo.Libro;
import biblioteca.modelo.Usuario;

public class GestorPrestamos {

    public Prestamo crearPrestamo(Usuario usuario, Libro libro) {
        if (usuario == null || libro == null) {
            return null;
        }
        if (!verificarDisponibilidad(libro)) {
            notificar(usuario, "El libro \"" + libro.getTitulo() + "\" no está disponible.");
            return null;
        }
        Prestamo prestamo = new Prestamo(usuario, libro);
        notificar(usuario, "Préstamo #" + prestamo.getId() + " creado: \"" + libro.getTitulo() + "\".");
        return prestamo;
    }

    public void devolverPrestamo(Prestamo prestamo) {
        if (prestamo == null || !prestamo.estaActivo()) {
            return;
        }
        prestamo.registrarDevolucion();
        notificar(prestamo.getUsuario(),
                "Préstamo #" + prestamo.getId() + " devuelto: \"" + prestamo.getLibro().getTitulo() + "\".");
    }

    public boolean verificarDisponibilidad(Libro libro) {
        return libro != null && libro.isDisponible();
    }

    // Depende de la interfaz Notificable, no de una clase concreta
    private void notificar(Notificable destino, String mensaje) {
        if (destino != null) {
            destino.enviar(mensaje);
        }
    }
}
