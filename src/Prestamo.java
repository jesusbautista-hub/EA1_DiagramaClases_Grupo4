package biblioteca.prestamo;
import java.biblotecla.modelo.usuario;
import java.biblotecla.modelo.libro;
import java.time.LocalDate;

public class Prestamo {

    private int id;
    private static int contador = 1;
    private usuario usuario;
    private libro libro;
    private Detalleprestamo detalle;
    private boolean activo;

}

public Prestamo(usuario usuario, libro libro) {
    this.usuario = usuario;
    this.libro = libro;
    this.id = contador++;
    this.activo = true;
    this.detalle = new Detalleprestamo(LocalDate.now(), null);
}
//Sobrecarga
public registarDevolucion(){
    registarDevolucion(LocalDate.now());

}
//Sobrecarga con fecha especifica
public registarDevolucion(LocalDate fecha) {
    detalle.registarDevolucion(fecha);
    this.activo = false;
    libro.devolver();

}
public boolean estaActivo() {
    return activo;
    
}
 public inti id getId() {
    return id;
 }
 public usuario getUsuario() {
    return usuario;
 }
 public libro getLibro() {
    return libro;
 }
 public detalleprestamo getDetalle() {
    return detalle;
 }
