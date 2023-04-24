package br.com.mauricioborges.graficos.utils;

import static java.util.Objects.requireNonNull;

/**
 * Utilitários para arrays
 *
 * @author Mauricio Borges
 * @since 10/2022
 */
public abstract class ArrayUtils {

    /**
     * Cria uma cópia do array bidimensional
     *
     * @param array array a ser copiado
     * @return cópia do array
     */
    public static Double[][] copy(Double[][] array) {
        requireNonNull(array, "O array não pode ser nulo");
        requireNonNull(array[0], "O array deve ser bidimensional (array[0]!=null)");

        Double[][] newArray = new Double[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i].clone();
        }
        return newArray;
    }
}
