/**
 * Módulo de Gráficos
 */
module br.com.mauricioborges.graficos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires djep;
    requires jep;
    requires java.logging;

    opens br.com.mauricioborges.graficos.gui to javafx.fxml;
  
    exports br.com.mauricioborges.graficos;
    exports br.com.mauricioborges.graficos.math;
    exports br.com.mauricioborges.graficos.utils;
}
