package br.com.mauricioborges.graficos;

import br.com.mauricioborges.graficos.math.Funcao;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Funcao f = x -> -4 / Math.pow(x - 4, 2);
        Grafico g = new Grafico();
        g.plotFuncao(f, 0, 2*Math.PI, "Assintota Vertical n = 4");
        g.show(primaryStage);
    }

}
