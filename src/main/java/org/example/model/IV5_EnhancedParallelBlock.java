package org.example.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IV5_EnhancedParallelBlock {
    

    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static double[][] matrixMultiplicationParallel(double[][] A, double[][] B, int size, int bsize) {
        double[][] result = new double[size][size];

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // First part of the matrix multiplication
        executor.execute(() -> {
            for (int i1 = 0; i1 < size / 2; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {
                                    result[i][k] += A[i][j] * B[j][k];
                                }
                            }
                        }
                    }
                }
            }
        });

        // Second part of the matrix multiplication
        executor.execute(() -> {
            for (int i1 = size / 2; i1 < size; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {
                                    result[i][k] += A[i][j] * B[j][k];
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

        return result;
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] result = matrixMultiplicationParallel(A, B, 2, 1);

        // Print the result matrix
        System.out.println("Result Matrix:");
        printMatrix(result);
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
