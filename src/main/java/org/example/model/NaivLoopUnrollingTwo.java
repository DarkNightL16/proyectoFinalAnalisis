package org.example.model;

public class NaivLoopUnrollingTwo {
    public static double[][] multiply(double[][] A, double[][] B) {
        int N = A.length;
        int P = A[0].length;
        int M = B[0].length;
        double[][] Result = new double[N][M];
        int i;
        int j;
        int k;
        double aux;

        if (P % 2 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k += 2) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 2) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j];
                    }
                    Result[i][j] = aux + A[i][PP] * B[PP][j];
                }
            }
        }

        return Result;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = multiply(A, B);
        printMatrix(C);
    }
}
