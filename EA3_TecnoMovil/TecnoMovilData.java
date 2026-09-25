import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TecnoMovilData {

    // Estructura de Datos Inmutable (Record Java)
    public record RegistroViaje(
            String idUsuario,
            String ruta,
            String estacion,
            String accion, 
            LocalDateTime timestamp
    ) {}

    public static void main(String[] args) {
        List<RegistroViaje> dataset = cargarDatosSimulados();

        System.out.println("TECNOMOVIL DATA: ANÁLISIS FUNCIONAL");
        System.out.println("Total de registros cargados: " + dataset.size() + "\n");

        // 1. Afluencia por estación
        System.out.println("--- 1. Afluencia (Entradas) por Estación (Orden Descendente) ---");
        calcularAfluenciaPorEstacion(dataset)
                .forEach((estacion, total) -> System.out.printf("Estación: %-18s | Entradas: %d%n", estacion, total));

        // 2. Identificación de horas pico
        System.out.println("\n--- 2. Identificación de Horas Pico ---");
        obtenerHorasPico(dataset)
                .forEach((hora, flujo) -> System.out.printf("Hora: %02d:00 | Flujo total: %d entradas%n", hora, flujo));

        // 3. Rutas más utilizadas
        System.out.println("\n--- 3. Rutas Más Utilizadas (Orden Descendente) ---");
        obtenerRutasMasUtilizadas(dataset)
                .forEach((ruta, uso) -> System.out.printf("Ruta: %-10s | Total viajes (entradas): %d%n", ruta, uso));

        // 4. Patrones de viaje por usuario (Responsabilidad Compañero 2)
        System.out.println("\n--- 4. Patrones de Viaje por Usuario (Historial Cronológico) ---");
        obtenerPatronesViajePorUsuario(dataset)
                .forEach((usuario, estaciones) -> System.out.printf("Usuario: %-8s | Estaciones: %s%n", usuario, estaciones));

        // 5. Tiempo promedio de viaje por ruta (Responsabilidad Miguel)
        System.out.println("\n--- 5. Tiempo Promedio de Viaje por Ruta ---");
        calcularTiempoPromedioPorRuta(dataset)
                .forEach((ruta, minutos) -> System.out.printf("Ruta: %-10s | Tiempo promedio: %.1f minutos%n", ruta, minutos));

        // 6. Estaciones con sobrecarga (Responsabilidad Miguel)
        final long umbral = 2;
        System.out.printf("%n--- 6. Estaciones con Sobrecarga (afluencia mayor a %d) ---%n", umbral);
        List<String> sobrecargadas = detectarEstacionesConSobrecarga(dataset, umbral);
        if (sobrecargadas.isEmpty()) {
            System.out.println("Ninguna estación supera el umbral.");
        } else {
            sobrecargadas.forEach(estacion -> System.out.println("Estación en sobrecarga: " + estacion));
        }
    }

    // --- TAREAS FUNCIONALES ---

    public static Map<String, Long> calcularAfluenciaPorEstacion(List<RegistroViaje> registros) {
        Map<String, Long> entradasPorEstacion = registros.stream()
                .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        RegistroViaje::estacion,
                        Collectors.counting()
                ));
        return ordenarDescendente(entradasPorEstacion);
    }

    public static Map<Integer, Long> obtenerHorasPico(List<RegistroViaje> registros) {
        Map<Integer, Long> flujoPorHora = registros.stream()
                .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        r -> r.timestamp().getHour(),
                        Collectors.counting()
                ));
        return ordenarDescendente(flujoPorHora);
    }

    /**
     * Tarea 3: Determina el volumen total de uso de cada ruta en orden descendente.
     */
    public static Map<String, Long> obtenerRutasMasUtilizadas(List<RegistroViaje> registros) {
        Map<String, Long> viajesPorRuta = registros.stream()
                .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        RegistroViaje::ruta,
                        Collectors.counting()
                ));
        return ordenarDescendente(viajesPorRuta);
    }

    /**
     * Tarea 4: Genera para cada usuario la lista ordenada cronológicamente de estaciones visitadas.
     */
    public static Map<String, List<String>> obtenerPatronesViajePorUsuario(List<RegistroViaje> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroViaje::idUsuario,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                lista -> lista.stream()
                                        .sorted(Comparator.comparing(RegistroViaje::timestamp))
                                        .map(RegistroViaje::estacion)
                                        .toList()
                        )
                ));
    }

    /**
     * Tarea 5: Calcula el tiempo promedio de viaje en minutos por ruta.
     */
    public static Map<String, Double> calcularTiempoPromedioPorRuta(List<RegistroViaje> registros) {
        return registros.stream()
                .filter(r -> "salida".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        RegistroViaje::ruta,
                        Collectors.averagingDouble(salida ->
                            registros.stream()
                                    .filter(e -> "entrada".equalsIgnoreCase(e.accion()) &&
                                                 e.idUsuario().equals(salida.idUsuario()) &&
                                                 e.ruta().equals(salida.ruta()))
                                    .mapToDouble(e -> Duration.between(e.timestamp(), salida.timestamp()).toMinutes())
                                    .findFirst()
                                    .orElse(0.0)
                        )
                ));
    }

    /**
     * Tarea 6: Detecta qué estaciones superan un umbral de afluencia específico.
     */
    public static List<String> detectarEstacionesConSobrecarga(List<RegistroViaje> registros, long umbral) {
        return calcularAfluenciaPorEstacion(registros).entrySet().stream()
                .filter(entry -> entry.getValue() > umbral)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // --- UTILIDAD COMÚN ---

    /**
     * Ordena un conteo de mayor a menor cantidad y, ante empates, alfabéticamente por clave.
     * El LinkedHashMap conserva ese orden al recorrer el resultado.
     */
    private static <K extends Comparable<? super K>> Map<K, Long> ordenarDescendente(Map<K, Long> conteos) {
        return conteos.entrySet().stream()
                .sorted(Map.Entry.<K, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // --- DATOS SIMULADOS BASE ---
    private static List<RegistroViaje> cargarDatosSimulados() {
        LocalDateTime base = LocalDateTime.of(2026, 9, 22, 6, 0);
        return List.of(
                new RegistroViaje("U001", "Ruta A", "Estación Central", "entrada", base),
                new RegistroViaje("U001", "Ruta A", "Estación Norte", "salida", base.plusMinutes(25)),
                new RegistroViaje("U002", "Ruta B", "Estación Sur", "entrada", base.plusMinutes(5)),
                new RegistroViaje("U002", "Ruta B", "Estación Central", "salida", base.plusMinutes(35)),
                new RegistroViaje("U003", "Ruta A", "Estación Central", "entrada", base.plusMinutes(10)),
                new RegistroViaje("U004", "Ruta A", "Estación Central", "entrada", base.plusHours(1))
        );
    }
}