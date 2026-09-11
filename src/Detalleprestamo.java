package biblioteca.prestamo;
import java.time.LocalDate;

public class Detalleprestamo {
    //Atributos
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    //Constructor
    public Detalleprestamo(LocalDate fechaprestamo , LocalDate fechadevolucionn){
        this.fechaPrestamo = fechaprestamo;
        this.fechaDevolucion = null;

    }
    //Metodos
    public void registarDevolucion(LocalDate fecha) {
        this.fechaDevolucion = fecha;
    }
    public boolean estaDevuelto() {
        return fechaDevolucion != null;
    }
    //Getters y Setters
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }



    
}
