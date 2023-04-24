package br.com.mauricioborges.graficos.math.metodosnumericos;

/**
 * Método das Substituições Sucessivas
 *
 * @author Mauricio Borges
 * @since 2018
 */
public class SubstituicoesSucessivas {

    // parâmetros do método
    private final int n;
    private final Double[][] L;
    private final Double[] C;

    /**
     * MÉTODO DAS SUBSTITUIÇÕES SUCESSIVAS
     *
     * @param n (ordem da matriz L)
     * @param L (matriz triangular inferior)
     * @param C (vetor independente)
     */
    public SubstituicoesSucessivas(int n, Double[][] L, Double[] C) {
        this.n = n;
        this.L = L;
        this.C = C;
    }

    /**
     * Resolve o método
     *
     * @return solução do sistema triangular inferior
     */
    public Double[] solve() {
        Double[] X = new Double[n + 1];
        X[1] = C[1] / L[1][1];
        for (int i = 2; i <= n; i++) {
            double soma = 0;
            for (int j = 1; j <= (i - 1); j++) {
                soma += L[i][j] * X[j];
            }
            X[i] = (C[i] - soma) / L[i][i];
        }
        return X;
    }
}
