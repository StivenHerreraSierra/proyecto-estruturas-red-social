package co.edu.estructuras.red;

import co.edu.estructuras.red.controller.RedSocialController;
import co.edu.estructuras.red.estructuras.exception.GrafoException;
import co.edu.estructuras.red.model.Vendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import co.edu.estructuras.red.model.Red;
import java.io.IOException;

public class Principal extends Application {
    private BorderPane root;
    public static void main(String[] args) {
        launch(args);
    }

    private Red redSocial;

    @Override
    public void start(Stage primaryStage) {
        this.redSocial = new Red();

        loadRoot();
        loadRedSocialView();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Red Social");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRedSocialView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RedSocialView.fxml"));
            AnchorPane view = loader.load();
            RedSocialController controller = loader.getController();
            controller.setPrincipal(this);
            root.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vendedor registrarVendedor(String nombre) {
        Vendedor vendedor = null;
        try {
            vendedor = redSocial.registrarVendedor(nombre);
        } catch (GrafoException e) {
            System.err.println("Error registrando el vendedor: " + e.getMessage());
        }

        return  vendedor;
    }
}
