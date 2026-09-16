package biblioteca.modelo;

import biblioteca.prestamo.Prestable;

public abstract class Libro implements Prestable {

	private int id;
	private String titulo;
	private boolean disponible;

	public Libro(int id, String titulo) {
		this.id = id;
		this.titulo = titulo;
		this.disponible = true;
	}

	@Override
	public void prestar() {
		this.disponible = false;
	}

	@Override
	public void devolver() {
		this.disponible = true;
	}

	public abstract String getDescripcion();

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
