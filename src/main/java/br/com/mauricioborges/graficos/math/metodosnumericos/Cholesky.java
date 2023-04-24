package br.com.mauricioborges.graficos.math.metodosnumericos;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Método Cholesky
 *
 * @author Mauricio Borges
 * @since 2018
 */
public class Cholesky {

    // parâmetros do método
    private final int n;
    private final Double[][] A;

    // resultados
    private double determinante = 1;

    /**
     * MÉTODO CHOLESKY
     *
     * @param n (ordem da matriz)
     * @param A (matriz a ser decomposta)
     */
    public Cholesky(int n, Double[][] A) {
        this.n = n;
        this.A = A;
    }

    /**
     * Obter o determinante
     *
     * @return determinante
     */
    public double getDeterminante() {
        return determinante;
    }

    /**
     * Resolve o método
     *
     * @return fator L escrito sobre A
     */
    public Double[][] solve() {
        for (int j = 1; j <= n; j++) {
            double soma = 0;
            for (int k = 1; k <= (j - 1); k++) {
                soma += pow(A[j][k], 2);
            }
            double t = A[j][j] - soma;
            double r;
            if (t > 0) {
                A[j][j] = sqrt(t);
                r = 1 / A[j][j];
                determinante *= t;
            } else {
                throw new ArithmeticException("ERRO metodoCholesky: A matriz não é definida positiva!");
            }
            for (int i = (j + 1); i <= n; i++) {
                soma = 0;
                for (int k = 1; k <= (j - 1); k++) {
                    soma += A[i][k] * A[j][k];
                }
                A[i][j] = (A[i][j] - soma) * r;
            }
        }
        return A;
    }
}
