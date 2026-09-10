package biblioteca.modelo;

public abstract class Libro {

private int id;
private String titulo;
private boolean disponible;

public Libro(int id, String titulo) {
this.id = id;
this.titulo = titulo;
this.disponible = true; // un libro nuevo empieza disponible
}

// Comportamiento común: No varía entre las subclases.
public void prestar() {
this.disponible = false;
}

public void devolver() {
this.disponible = true;
}

// Método abstracto: cada subclase decide como describirse
public abstract String getDescripcion();

// Getters y setters (encapsulación)
public int getId() {
return id;
}

public String getTitulo() {
return titulo;
}

public void setTitulo(String titulo) {
this.titulo = titulo;
}

public boolean isDisponible() {
return disponible;
}
}
