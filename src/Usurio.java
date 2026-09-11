package biblioteca.modelo;
import biblioteca.prestamo.Prestamo;
public class Usurio {
    //Atributos
    private int id;
    private String nombre;
    private String correo;
    //Constructor
    public Usurio(int id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }
//Metodos 
     public prestamo solicitarPretamo(Libro libro) {
        if(libro != null && libro.isDisponible()) {
            libro.prestar();
            return new prestamo(this, libro);

        }
    System.out.println("El libro no está disponible para préstamo.");
        return null;

     }
     public void devolverPretamo(Prestamo prestamo) {
        if(prestamo != null && prestamo.estaActivo()) {
            prestamo.registarDevolucion();
            prestamo.out.Println("devolución registrada");
        } 
           
        }
        //Getters y Setters
        public  int getId() {
            return id;
        }
        public String getNombre() {
            return nombre;
        }   
            public  String getCorreo() {
            return correo;
            public void setCorreo(String correo) {
                this.correo = correo;
            }
   
}
