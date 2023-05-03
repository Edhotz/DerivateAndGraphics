package br.com.mauricioborges.graficos;

import br.com.mauricioborges.graficos.math.Funcao;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Optional;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Calculo da derivada");
        dialog.setHeaderText("Insira a derivada de uma funcao:");
        dialog.setContentText("f(x): ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int number = Integer.parseInt(result.get());
                // do something with the number...
            } catch (NumberFormatException e) {
                // handle invalid input...
            }
        }

        String value = result.get();

        Derivadas derivadas = new Derivadas();
        derivadas.setFuncionADerivar(value);
        derivadas.derivar();

        String derivateResult = derivadas.getFuncionDerivada();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado da derivada");
        alert.setHeaderText(derivateResult);
        alert.setContentText("Resultado da derivada " + " -4 /(x-4)^2 ");
        alert.showAndWait();


        Funcao f = x -> -4 / Math.pow((x-4), 2);
        Grafico g = new Grafico();
        g.plotFuncao(f, 0, 2*Math.PI, "Assintota Vertical n = 4");
        g.show(primaryStage);
    }

}

