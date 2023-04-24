package br.com.mauricioborges.graficos;

import br.com.mauricioborges.graficos.utils.ChartUtils.EstiloLinha;
import static java.util.Objects.requireNonNull;
import javafx.scene.paint.Color;

/**
 * Estilos para gráficos
 *
 * @author Maurício Borges
 * @since 09/2022
 */
public class Estilo {

    /**
     * Apenas linha
     */
    public static final Estilo LINHA = new Estilo.Builder().setExibirMarcador(false).build();
    /**
     * Apenas marcador
     */
    public static final Estilo MARCADOR = new Estilo.Builder().setExibirLinha(false).build();
    /**
     * Linha e marcador
     */
    public static final Estilo LINHA_E_MARCADOR = new Estilo.Builder().build();

    // parâmetros do estilo
    private boolean exibirLinha = true;
    private boolean exibirMarcador = true;
    private Color cor;
    private EstiloLinha estiloLinha = EstiloLinha.CONTINUA;

    /**
     * Construtor privado para não permitir a criação de instâncias fora da
     * classe
     */
    private Estilo() {
    }

    /**
     * Exibir ou não a linha do gráfico (padrão é true)
     *
     * @return true or false
     */
    public boolean exibirLinha() {
        return exibirLinha;
    }

    /**
     * Exibir ou não a linha do gráfico (padrão é true)<br>
     * Sem efeito para gráficos de funções e linhas de tendência
     *
     * @param exibirLinha true or false
     */
    public void setExibirLinha(boolean exibirLinha) {
        this.exibirLinha = exibirLinha;
    }

    /**
     * Exibir ou não o marcador nos pontos do gráfico (padrão é true)
     *
     * @return true or false
     */
    public boolean exibirMarcador() {
        return exibirMarcador;
    }

    /**
     * Exibir ou não o marcador nos pontos do gráfico (padrão é true)<br>
     * Sem efeito para gráficos de funções e linhas de tendência
     *
     * @param exibirMarcador true or false
     */
    public void setExibirMarcador(boolean exibirMarcador) {
        this.exibirMarcador = exibirMarcador;
    }

    /**
     * Obter a cor associada ao estilo (padrão é null)
     *
     * @return cor
     */
    public Color getCor() {
        return cor;
    }

    /**
     * Definir a cor associada ao estilo (padrão é null)
     *
     * @param cor cor
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }

    /**
     * Obter o estilo da linha do gráfico (padrão é EstiloLinha.CONTINUA)
     *
     * @return estilo da linha
     */
    public EstiloLinha getEstiloLinha() {
        return estiloLinha;
    }

    /**
     * Definir o estilo da linha do gráfico (padrão é EstiloLinha.CONTINUA)
     *
     * @param estiloLinha estilo da linha
     */
    public void setEstiloLinha(EstiloLinha estiloLinha) {
        this.estiloLinha = requireNonNull(estiloLinha, "O estilo da linha não pode ser nulo");
    }

    /**
     * Builder para construir um estilo com os parâmetros desejados
     */
    public static class Builder {

        private final Estilo estilo = new Estilo();

        /**
         * Exibir ou não a linha do gráfico (padrão é true)<br>
         * Sem efeito para gráficos de funções e linhas de tendência
         *
         * @param exibirLinha true or false
         * @return a própria instância do Builder
         */
        public Builder setExibirLinha(boolean exibirLinha) {
            this.estilo.setExibirLinha(exibirLinha);
            return this;
        }

        /**
         * Exibir ou não o marcador nos pontos do gráfico (padrão é true)<br>
         * Sem efeito para gráficos de funções e linhas de tendência
         *
         * @param exibirMarcador true or false
         * @return a própria instância do Builder
         */
        public Builder setExibirMarcador(boolean exibirMarcador) {
            this.estilo.setExibirMarcador(exibirMarcador);
            return this;
        }

        /**
         * Definir a cor associada ao estilo (padrão é null)
         *
         * @param cor cor
         * @return a própria instância do Builder
         */
        public Builder setCor(Color cor) {
            this.estilo.setCor(cor);
            return this;
        }

        /**
         * Definir o estilo da linha do gráfico (padrão é EstiloLinha.CONTINUA)
         *
         * @param estiloLinha estilo da linha
         * @return a própria instância do Builder
         */
        public Builder setEstiloLinha(EstiloLinha estiloLinha) {
            this.estilo.setEstiloLinha(estiloLinha);
            return this;
        }

        /**
         * Constrói o objeto
         *
         * @return estilo com os parâmetros desejados
         */
        public Estilo build() {
            return this.estilo;
        }
    }
}
