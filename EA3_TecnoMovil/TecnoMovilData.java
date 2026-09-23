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
        System.out.println("--- 1. Afluencia (Entradas) por Estación ---");
        calcularAfluenciaPorEstacion(dataset)
                .forEach((estacion, total) -> System.out.printf("Estación: %-15s | Entradas: %d%n", estacion, total));

        // 2. Identificación de horas pico
        System.out.println("\n--- 2. Identificación de Horas Pico ---");
        obtenerHorasPico(dataset)
                .forEach((hora, flujo) -> System.out.printf("Hora: %02d:00 | Flujo total: %d entradas%n", hora, flujo));
        
        // NOTA PARA EL GRUPO: Los compañeros Santiago y Miguel deben agregar las llamadas a sus funciones aquí abajo.
    }

    // --- TAREAS FUNCIONALES ---

    public static Map<String, Long> calcularAfluenciaPorEstacion(List<RegistroViaje> registros) {
        return registros.stream()
                .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        RegistroViaje::estacion,
                        Collectors.counting()
                ));
    }

    public static Map<Integer, Long> obtenerHorasPico(List<RegistroViaje> registros) {
        return registros.stream()
                .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        r -> r.timestamp().getHour(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
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