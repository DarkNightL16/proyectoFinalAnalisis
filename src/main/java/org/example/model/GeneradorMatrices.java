package org.example.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GeneradorMatrices {

    public static void generarMatricesA() {
        int[] sizes = {64, 128, 256, 512};
        String directory = "./src/main/java/org/example/file_matrices";
        Random random = new Random();

        for (int size : sizes) {
            for (int i = 0; i < 2; i++) { 
                int[][] matrix = new int[size][size];

                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < size; k++) {
                        matrix[j][k] = random.nextInt(900_000) + 100_000;
                    }
                }

                String filename = directory + "/matriz_" + size + "_" + "num" + (i + 1) + ".txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            writer.write(Integer.toString(matrix[j][k]));
                            if (k < size - 1) {
                                writer.write(", ");
                            }
                        }
                        writer.newLine();
                    }
                    System.out.println("Matriz de tamaño " + size + " generada y guardada en " + filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static double[][] leerMatriz(String fileName) throws FileNotFoundException {
        List<double[]> matrix = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(", ");
            double[] row = new double[line.length];
            for (int i = 0; i < line.length; i++) {
                row[i] = Double.parseDouble(line[i]);
            }
            matrix.add(row);
        }
        scanner.close();

        // Convert List<double[]> to double[][]
        double[][] result = new double[matrix.size()][];
        for (int i = 0; i < matrix.size(); i++) {
            result[i] = matrix.get(i);
        }
        return result;
    }
}