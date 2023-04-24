package br.com.mauricioborges.graficos;

import br.com.mauricioborges.graficos.gui.CenaGraficoController;
import br.com.mauricioborges.graficos.math.Funcao;
import static br.com.mauricioborges.graficos.utils.FXUtils.findResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Representa um gráfico. Exemplo de uso:
 *
 * <pre><code>
 * Funcao f = x -> Math.sin(x);
 * Grafico g = new Grafico();
 * g.plotFuncao(f, 0, 2*Math.PI, "Seno");
 * g.show(stage);
 * </code></pre>
 *
 * @author Mauricio Borges
 * @since 2018
 */
public final class Grafico extends Application {

    private CenaGraficoController controle;
    private String tituloJanela = "Gráficos JavaFX";
    private String tituloGrafico = null;
    private String tituloEixoX = null;
    private String tituloEixoY = null;
    private boolean jaAbriuGrafico = false;
    // Funcoes
    private final List<Funcao> funcoes = new ArrayList<>();
    private final List<Double> inicio = new ArrayList<>();
    private final List<Double> fim = new ArrayList<>();
    private final List<String> tituloFuncoes = new ArrayList<>();
    private final List<Estilo> estiloFuncoes = new ArrayList<>();
    // Pontos
    private final List<Double[]> x = new ArrayList<>();
    private final List<Double[]> y = new ArrayList<>();
    private final List<String> tituloPontos = new ArrayList<>();
    private final List<Estilo> estiloPontos = new ArrayList<>();
    private final List<LinhaDeTendencia[]> linhasDeTendencia = new ArrayList<>();

    /**
     * Definir o título da janela
     *
     * @param tituloJanela título da janela
     */
    public void setTituloJanela(String tituloJanela) {
        this.tituloJanela = tituloJanela;
    }

    /**
     * Definir o título do gráfico
     *
     * @param tituloGrafico título do gráfico
     */
    public void setTituloGrafico(String tituloGrafico) {
        this.tituloGrafico = tituloGrafico;
    }

    /**
     * Definir o título do eixo X
     *
     * @param tituloEixoX título do eixo X
     */
    public void setTituloEixoX(String tituloEixoX) {
        this.tituloEixoX = tituloEixoX;
    }

    /**
     * Definir o título do eixo Y
     *
     * @param tituloEixoY título do eixo Y
     */
    public void setTituloEixoY(String tituloEixoY) {
        this.tituloEixoY = tituloEixoY;
    }

    /**
     * Plotar função em determinado intervalo
     *
     * @param funcao função
     * @param inicio início do intervalo
     * @param fim fim do intervalo
     * @param titulo legenda do gráfico
     * @param estilo opções de estilo
     */
    public void plotFuncao(Funcao funcao, double inicio, double fim, String titulo, Estilo estilo) {
        this.funcoes.add(requireNonNull(funcao, "A função não pode ser nula."));
        this.inicio.add(inicio);
        this.fim.add(fim);
        this.tituloFuncoes.add(requireNonNull(titulo, "O título da função não pode ser nulo."));
        this.estiloFuncoes.add(requireNonNull(estilo, "O estilo da função não pode ser nulo."));

        if (controle != null) {
            controle.plotFuncao(funcao, inicio, fim, titulo, estilo);
        }
    }

    /**
     * Plotar função em determinado intervalo com o estilo padrão
     *
     * @param funcao função
     * @param inicio início do intervalo
     * @param fim fim do intervalo
     * @param titulo legenda do gráfico
     */
    public void plotFuncao(Funcao funcao, double inicio, double fim, String titulo) {
        this.plotFuncao(funcao, inicio, fim, titulo, Estilo.LINHA);
    }

    /**
     * Plotar um conjunto de pontos
     *
     * @param x array com os valores de X
     * @param y array com os valores de Y
     * @param titulo legenda do gráfico
     * @param estilo opções de estilo
     * @param linhasDeTendencia linhas de tendência
     */
    public void plotPontos(Double[] x, Double[] y, String titulo, Estilo estilo, LinhaDeTendencia... linhasDeTendencia) {
        if (x.length != y.length) {
            throw new UnsupportedOperationException("Os arrays de X e Y devem ter o mesmo tamanho.");
        }
        this.x.add(x);
        this.y.add(y);
        this.tituloPontos.add(requireNonNull(titulo, "O título dos pontos não pode ser nulo."));
        this.estiloPontos.add(requireNonNull(estilo, "O estilo dos pontos não pode ser nulo."));
        this.linhasDeTendencia.add(linhasDeTendencia);

        if (controle != null) {
            controle.plotPontos(x, y, titulo, estilo, linhasDeTendencia);
        }
    }

    /**
     * Plotar um conjunto de pontos com o estilo padrão (com linha e marcador) e
     * sem linha de tendência
     *
     * @param x array com os valores de X
     * @param y array com os valores de Y
     * @param titulo legenda do gráfico
     */
    public void plotPontos(Double[] x, Double[] y, String titulo) {
        this.plotPontos(x, y, titulo, Estilo.LINHA_E_MARCADOR);
    }

    /**
     * Plotar um conjunto de pontos sem linha de tendência
     *
     * @param x array com os valores de X
     * @param y array com os valores de Y
     * @param titulo legenda do gráfico
     * @param estilo opções de estilo
     */
    public void plotPontos(Double[] x, Double[] y, String titulo, Estilo estilo) {
        this.plotPontos(x, y, titulo, estilo, (LinhaDeTendencia) null);
    }

    /**
     * Plotar um conjunto de pontos com o estilo padrão (com linha e marcador)
     *
     * @param x array com os valores de X
     * @param y array com os valores de Y
     * @param titulo legenda do gráfico
     * @param linhasDeTendencia linhas de tendência
     */
    public void plotPontos(Double[] x, Double[] y, String titulo, LinhaDeTendencia... linhasDeTendencia) {
        this.plotPontos(x, y, titulo, Estilo.LINHA_E_MARCADOR, linhasDeTendencia);
    }

    private Parent carregarFXML() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(findResource("gui/CenaGrafico.fxml"));
        Parent root;
        try {
            root = loader.load();
            controle = loader.getController();
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao tentar carregar o FXML do gráfico.", ex);
        }

        // titulo do gráfico no controller
        controle.setTituloGrafico(tituloGrafico);
        // titulo dos eixos no controller
        controle.setTituloEixos(tituloEixoX, tituloEixoY);

        return root;
    }

    /**
     * Exibir o gráfico em um AnchorPane.
     *
     * @param painel painel onde o gráfico será exibido
     */
    public final void show(AnchorPane painel) {
        if (jaAbriuGrafico) {
            return;
        }
        requireNonNull(painel, "O painel não pode ser nulo.");

        Parent p = carregarFXML();
        painel.getChildren().clear();
        painel.getChildren().add(p);

        AnchorPane.setTopAnchor(p, 0d);
        AnchorPane.setBottomAnchor(p, 0d);
        AnchorPane.setLeftAnchor(p, 0d);
        AnchorPane.setRightAnchor(p, 0d);
        show((Stage) null);
    }

    /**
     * Exibir o gráfico em uma janela.<br>
     * Mesmo que chamar o método <code>start(Stage janela)</code>
     *
     * @param janela janela onde o gráfico será exibido
     */
    public final void show(Stage janela) {
        if (jaAbriuGrafico) {
            return;
        }
        if (controle == null) {
            this.start(janela);
            return;
        }
        // plotando os gráficos
        for (int i = 0; i < funcoes.size(); i++) {
            controle.plotFuncao(funcoes.get(i), inicio.get(i), fim.get(i), tituloFuncoes.get(i), estiloFuncoes.get(i));
        }
        for (int i = 0; i < x.size(); i++) {
            controle.plotPontos(x.get(i), y.get(i), tituloPontos.get(i), estiloPontos.get(i), linhasDeTendencia.get(i));
        }
        jaAbriuGrafico = true;
    }

    /**
     * Exibir o gráfico em uma janela.<br>
     * Mesmo que chamar o método <code>show(Stage janela)</code>
     *
     * @param janela janela onde o gráfico será exibido
     */
    @Override
    public final void start(Stage janela) {
        if (jaAbriuGrafico) {
            return;
        }
        requireNonNull(janela, "A janela não pode ser nula.");

        // carregando e adicionando a cena na janela
        Scene scene = new Scene(carregarFXML());
        janela.setTitle(tituloJanela);
        janela.setScene(scene);

        // adicionando o icone da janela
        Image icone = new Image(findResource("gui/img/iconeJava.png").toString());
        janela.getIcons().add(icone);

        // configurando e exibindo a janela
        janela.setOnCloseRequest(event -> System.exit(0));
        janela.show();

        // plotando os gráficos
        this.show(janela);
    }

}
