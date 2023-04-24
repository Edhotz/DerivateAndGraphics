package br.com.mauricioborges.graficos;

import static java.util.Objects.requireNonNull;

/**
 * Linha de tendência para gráficos de conjuntos de pontos
 *
 * @author Mauricio Borges
 * @since 10/2022
 */
public class LinhaDeTendencia {

    /**
     * Linear (polinomial de grau 1)
     */
    public static final LinhaDeTendencia LINEAR = new LinhaDeTendencia.Builder(Tipo.POLINOMIAL).setGrau(1).build();
    /**
     * Quadrática (polinomial de grau 2)
     */
    public static final LinhaDeTendencia QUADRATICA = new LinhaDeTendencia.Builder(Tipo.POLINOMIAL).setGrau(2).build();
    /**
     * Exponencial
     */
    public static final LinhaDeTendencia EXPONENCIAL = new LinhaDeTendencia.Builder(Tipo.EXPONENCIAL).build();
    /**
     * Logarítmica
     */
    public static final LinhaDeTendencia LOGARITMICA = new LinhaDeTendencia.Builder(Tipo.LOGARITMICA).build();
    /**
     * Potência de x
     */
    public static final LinhaDeTendencia POTENCIA = new LinhaDeTendencia.Builder(Tipo.POTENCIA).build();
    /**
     * Média móvel
     */
    public static final LinhaDeTendencia MEDIA_MOVEL = new LinhaDeTendencia.Builder(Tipo.MEDIA_MOVEL).build();

    // parâmetros da linha de tendência
    private final Tipo tipo;
    private int grau = 1; // linhas de tendência polinomiais
    private int numeroDePontos = 2; // linha de tendência média móvel
    private double inicio = Double.MAX_VALUE;
    private double fim = Double.MAX_VALUE;
    private String titulo = null;
    private double b0 = Double.MAX_VALUE;

    // estilo
    private Estilo estilo = Estilo.LINHA;

    // informações
    private boolean exibirEquacao = true;
    private boolean exibirR2 = false;
    private boolean exibirSigma2 = false;

    /**
     * Construtor privado para não permitir a criação de instâncias fora da
     * classe
     *
     * @param tipo tipo da linha de tendência
     */
    private LinhaDeTendencia(Tipo tipo) {
        this.tipo = requireNonNull(tipo, "O tipo da linha de tendência não pode ser nulo.");
    }

    /**
     * Obter o tipo da linha de tendência
     *
     * @return tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obter o grau do polinômio (padrão é 1)
     *
     * @return grau
     */
    public int getGrau() {
        return grau;
    }

    /**
     * Definir o grau do polinômio (padrão é 1)
     *
     * @param grau grau
     */
    public void setGrau(int grau) {
        if (tipo != Tipo.POLINOMIAL) {
            throw new UnsupportedOperationException("Só é possível alterar o grau de linhas de tendência polinomiais.");
        }
        if (grau < 0) {
            throw new IllegalArgumentException("O grau da linha de tendência não pode ser negativo.");
        }
        this.grau = grau;
    }

    /**
     * Obter o número de pontos da média móvel
     *
     * @return número de pontos
     */
    public int getNumeroDePontos() {
        return numeroDePontos;
    }

    /**
     * Definir o número de pontos da média móvel
     *
     * @param numeroDePontos número de pontos
     */
    public void setNumeroDePontos(int numeroDePontos) {
        if (tipo != Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Só é possível alterar o número de pontos de linha de tendência média móvel.");
        }
        if (numeroDePontos < 2) {
            throw new IllegalArgumentException("O número de pontos da média móvel não pode ser menor do que 2.");
        }
        this.numeroDePontos = numeroDePontos;
    }

    /**
     * Obter o início do intervalo
     *
     * @return valor (se nenhum valor tiver sido definido, retornará
     * Double.MAX_VALUE)
     */
    public double getInicio() {
        return inicio;
    }

    /**
     * Definir o início do intervalo
     *
     * @param inicio início
     */
    public void setInicio(double inicio) {
        if (tipo == Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Não é possível definir o início do intervalo de linha de tendência média móvel");
        }
        this.inicio = inicio;
    }

    /**
     * Obter o fim do intervalo
     *
     * @return valor (se nenhum valor tiver sido definido, retornará
     * Double.MAX_VALUE)
     */
    public double getFim() {
        return fim;
    }

    /**
     * Definir o fim do intervalo
     *
     * @param fim fim
     */
    public void setFim(double fim) {
        if (tipo == Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Não é possível definir o fim do intervalo de linha de tendência média móvel");
        }
        this.fim = fim;
    }

    /**
     * Obter o título da linha de tendência (padrão é null)
     *
     * @return título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Definir o título da linha de tendência (padrão é null)
     *
     * @param titulo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obter o ponto de intersecção com o eixo Y
     *
     * @return valor (se nenhum valor tiver sido definido, retornará
     * Double.MAX_VALUE)
     */
    public double getB0() {
        return b0;
    }

    /**
     * Definir o ponto de intersecção com o eixo Y
     *
     * @param b0 valor
     */
    public void setB0(double b0) {
        if (tipo == Tipo.LOGARITMICA || tipo == Tipo.POTENCIA || tipo == Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Não é possível alterar o ponto de intersecção "
                    + "com o eixo Y de linha de tendência " + tipo.toString().toLowerCase());
        }
        if (tipo == Tipo.EXPONENCIAL && b0 <= 0) {
            throw new IllegalArgumentException("Não é possível uma intersecção do eixo Y em valores "
                    + "menores ou iguais a zero para uma função exponencial");
        }
        this.b0 = tipo == Tipo.EXPONENCIAL ? Math.log(b0) : b0;
    }

    /**
     * Obter o estilo da linha de tendência (padrão é Estilo.LINHA)
     *
     * @return estilo
     */
    public Estilo getEstilo() {
        return estilo;
    }

    /**
     * Definir o estilo da linha de tendência (padrão é Estilo.LINHA)
     *
     * @param estilo estilo
     */
    public void setEstilo(Estilo estilo) {
        this.estilo = requireNonNull(estilo, "O estilo não pode ser nulo");
    }

    /**
     * Exibir ou não a equação da linha de tendência (padrão é true)
     *
     * @return true or false
     */
    public boolean exibirEquacao() {
        return exibirEquacao;
    }

    /**
     * Exibir ou não a equação da linha de tendência (padrão é true)
     *
     * @param exibirEquacao true or false
     */
    public void setExibirEquacao(boolean exibirEquacao) {
        if (tipo == Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Não é possível exibir a equação de linha de tendência média móvel");
        }
        this.exibirEquacao = exibirEquacao;
    }

    /**
     * Exibir ou não o coeficiente de determinação (padrão é false)
     *
     * @return true or false
     */
    public boolean exibirR2() {
        return exibirR2;
    }

    /**
     * Exibir ou não o coeficiente de determinação (padrão é false)
     *
     * @param exibirR2 true or false
     */
    public void setExibirR2(boolean exibirR2) {
        if (tipo == Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Não é possível exibir o r² de linha de tendência média móvel");
        }
        this.exibirR2 = exibirR2;
    }

    /**
     * Exibir ou não a variância residual (padrão é false)
     *
     * @return true or false
     */
    public boolean exibirSigma2() {
        return exibirSigma2;
    }

    /**
     * Exibir ou não a variância residual (padrão é false)
     *
     * @param exibirSigma2 true or false
     */
    public void setExibirSigma2(boolean exibirSigma2) {
        if (tipo == Tipo.MEDIA_MOVEL) {
            throw new UnsupportedOperationException("Não é possível exibir o σ² de linha de tendência média móvel");
        }
        this.exibirSigma2 = exibirSigma2;
    }

    /**
     * Tipos de linha de tendência
     */
    public static enum Tipo {
        /**
         * Linha de tendência polinomial
         */
        POLINOMIAL("Polinomial"),
        /**
         * Linha de tendência exponencial
         */
        EXPONENCIAL("Exponencial"),
        /**
         * Linha de tendência logarítmica
         */
        LOGARITMICA("Logarítmica"),
        /**
         * Linha de tendência de potência de x
         */
        POTENCIA("Potência de x"),
        /**
         * Linha de tendência média móvel
         */
        MEDIA_MOVEL("Média móvel");

        private final String titulo;

        private Tipo(String titulo) {
            this.titulo = titulo;
        }

        @Override
        public String toString() {
            return this.titulo;
        }
    }

    /**
     * Builder para construir uma linha de tendência com os parâmetros desejados
     */
    public static class Builder {

        private final LinhaDeTendencia linhaDeTendencia;

        /**
         * Para iniciar construção da linha de tendência, é necessário informar
         * o tipo
         *
         * @param tipo tipo da linha de tendência
         */
        public Builder(Tipo tipo) {
            this.linhaDeTendencia = new LinhaDeTendencia(tipo);
        }

        /**
         * Definir o grau do polinômio (padrão é 1)
         *
         * @param grau grau
         * @return a própria instância do Builder
         */
        public Builder setGrau(int grau) {
            this.linhaDeTendencia.setGrau(grau);
            return this;
        }

        /**
         * Definir o número de pontos da média móvel
         *
         * @param numeroDePontos número de pontos
         * @return a própria instância do Builder
         */
        public Builder setNumeroDePontos(int numeroDePontos) {
            this.linhaDeTendencia.setNumeroDePontos(numeroDePontos);
            return this;
        }

        /**
         * Definir o início do intervalo
         *
         * @param inicio início
         * @return a própria instância do Builder
         */
        public Builder setInicio(double inicio) {
            this.linhaDeTendencia.setInicio(inicio);
            return this;
        }

        /**
         * Definir o fim do intervalo
         *
         * @param fim fim
         * @return a própria instância do Builder
         */
        public Builder setFim(double fim) {
            this.linhaDeTendencia.setFim(fim);
            return this;
        }

        /**
         * Definir o título da linha de tendência (padrão é null)
         *
         * @param titulo título
         * @return a própria instância do Builder
         */
        public Builder setTitulo(String titulo) {
            this.linhaDeTendencia.setTitulo(titulo);
            return this;
        }

        /**
         * Definir o ponto de intersecção com o eixo Y
         *
         * @param b0 valor
         * @return a própria instância do Builder
         */
        public Builder setB0(double b0) {
            this.linhaDeTendencia.setB0(b0);
            return this;
        }

        /**
         * Definir o estilo da linha de tendência (padrão é Estilo.LINHA)
         *
         * @param estilo estilo
         * @return a própria instância do Builder
         */
        public Builder setEstilo(Estilo estilo) {
            this.linhaDeTendencia.setEstilo(estilo);
            return this;
        }

        /**
         * Exibir ou não a equação da linha de tendência (padrão é true)
         *
         * @param exibirEquacao true or false
         * @return a própria instância do Builder
         */
        public Builder setExibirEquacao(boolean exibirEquacao) {
            this.linhaDeTendencia.setExibirEquacao(exibirEquacao);
            return this;
        }

        /**
         * Exibir ou não o coeficiente de determinação (padrão é false)
         *
         * @param exibirR2 true or false
         * @return a própria instância do Builder
         */
        public Builder setExibirR2(boolean exibirR2) {
            this.linhaDeTendencia.setExibirR2(exibirR2);
            return this;
        }

        /**
         * Exibir ou não a variância residual (padrão é false)
         *
         * @param exibirSigma2 true or false
         * @return a própria instância do Builder
         */
        public Builder setExibirSigma2(boolean exibirSigma2) {
            this.linhaDeTendencia.setExibirSigma2(exibirSigma2);
            return this;
        }

        /**
         * Constrói o objeto
         *
         * @return linha de tendência com os parâmetros desejados
         */
        public LinhaDeTendencia build() {
            return this.linhaDeTendencia;
        }
    }
}
