package br.com.mauricioborges.graficos.utils;

import java.net.URL;

/**
 * Utilitários para JavaFX
 *
 * @author Mauricio Borges
 * @since 05/2020
 */
public abstract class FXUtils {

    /**
     * Encontra o resource solicitado
     *
     * @param parcialURL URL parcial, após o pacote raiz
     * @return objeto URL contendo a localização do resource
     */
    public static URL findResource(String parcialURL) {
        return FXUtils.class.getResource(gerarURL(parcialURL));
    }

    /**
     * Gerar URL de algum resource
     *
     * @param parcialURL URL parcial, após o pacote raiz
     * @return
     */
    private static String gerarURL(String parcialURL) {
        return "/br/com/mauricioborges/graficos/" + parcialURL;
    }

}
