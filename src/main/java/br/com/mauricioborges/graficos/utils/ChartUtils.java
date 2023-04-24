package br.com.mauricioborges.graficos.utils;

import static br.com.mauricioborges.graficos.utils.ChartUtils.EstiloLinha.CONTINUA;
import static br.com.mauricioborges.graficos.utils.ChartUtils.EstiloLinha.PONTILHADA;
import static br.com.mauricioborges.graficos.utils.ColorUtils.toWeb;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

/**
 * Utilitários para gráficos
 *
 * @author Mauricio Borges
 * @since 10/2022
 */
public abstract class ChartUtils {

    /* 
    -------------------------------------------------------------
    ------------------------ LINE STYLES ------------------------
    -------------------------------------------------------------
     */
    private static void setStyleLine(XYChart.Series<Number, Number> seriesOfData, String style) {
        if (seriesOfData.getNode() == null) {
            return;
        }
        String style0 = seriesOfData.getNode().lookup(".chart-series-line").getStyle();
        seriesOfData.getNode().lookup(".chart-series-line").setStyle(style0 + style);
    }

    /**
     * Definir a largura da linha
     *
     * @param seriesOfData conjunto dos dados
     * @param width largura
     */
    public static void setLarguraLinha(XYChart.Series<Number, Number> seriesOfData, double width) {
        setStyleLine(seriesOfData, "-fx-stroke-width: " + width + "px;");
    }

    /**
     * Definir a cor da linha
     *
     * @param seriesOfData conjunto dos dados
     * @param cor cor
     */
    public static void setCorLinha(XYChart.Series<Number, Number> seriesOfData, Color cor) {
        setStyleLine(seriesOfData, "-fx-stroke: " + toWeb(cor) + ";");
    }

    /**
     * Definir o estilo da linha
     *
     * @param seriesOfData conjunto dos dados
     * @param lineStyle estilo da linha
     */
    public static void setEstiloLinha(XYChart.Series<Number, Number> seriesOfData, EstiloLinha lineStyle) {
        if (lineStyle == CONTINUA) {
            return;
        }
        if (seriesOfData.getData().size() > 500) {
            for (int n = 0;; n++) {
                int inicio = (lineStyle == PONTILHADA ? 25 : 70) * n;
                int fim = (lineStyle == PONTILHADA ? 18 : 35) + inicio;
                if (fim >= seriesOfData.getData().size()) {
                    break;
                }
                for (int i = inicio; i <= fim; i++) {
                    seriesOfData.getData().get(i).setNode(null);
                }
            }
            return;
        }
        switch (lineStyle) {
            case PONTILHADA:
                setStyleLine(seriesOfData, "-fx-stroke-dash-array: 0.5px 6px;");
                break;
            case TRACEJADA:
                setStyleLine(seriesOfData, "-fx-stroke-dash-array: 8px 12px;");
                break;
        }
    }

    /* 
    -------------------------------------------------------------
    -------------------- LINE SYMBOL STYLES ---------------------
    -------------------------------------------------------------
     */
    private static void setStyleLineSymbol(XYChart.Series<Number, Number> seriesOfData, String style) {
        seriesOfData.getData().forEach(data -> {
            if (data.getNode() == null) {
                return;
            }
            String style0 = data.getNode().lookup(".chart-line-symbol").getStyle();
            data.getNode().lookup(".chart-line-symbol").setStyle(style0 + style);
        });
    }

    /**
     * Definir o tamanho do marcador
     *
     * @param seriesOfData conjunto dos dados
     * @param width tamanho
     */
    public static void setTamanhoMarcador(XYChart.Series<Number, Number> seriesOfData, double width) {
        setStyleLineSymbol(seriesOfData, "-fx-padding: " + width + "px;");
    }

    /**
     * Definir a cor do marcador
     *
     * @param seriesOfData conjunto dos dados
     * @param cor cor
     */
    public static void setCorMarcador(XYChart.Series<Number, Number> seriesOfData, Color cor) {
        setStyleLineSymbol(seriesOfData, "-fx-background-color: " + toWeb(cor) + ", white;");
    }

    /* 
    -------------------------------------------------------------
    ----------------------- SERIES STYLES -----------------------
    -------------------------------------------------------------
     */
    /**
     * Define a cor da linha, dos marcadores e do símbolo da legenda
     *
     * @param seriesOfData conjunto dos dados
     * @param cor cor
     */
    public static void setCor(XYChart.Series<Number, Number> seriesOfData, Color cor) {
        XYChart grafico = seriesOfData.getChart();
        if (grafico == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(grafico.getStyle());
        int index = grafico.getData().indexOf(seriesOfData) + 1;
        sb.append("CHART_COLOR_").append(index).append(": ").append(toWeb(cor)).append(";");
        grafico.setStyle(sb.toString());
    }

    /**
     * Estilos para a linha do gráfico
     */
    public static enum EstiloLinha {
        /**
         * Linha pontilhada
         */
        PONTILHADA,
        /**
         * Linha tracejada
         */
        TRACEJADA,
        /**
         * Linha contínua
         */
        CONTINUA
    }
}
