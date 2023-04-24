package br.com.mauricioborges.graficos.utils;

import javafx.scene.paint.Color;

/**
 * Utilitários para cores do JavaFX
 *
 * @author Mauricio Borges
 * @since 05/2020
 */
public abstract class ColorUtils {

    /**
     * Set opacity of color
     *
     * @param cor color object
     * @param opacity opacity
     * @return color with opacity setting
     */
    public static Color setOpacity(Color cor, double opacity) {
        double r = cor.getRed();
        double g = cor.getGreen();
        double b = cor.getBlue();
        return Color.color(r, g, b, opacity);
    }

    /**
     * Color to string web format with opacity 1
     *
     * @param cor color object
     * @return string representing the color in string web format
     */
    public static String toWeb(Color cor) {
        return "#" + cor.toString().substring(2);
    }

    /**
     * Color to string web format with opacity setting
     *
     * @param cor color object
     * @param opacity opacity
     * @return string representing the color in string web format
     */
    public static String toWeb(Color cor, double opacity) {
        return toWeb(setOpacity(cor, opacity));
    }

    /**
     * Color in FX format to AWT format
     *
     * @param cor color
     * @return color in AWT format
     */
    public static java.awt.Color toAWTColor(Color cor) {
        return new java.awt.Color((float) cor.getRed(),
                (float) cor.getGreen(),
                (float) cor.getBlue(),
                (float) cor.getOpacity());
    }

    /**
     * Color in AWT format to FX format
     *
     * @param cor color
     * @return color in FX format
     */
    public static Color toFXColor(java.awt.Color cor) {
        return new Color(cor.getRed() / 255d,
                cor.getGreen() / 255d,
                cor.getBlue() / 255d,
                cor.getAlpha() / 255d);
    }
}
