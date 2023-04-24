package br.com.mauricioborges.graficos;

import br.com.mauricioborges.graficos.math.Funcao;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Scanner;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Derivadas derivadas = new Derivadas();

        System.out.println("Insira a funcao a derivar");

        String funcao = JOptionPane.showInputDialog("Insira a derivada");

        derivadas.setFuncionADerivar(funcao);
        derivadas.derivar();

        String result = derivadas.getFuncionDerivada();

        JOptionPane.showMessageDialog(null, "Valor da derivada: " +  result);

        Funcao f = x -> Double.parseDouble(derivadas.getFuncionDerivada());
        Grafico g = new Grafico();
        g.plotFuncao(f, 0, 2*Math.PI, "Seno");
        g.show(primaryStage);
    }
}
