package br.com.mauricioborges.graficos;

import br.com.mauricioborges.graficos.math.Funcao;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
        dialog.setTitle("Number Input");
        dialog.setHeaderText("Please enter a number:");
        dialog.setContentText("Number:");

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Resultado");
        alert.setContentText(value);
        alert.showAndWait();


        Funcao f = x -> -4 / Math.pow((x-4), 2);
        Grafico g = new Grafico();
        g.plotFuncao(f, 0, 2*Math.PI, "Assintota Vertical n = 4");
        g.show(primaryStage);
    }

}

