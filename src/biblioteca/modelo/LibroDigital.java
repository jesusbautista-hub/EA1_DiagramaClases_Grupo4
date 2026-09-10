package biblioteca.modelo;

public class LibroDigital extends Libro {

private String formato;
private double tamanoMB;

public LibroDigital(int id, String titulo, String formato, double tamanoMB) {
super(id, titulo);
this.formato = formato;
this.tamanoMB = tamanoMB;
    }

@Override
public String getDescripcion() {
return "Libro digital: \"" + getTitulo() + "\" (" + formato +", " + tamanoMB + " MB)";
    }

public String getFormato() {
return formato;
    }

public double getTamanoMB() {
return tamanoMB;
    }

public void setTamanoMB(double tamanoMB) {
this.tamanoMB = tamanoMB;
    }
}
