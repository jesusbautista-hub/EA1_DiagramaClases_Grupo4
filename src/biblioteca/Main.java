package biblioteca;

import biblioteca.modelo.Autor;
import biblioteca.modelo.Biblioteca;
import biblioteca.modelo.Libro;
import biblioteca.modelo.LibroDigital;
import biblioteca.modelo.LibroFisico;
import biblioteca.modelo.Usuario;
import biblioteca.prestamo.GestorPrestamos;
import biblioteca.prestamo.Prestamo;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", "Av. Principal 123");
        GestorPrestamos gestor = new GestorPrestamos();

        LibroFisico libroFisico = new LibroFisico(1, "1984", 328, "Estante A");
        LibroDigital libroDigital = new LibroDigital(2, "El Principito", "PDF", 1.5);
        Usuario usuario = new Usuario(1, "Ana Pérez", "ana@correo.com");

        biblioteca.agregarLibro(libroFisico);
        biblioteca.agregarLibro(libroDigital);
        biblioteca.registrarUsuario(usuario);

        Autor autor = new Autor(1, "George Orwell", "Británico");
        autor.agregarLibro(libroFisico);

        titulo("1. Polimorfismo: misma llamada, distinta respuesta");
        for (Libro libro : biblioteca.getLibros()) {
            System.out.println("   " + libro.getDescripcion());
        }

        titulo("2. Biblioteca: agregación y búsqueda");
        System.out.println("   Biblioteca: " + biblioteca.getNombre() + " (" + biblioteca.getDireccion() + ")");
        System.out.println("   Libros registrados: " + biblioteca.getLibros().size());
        System.out.println("   Usuarios registrados: " + biblioteca.getUsuarios().size());
        System.out.println("   Autor: " + autor.getNombre() + " (" + autor.getNacionalidad() + ") - "
                + autor.getLibros().size() + " libro(s)");
        System.out.println("   buscarLibro(\"1984\"): " + describir(biblioteca.buscarLibro("1984")));
        System.out.println("   buscarLibro(\"el principito\"): " + describir(biblioteca.buscarLibro("el principito")));
        System.out.println("   buscarLibro(\"Inexistente\"): " + describir(biblioteca.buscarLibro("Inexistente")));

        titulo("3. Préstamo a través del gestor");
        System.out.println("   ¿Disponible antes de prestar? " + gestor.verificarDisponibilidad(libroFisico));
        Prestamo prestamo = gestor.crearPrestamo(usuario, libroFisico);
        System.out.println("   Préstamo #" + prestamo.getId() + " creado el " + prestamo.getFechaCreacion());
        System.out.println("   ¿Disponible después de prestar? " + libroFisico.isDisponible());
        System.out.println("   ¿Préstamo activo? " + prestamo.estaActivo());
        System.out.println("   Préstamos del usuario: " + usuario.getPrestamos().size());

        titulo("4. No se puede prestar dos veces el mismo libro");
        Prestamo repetido = gestor.crearPrestamo(usuario, libroFisico);
        System.out.println("   Resultado del segundo intento: " + (repetido == null ? "rechazado (null)" : "creado"));
        System.out.println("   Préstamos del usuario: " + usuario.getPrestamos().size());

        titulo("5. Devolución");
        gestor.devolverPrestamo(prestamo);
        System.out.println("   ¿Préstamo activo? " + prestamo.estaActivo());
        System.out.println("   ¿Detalle devuelto? " + prestamo.getDetalle().estaDevuelto());
        System.out.println("   Fecha de devolución: " + prestamo.getDetalle().getFechaDevolucion());
        System.out.println("   ¿Libro disponible otra vez? " + libroFisico.isDisponible());

        titulo("6. Devolver dos veces no rompe nada");
        gestor.devolverPrestamo(prestamo);
        System.out.println("   Fecha de devolución tras el segundo intento: "
                + prestamo.getDetalle().getFechaDevolucion());
        System.out.println("   ¿Libro disponible? " + libroFisico.isDisponible());

        titulo("7. Sobrecarga: devolución con fecha específica");
        Prestamo prestamoDigital = usuario.solicitarPrestamo(libroDigital);
        System.out.println("   Préstamo #" + prestamoDigital.getId() + " del libro digital");
        LocalDate fechaElegida = LocalDate.of(2026, 1, 15);
        prestamoDigital.registrarDevolucion(fechaElegida);
        System.out.println("   Fecha registrada: " + prestamoDigital.getDetalle().getFechaDevolucion()
                + " (hoy es " + LocalDate.now() + ")");
        System.out.println("   ¿Libro digital disponible? " + libroDigital.isDisponible());

        titulo("8. Casos borde");
        System.out.println("   solicitarPrestamo(null): " + usuario.solicitarPrestamo(null));
        gestor.devolverPrestamo(null);
        System.out.println("   devolverPrestamo(null): sin errores");
        System.out.println("   crearPrestamo(null, null): " + gestor.crearPrestamo(null, null));

        titulo("9. Historial del usuario");
        for (Prestamo p : usuario.getPrestamos()) {
            System.out.println("   Préstamo #" + p.getId() + " - " + p.getLibro().getTitulo()
                    + " | prestado: " + p.getDetalle().getFechaPrestamo()
                    + " | devuelto: " + p.getDetalle().getFechaDevolucion());
        }
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }

    private static String describir(Libro libro) {
        return libro == null ? "no encontrado" : libro.getDescripcion();
    }
}
