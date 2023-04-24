package br.com.mauricioborges.graficos.math.metodosnumericos;

/**
 * Método das Substituições Retroativas
 *
 * @author Mauricio Borges
 * @since 2018
 */
public class SubstituicoesRetroativas {

    // parâmetros do método
    private final int n;
    private final Double[][] U;
    private final Double[] D;

    /**
     * MÉTODO DAS SUBSTITUIÇÕES RETROATIVAS
     *
     * @param n (ordem da matriz U)
     * @param U (matriz triangular superior)
     * @param D (vetor independente)
     */
    public SubstituicoesRetroativas(int n, Double[][] U, Double[] D) {
        this.n = n;
        this.U = U;
        this.D = D;
    }

    /**
     * Resolve o método
     *
     * @return solução do sistema triangular superior
     */
    public Double[] solve() {
        Double[] X = new Double[n + 1];
        X[n] = D[n] / U[n][n];
        for (int i = (n - 1); i >= 1; i--) {
            double soma = 0;
            for (int j = (i + 1); j <= n; j++) {
                soma += U[i][j] * X[j];
            }
            X[i] = (D[i] - soma) / U[i][i];
        }
        return X;
    }
}
