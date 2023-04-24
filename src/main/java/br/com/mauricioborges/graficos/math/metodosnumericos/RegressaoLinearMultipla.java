package br.com.mauricioborges.graficos.math.metodosnumericos;

import static br.com.mauricioborges.graficos.utils.ArrayUtils.copy;
import static java.lang.Math.pow;

/**
 * Método numérico para aproximar um conjunto de pontos por uma função
 * polinomial
 *
 * @author Mauricio Borges
 * @since 2018
 */
public class RegressaoLinearMultipla {

    // parâmetros do método
    private int n;
    private final int v;
    private final int p;
    private Double[][] X;
    private Double[] Y;
    private double b0 = Double.MAX_VALUE;

    // resultados
    private double r2 = 0;
    private double sigma2 = 0;

    /**
     * REGRESSÃO LINEAR MÚLTIPLA
     *
     * @param n (número de pontos)
     * @param v (número de variáveis)
     * @param p (número de parâmetros)
     * @param X (matriz de dimensão N x V contendo as variáveis explicativas)
     * @param Y (matriz de dimensão N contendo as variáveis respostas)
     */
    public RegressaoLinearMultipla(int n, int v, int p, Double[][] X, Double[] Y) {
        this.n = n;
        this.v = v;
        this.p = p;
        this.X = X;
        this.Y = Y;
    }

    /**
     * REGRESSÃO LINEAR MÚLTIPLA
     *
     * @param n (número de pontos)
     * @param v (número de variáveis)
     * @param p (número de parâmetros)
     * @param X (matriz de dimensão N x V contendo as variáveis explicativas)
     * @param Y (matriz de dimensão N contendo as variáveis respostas)
     * @param b0 (ponto de intersecção com o eixo Y)
     */
    public RegressaoLinearMultipla(int n, int v, int p, Double[][] X, Double[] Y, double b0) {
        this.n = n;
        this.v = v;
        this.p = p;
        this.X = X;
        this.Y = Y;
        this.b0 = b0;
    }

    /**
     * Obter o coeficiente de determinação
     *
     * @return coeficiente de determinação
     */
    public double getR2() {
        return r2;
    }

    /**
     * Obter a variância residual
     *
     * @return variância residual
     */
    public double getSigma2() {
        return sigma2;
    }

    /**
     * Resolve o método
     *
     * @return coeficiêntes de regressão
     */
    public Double[] solve() {
        if (v > 1 && v + 1 != p) {
            throw new ArithmeticException("ERRO regressaoLinearMultipla! Não é possível calcular a regressão.");
        }

        if (b0 != Double.MAX_VALUE) {
            RegressaoLinearMultipla rlm = new RegressaoLinearMultipla(n, v, p, copy(X), Y.clone());
            rlm.solve();
            r2 = rlm.getR2();
            sigma2 = rlm.getSigma2();

            int addPontos = (int) 5e5;
            Double[][] Xn = new Double[X.length + addPontos][X[1].length];
            Double[] Yn = new Double[Y.length + addPontos];
            for (int i = 1; i < X.length; i++) {
                Xn[i][1] = X[i][1];
                Yn[i] = Y[i];
            }
            int oldLength = X.length;
            X = Xn;
            Y = Yn;
            for (int i = oldLength; i < X.length; i++) {
                X[i][1] = 0d;
                Y[i] = b0;
            }
            n += addPontos;
        }

        int vp1 = v + 1;
        int pm1 = p - 1;
        for (int i = 1; i <= n; i++) {
            for (int j = vp1; j >= 2; j--) {
                X[i][j] = X[i][j - 1];
            }
            X[i][1] = 1.0;
        }
        if (v == 1 && p > 2) {
            for (int j = 2; j <= pm1; j++) {
                int jp1 = j + 1;
                for (int i = 1; i <= n; i++) {
                    X[i][jp1] = pow(X[i][2], 2);
                }
            }
        }
        // equações normais
        Double[][] Sxx = new Double[p + 1][p + 1];
        Double[] Sxy = new Double[p + 1];
        double soma;
        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= p; j++) {
                soma = 0;
                for (int k = 1; k <= n; k++) {
                    soma += X[k][i] * X[k][j];
                }
                Sxx[i][j] = soma;
            }
            soma = 0;
            for (int k = 1; k <= n; k++) {
                soma += X[k][i] * Y[k];
            }
            Sxy[i] = soma;
        }

        Double[][] L = new Cholesky(p, Sxx).solve();

        Double[] t = new SubstituicoesSucessivas(p, L, Sxy).solve();
        Double[][] U = new Double[p + 1][p + 1];
        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= i; j++) {
                U[j][i] = L[i][j];
            }
        }

        Double[] b = new SubstituicoesRetroativas(p, U, t).solve();
        double D = 0;
        double Sy2 = 0;
        for (int i = 1; i <= n; i++) {
            double u = 0;
            for (int j = 1; j <= p; j++) {
                u += b[j] * X[i][j];
            }
            double d = Y[i] - u;
            D += pow(d, 2);
            Sy2 += pow(Y[i], 2);
        }

        if (b0 == Double.MAX_VALUE) {
            r2 = 1 - (D / (Sy2 - (pow(Sxy[1], 2) / n))); // coeficiente de determinação
            sigma2 = D / (n - p); // variância residual
        }

        return b;
    }
}
