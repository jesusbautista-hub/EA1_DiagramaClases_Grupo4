package biblioteca.modelo;

public class LibroFisico extends Libro {

private int numeroPaginas;
private String ubicacion;

public LibroFisico(int id, String titulo, int numeroPaginas, String ubicacion) {
super(id, titulo); // llama al constructor de Libro
this.numeroPaginas = numeroPaginas;
this.ubicacion = ubicacion;
    }

@Override
public String getDescripcion() {
return "Libro físico: \"" + getTitulo() + "\" (" + numeroPaginas +" páginas) - Ubicación: " + ubicacion;
    }

public int getNumeroPaginas() {
return numeroPaginas;
    }

public String getUbicacion() {
return ubicacion;
    }

public void setUbicacion(String ubicacion) {
this.ubicacion = ubicacion;
    }
}
