package org.example.model;

public class WinogradOriginal {
    public static double[][] multiply(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        double aux;
        int upsilon = P % 2;
        int gamma = P - upsilon;
        double[] y = new double[M];
        double[] z = new double[N];

        for (int i = 0; i < M; i++) {
            aux = 0;
            for (int j = 0; j < gamma; j += 2) {
                aux += A[i][j] * A[i][j + 1];
            }
            y[i] = aux;
        }

        for (int i = 0; i < N; i++) {
            aux = 0;
            for (int j = 0; j < gamma; j += 2) {
                aux += B[j][i] * B[j + 1][i];
            }
            z[i] = aux;
        }

        if (upsilon == 1) {
            int PP = P - 1;
            for (int i = 0; i < M; i++) {
                for (int k = 0; k < N; k++) {
                    aux = 0;
                    for (int j = 0; j < gamma; j += 2) {
                        aux += (A[i][j] + B[j + 1][k]) * (A[i][j + 1] + B[j][k]);
                    }
                    Result[i][k] = aux - y[i] - z[k] + A[i][PP] * B[PP][k];
                }
            }
        } else {
            for (int i = 0; i < M; i++) {
                for (int k = 0; k < N; k++) {
                    aux = 0;
                    for (int j = 0; j < gamma; j += 2) {
                        aux += (A[i][j] + B[j + 1][k]) * (A[i][j + 1] + B[j][k]);
                    }
                    Result[i][k] = aux - y[i] - z[k];
                }
            }
        }

        return Result;
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
