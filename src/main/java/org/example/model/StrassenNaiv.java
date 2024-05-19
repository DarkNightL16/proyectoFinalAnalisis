package org.example.model;

public class StrassenNaiv {
    public static double[][] multiply(double[][] A, double[][] B) {
        int N = A.length;
        int P = A[0].length;
        int M = B[0].length;
        int MaxSize, k, m, NewSize, i, j;
        MaxSize = Math.max(N, Math.max(P, M));
        if (MaxSize < 16) {
            MaxSize = 16; // otherwise it is not possible to compute k
        }
        k = (int) (Math.floor(Math.log(MaxSize) / Math.log(2)) - 4);
        m = (int) (Math.floor(MaxSize * Math.pow(2, -k)) + 1);
        NewSize = m * (int) Math.pow(2, k);
        // add zero rows and columns to use Strassens algorithm
        double[][] NewA = new double[NewSize][NewSize];
        double[][] NewB = new double[NewSize][NewSize];
        double[][] AuxResult = new double[NewSize][NewSize];
        for (i = 0; i < NewSize; i++) {
            for (j = 0; j < NewSize; j++) {
                NewA[i][j] = 0.0;
                NewB[i][j] = 0.0;
            }
        }
        for (i = 0; i < N; i++) {
            System.arraycopy(A[i], 0, NewA[i], 0, P);
        }
        for (i = 0; i < P; i++) {
            System.arraycopy(B[i], 0, NewB[i], 0, M);
        }
        StrassenNaivStep(NewA, NewB, AuxResult, NewSize, m);
        // extract the result
        double[][] Result = new double[N][M];
        for (i = 0; i < N; i++) {
            System.arraycopy(AuxResult[i], 0, Result[i], 0, M);
        }
        return Result;
    }

    public static void StrassenNaivStep(double[][] A, double[][] B, double[][] Result, int N, int m) {
        int i, j, NewSize;
        if (N % 2 == 0 && N > m) { // recursive use of StrassenNaivStep
            NewSize = N / 2;
            // Decompose A and B
            // Create ResultPart, Aux1,...,Aux7 and Helper1, Helper2
            double[][] A11 = new double[NewSize][NewSize];
            double[][] A12 = new double[NewSize][NewSize];
            double[][] A21 = new double[NewSize][NewSize];
            double[][] A22 = new double[NewSize][NewSize];
            double[][] B11 = new double[NewSize][NewSize];
            double[][] B12 = new double[NewSize][NewSize];
            double[][] B21 = new double[NewSize][NewSize];
            double[][] B22 = new double[NewSize][NewSize];
            double[][] ResultPart11 = new double[NewSize][NewSize];
            double[][] ResultPart12 = new double[NewSize][NewSize];
            double[][] ResultPart21 = new double[NewSize][NewSize];
            double[][] ResultPart22 = new double[NewSize][NewSize];
            double[][] Helper1 = new double[NewSize][NewSize];
            double[][] Helper2 = new double[NewSize][NewSize];
            double[][] Aux1 = new double[NewSize][NewSize];
            double[][] Aux2 = new double[NewSize][NewSize];
            double[][] Aux3 = new double[NewSize][NewSize];
            double[][] Aux4 = new double[NewSize][NewSize];
            double[][] Aux5 = new double[NewSize][NewSize];
            double[][] Aux6 = new double[NewSize][NewSize];
            double[][] Aux7 = new double[NewSize][NewSize];
            // Fill new matrices
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    A11[i][j] = A[i][j];
                    A12[i][j] = A[i][NewSize + j];
                    A21[i][j] = A[NewSize + i][j];
                    A22[i][j] = A[NewSize + i][NewSize + j];
                    B11[i][j] = B[i][j];
                    B12[i][j] = B[i][NewSize + j];
                    B21[i][j] = B[NewSize + i][j];
                    B22[i][j] = B[NewSize + i][NewSize + j];
                }
            }
            // Computing the seven aux. variables
            Plus(A11, A22, Helper1, NewSize, NewSize);
            Plus(B11, B22, Helper2, NewSize, NewSize);
            StrassenNaivStep(Helper1, Helper2, Aux1, NewSize, m);
            Plus(A21, A22, Helper1, NewSize, NewSize);
            StrassenNaivStep(Helper1, B11, Aux2, NewSize, m);
            Minus(B12, B22, Helper1, NewSize, NewSize);
            StrassenNaivStep(A11, Helper1, Aux3, NewSize, m);
            Minus(B21, B11, Helper1, NewSize, NewSize);
            StrassenNaivStep(A22, Helper1, Aux4, NewSize, m);
            Plus(A11, A12, Helper1, NewSize, NewSize);
            StrassenNaivStep(Helper1, B22, Aux5, NewSize, m);
            Minus(A21, A11, Helper1, NewSize, NewSize);
            Plus(B11, B12, Helper2, NewSize, NewSize);
            StrassenNaivStep(Helper1, Helper2, Aux6, NewSize, m);
            Minus(A12, A22, Helper1, NewSize, NewSize);
            Plus(B21, B22, Helper2, NewSize, NewSize);
            StrassenNaivStep(Helper1, Helper2, Aux7, NewSize, m);
            // Computing the four parts of the result
            Plus(Aux1, Aux4, ResultPart11, NewSize, NewSize);
            Minus(ResultPart11, Aux5, ResultPart11, NewSize, NewSize);
            Plus(ResultPart11, Aux7, ResultPart11, NewSize, NewSize);
            Plus(Aux3, Aux5, ResultPart12, NewSize, NewSize);
            Plus(Aux2, Aux4, ResultPart21, NewSize, NewSize);
            Plus(Aux1, Aux3, ResultPart22, NewSize, NewSize);
            Minus(ResultPart22, Aux2, ResultPart22, NewSize, NewSize);
            Plus(ResultPart22, Aux6, ResultPart22, NewSize, NewSize);
            // Store results in the "result matrix"
            for (i = 0; i < NewSize; i++) {
                System.arraycopy(ResultPart11[i], 0, Result[i], 0, NewSize);
                System.arraycopy(ResultPart12[i], 0, Result[i], NewSize, NewSize);
            }
            for (i = 0; i < NewSize; i++) {
                System.arraycopy(ResultPart21[i], 0, Result[NewSize + i], 0, NewSize);
                System.arraycopy(ResultPart22[i], 0, Result[NewSize + i], NewSize, NewSize);
            }
        } else {
            // Use naive algorithm
            NaivStandard(A, B, Result, N, N, N);
        }
    }

    public static void NaivStandard(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        double aux;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                aux = 0.0;
                for (int k = 0; k < P; k++) {
                    aux += A[i][k] * B[k][j];
                }
                Result[i][j] = aux;
            }
        }
    }

    public static void Plus(double[][] A, double[][] B, double[][] Result, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Result[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public static void Minus(double[][] A, double[][] B, double[][] Result, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Result[i][j] = A[i][j] - B[i][j];
            }
        }
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
