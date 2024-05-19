package org.example.model;


public class WinogradScaled {

    public static void multiply(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        // Crear copias escaladas de A y B
        double[][] CopyA = new double[N][P];
        double[][] CopyB = new double[P][M];
    
        // Factores de escalamiento
        double a = normInf(A, N, P);
        double b = normInf(B, P, M);
        double lambda = Math.floor(0.5 + Math.log(b / a) / Math.log(4));
    
        // Escalamiento
        multiplyWithScalar(A, CopyA, N, P, Math.pow(2, lambda));
        multiplyWithScalar(B, CopyB, P, M, Math.pow(2, -lambda));
    
        // Usando Winograd con matrices escaladas
        WinogradOriginal.multiply(CopyA, CopyB, Result, N, P, M);
    }
    
    // Función auxiliar para calcular la norma infinito de una matriz
    private static double normInf(double[][] matrix, int rows, int cols) {
        double maxVal = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxVal = Math.max(maxVal, Math.abs(matrix[i][j]));
            }
        }
        return maxVal;
    }
    
    // Función auxiliar para multiplicar una matriz por un escalar
    private static void multiplyWithScalar(double[][] source, double[][] target, int rows, int cols, double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                target[i][j] = source[i][j] * scalar;
            }
        }
    }

    public static void printMatrix(double[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] Result = new double[2][2];

        multiply(A, B, Result, 2, 2, 2);
        printMatrix(Result, 2, 2);
    }
}
