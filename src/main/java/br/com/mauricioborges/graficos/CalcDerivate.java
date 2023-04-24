package br.com.mauricioborges.graficos;

import javax.swing.*;

public class CalcDerivate {
    public static void main(String[] args) {
        String getFunction = JOptionPane.showInputDialog("Insira a sua derivada");
        Derivadas derivadas = new Derivadas();

        derivadas.setFuncionADerivar(getFunction);
        derivadas.derivar();

        JOptionPane.showMessageDialog(null, derivadas.getFuncionDerivada() + " -4 / (x - 4)^2");


        double functionResult = Double.parseDouble(derivadas.getFuncionDerivada());
    }
}
