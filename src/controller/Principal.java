package controller;

import estructuras.exception.GrafoException;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Red;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Red redSocial;

    @Override
    public void start(Stage primaryStage) {
        redSocial = new Red();

        try {
            redSocial.registrarVendedor("Stiven");
            redSocial.registrarVendedor("Herrera");
            redSocial.registrarVendedor("Sierra");

            redSocial.registrarVendedor("Stiven");
            System.out.println(redSocial.toString());
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
        }
    }
}
