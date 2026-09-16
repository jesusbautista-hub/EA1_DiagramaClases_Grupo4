package biblioteca.prestamo;

import java.time.LocalDate;

public class DetallePrestamo {
    // Atributos
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // Constructor
    public DetallePrestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Metodos
    public void registrarDevolucion(LocalDate fecha) {
        this.fechaDevolucion = fecha;
    }

    public boolean estaDevuelto() {
        return fechaDevolucion != null;
    }

    // Getters y Setters
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}
