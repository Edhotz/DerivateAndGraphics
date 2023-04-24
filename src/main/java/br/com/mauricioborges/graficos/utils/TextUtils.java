package br.com.mauricioborges.graficos.utils;

import java.text.DecimalFormat;

/**
 * Utilitários para texto
 *
 * @author Mauricio Borges
 * @since 10/2022
 */
public abstract class TextUtils {

    /**
     * Sobrescrever números
     *
     * @param number número
     * @return string contendo o número sobrescrito
     */
    public static String sup(double number) {
        DecimalFormat df = new DecimalFormat();
        return sup(df.format(number));
    }

    /**
     * Sobrescrever textos
     *
     * @param texto texto
     * @return string contendo o texto sobrescrito
     */
    public static String sup(String texto) {
        char[] array = texto.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            sb.append(supChar(c));
        }
        return sb.toString();
    }

    private static String supChar(char c) {
        switch (c) {
            case '0':
                return "⁰";
            case '1':
                return "¹";
            case '2':
                return "²";
            case '3':
                return "³";
            case '4':
                return "⁴";
            case '5':
                return "⁵";
            case '6':
                return "⁶";
            case '7':
                return "⁷";
            case '8':
                return "⁸";
            case '9':
                return "⁹";
            case '-':
                return "⁻";
            case ',':
                return "ʼ";
            case '.':
                return "·";
            case 'x':
                return "ˣ";
            default:
                return "";
        }
    }

}
