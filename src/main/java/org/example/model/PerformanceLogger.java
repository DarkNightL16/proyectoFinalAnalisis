package org.example.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase PerformanceLogger proporciona un método estático para registrar el rendimiento de un algoritmo.
 * Los datos de rendimiento se escriben en un archivo llamado "performance_log.txt".
 */
public class PerformanceLogger {
    // Nombre del archivo donde se escribirán los datos de rendimiento
    private static final String FILE_NAME = "./src/main/java/org/example/file_cases/Tiempos_ejecucion.txt";

    /**
     * Registra el rendimiento de un algoritmo.
     *
     * @param algorithmName el nombre del algoritmo
     * @param inputSize el tamaño de la entrada al algoritmo
     * @param executionTime el tiempo de ejecución del algoritmo en milisegundos
     */
    public static void registrarRendimientoA(int inputSize, String algorithmName, double executionTime) {
        // Utiliza try-with-resources para asegurar que el BufferedWriter se cierre después de su uso
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Escribe los encabezados si el archivo está vacío
            if (new File(FILE_NAME).length() == 0) {
                writer.write("Matriz Size        Algorithm            Execution Time (ms)\n");
            }
            // Escribe los datos de rendimiento en el archivo con formato adecuado
            writer.write(String.format("%-15d %-30s %-15.6f\n", inputSize, algorithmName, executionTime));
        } catch (IOException e) {
            // Imprime la traza de pila para cualquier IOException
            e.printStackTrace();
        }
    }
}
