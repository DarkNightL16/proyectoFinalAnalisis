package org.example.model;

public class IV3_SequentialBlock{



    public static double[][] blockMatrixMultiply(double[][] A, double[][] B, int size, int bsize) {
        // Crear la matriz resultado
        double[][] result = new double[size][size];

        // Iterar sobre bloques i1, j1, k1
        for (int i1 = 0; i1 < size; i1 += bsize) {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    // Iterar dentro del bloque (i1, j1, k1)
                    for (int i = i1; i < i1 + bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                result[i][k] += A[i][j] * B[j][k]; // Multiplicación dentro del bloque
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = blockMatrixMultiply(A, B, 2,128);
        printMatrix(C);
        
    }

    // Función auxiliar para imprimir una matriz
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }
}
