package org.example.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IV4_ParallelBlock {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = matrixMultiplicationParallel(A, B, A.length, 1);
        printMatrix(C);
        
    }

    public static double[][] matrixMultiplicationParallel(double[][] A, double[][] B, int size, int bsize) {
        double[][] C = new double[size][size];
    
        // Inicialización de C a ceros
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = 0;
            }
        }
    
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    
        executor.execute(() -> {
            for (int i1 = 0; i1 < size; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {
                                    C[i][k] += A[i][j] * B[j][k];
                                }
                            }
                        }
                    }
                }
            }
        });
    
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    
        return C;
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
