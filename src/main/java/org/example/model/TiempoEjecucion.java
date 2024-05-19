package org.example.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TiempoEjecucion {
        protected static final List<String> categorias = new ArrayList<>();
        protected static final List<Double> tiemposEjecucion = new ArrayList<>();
    public static void calcularTiempos(int i) {

        double[][] a;
        double[][] b;
        try {
            a = GeneradorMatrices.leerMatriz("./src/main/java/org/example/file_matrices/matriz_" + i + "_" + "num" + 1 + ".txt");
            b = GeneradorMatrices.leerMatriz("./src/main/java/org/example/file_matrices/matriz_" + i + "_" + "num" + 2 + ".txt");
            double[][] resultado = new double[a.length][b[0].length];
            int n = a.length;
            int p = b.length;
            int m = b[0].length;

            calcularNaivOnArray(a, b);
            calcularNaivLoopUnrollingTwo(a, b);
            calcularNaivLoopUnrollingFour(a, b);
            calcularWinogradOriginal(a, b, resultado, n, p, m);
            calcularWinogradScaled(a, b, resultado, n, p, m);
            calcularStrassenNaiv(a, b); //Realentiza demasiado el programa
            calcularStrassenWinograd(a, b, resultado, n, p, m);
            calcularIII3SequentialBlock(a, b, i, i/4);
            calcularIII4ParallelBlock(a, b);
            calcularIII5EnhanchedParallelBlock(a, b);
            calcularIV3SequentialBlock(a, b, i, i/4);
            calcularIV4ParallelBlock(a, b);
            calcularIV5EnhanchedParallelBlock(a, b);
            calcularV3SequentialBlock(a,b,i,i/4);
            calcularV4ParallelBlock(a,b);
            
            //almacenar el gráfico
            ChartGenerator.generateBarChart(
                categorias,
                tiemposEjecucion,
                "Tiempos de ejecución de los algoritmos con matrices de tamaño " + i,
                "Algoritmo",
                "Tiempo de ejecucion (ms)",
                i
        );

        categorias.clear();
        tiemposEjecucion.clear();

        JOptionPane.showMessageDialog(null, "Tiempos de ejecución de los algoritmos con matrices de tamaño " + i + " generados correctamente");

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }

    }
    private static void calcularV4ParallelBlock(double[][] a, double[][] b) {
        double start = System.nanoTime();
        V4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("V4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución V4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length, "V.4ParallelBlock ", executionTime);
    }
    private static void calcularV3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        double start = System.nanoTime();
        V3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("V3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución V3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"V.3SequentialBlock ", executionTime);
    }
    private static void calcularIV5EnhanchedParallelBlock(double[][] a, double[][] b) {
        double start = System.nanoTime();
        IV5_EnhancedParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("IV5.EnhanchedParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV5.EnhanchedParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"IV.5EnhanchedParallelBlock ", executionTime);
    }
    private static void calcularIV4ParallelBlock(double[][] a, double[][] b) {
        double start = System.nanoTime();
        IV4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("IV4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"IV.4ParallelBlock ", executionTime);
    }
    private static void calcularIV3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        double start = System.nanoTime();
        IV3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("IV3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"IV.3SequentialBlock ", executionTime);
    }
    private static void calcularIII5EnhanchedParallelBlock(double[][] a, double[][] b) {
        double start = System.nanoTime();
        III5_EnhancedParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("III5.EnhanchedParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III5.EnhanchedParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"III.5EnhanchedParallelBlock ", executionTime);
    }
    private static void calcularIII4ParallelBlock(double[][] a, double[][] b) {
        double start = System.nanoTime();
        III4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("III4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"III.4ParallelBlock ", executionTime);
    }
    private static void calcularIII3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        double start = System.nanoTime();
        III3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("III3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"III.3SequentialBlock ", executionTime);
    }
    private static void calcularStrassenWinograd(double[][] a, double[][] b, double[][] resultado, int n, int p,
            int m) {
        double start = System.nanoTime();
        StrassenWinograd.multiply(a, b, resultado, n, p, m);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("StrassenWinograd");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución StrassenWinograd: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"StrassenWinograd ", executionTime);

    }
    private static void calcularStrassenNaiv(double[][] a, double[][] b) {
        double start = System.nanoTime();
        StrassenNaiv.multiply(a, b);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("StrassenNaiv");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución StrassenNaiv: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"StrassenNaiv ", executionTime);
    }
    private static void calcularWinogradScaled(double[][] a, double[][] b, double[][] resultado, int n, int p, int m) {
        double start = System.nanoTime();
        WinogradScaled.multiply(a, b, resultado, n, p, m);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("WinogradScaled");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución WinogradScaled: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"WinogradScaled ", executionTime);
    }
    private static void calcularWinogradOriginal(double[][] a, double[][] b, double[][] resultado, int n, int p,
            int m) {
        double start = System.nanoTime();
        WinogradOriginal.multiply(a, b, resultado, n, p, m);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("WinogradOriginal");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución WinogradOriginal: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"WinogradOriginal ", executionTime);
        
    }
    private static void calcularNaivLoopUnrollingFour(double[][] a, double[][] b) {
        double start = System.nanoTime();
        NaivLoopUnrollingFour.multiply(a, b);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("NaivLoopUnrollingFour");
        tiemposEjecucion.add(executionTime);    
        System.out.println("Tiempo de ejecución NaivLoopUnrollingFour: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"NaivLoopUnrollingFour ", executionTime);
    }
    private static void calcularNaivLoopUnrollingTwo(double[][] a, double[][] b) {
        double start = System.nanoTime();
        NaivLoopUnrollingTwo.multiply(a, b);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("NaivLoopUnrollingTwo");
        tiemposEjecucion.add(executionTime);    
        System.out.println("Tiempo de ejecución NaivLoopUnrollingTwo: " + executionTime + " milisegundos");
        PerformanceLogger.registrarRendimientoA(a.length,"NaivLoopUnrollingTwo ", executionTime);
    }
    private static void calcularNaivOnArray(double[][] a, double[][] b) {
        double start = System.nanoTime();
        NaivOnArray.multiply(a, b);
        double end = System.nanoTime();
        double executionTime = (end - start) / 1000000;
        categorias.add("NaivOnArray");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución NaivOnArray: " + executionTime + " milisegundos");    
        PerformanceLogger.registrarRendimientoA(a.length,"NaivOnArray ", executionTime);
    }

}
