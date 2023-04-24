package br.com.mauricioborges.graficos.math;

/**
 * Interface para declaração de funções através de expressões lambda
 *
 * @author Mauricio Borges
 * @since 2018
 */
@FunctionalInterface
public interface Funcao {

    /**
     * Aplicar o valor do parâmetro na função matemática
     *
     * @param x parâmetro
     * @return resultado da expressão matemática
     */
    public abstract double apply(double x);
}
