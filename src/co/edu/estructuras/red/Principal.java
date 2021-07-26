package co.edu.estructuras.red;

import co.edu.estructuras.red.controller.RedSocialController;
import co.edu.estructuras.red.estructuras.Grafo;
import co.edu.estructuras.red.estructuras.exception.GrafoException;
import co.edu.estructuras.red.estructuras.exception.NodoException;
import co.edu.estructuras.red.model.Vendedor;
import co.edu.estructuras.red.model.exception.RedSocialException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        } catch (GrafoException | RedSocialException e) {
            System.err.println("Error registrando el vendedor: " + e.getMessage());
        }

        return  vendedor;
    }

    public Grafo<Vendedor> getListaTodosVendedoresAgregar(Vendedor vendedor) {
        Grafo<Vendedor> vendedoresGrafo = null;
        try {
            vendedoresGrafo = redSocial.getListaVendedoresAgregar(vendedor);
        } catch (GrafoException | NodoException e) {
            System.err.println("Error obteniendo la lista de vendedores disponibles para agregar: " + e.getMessage());
        }
        return vendedoresGrafo;
    }

    public Grafo<Vendedor> getListaVendedoresSugeridosAgregar(Vendedor vendedor) {
        Grafo<Vendedor> vendedoresGrafo = null;
        try {
            vendedoresGrafo = redSocial.getListaSugeridosAgregar(vendedor);
        } catch (GrafoException | NodoException e) {
            System.err.println("Error obteniendo la lista de vendedores disponibles para agregar: " + e.getMessage());
        }
        return vendedoresGrafo;
    }

    public void agregarContacto(Vendedor usuario, Vendedor nuevoContacto) {
        try {
            redSocial.agregarContacto(usuario, nuevoContacto);
        } catch (GrafoException | RedSocialException | NodoException e) {
            System.err.println(e.getMessage());
        }
    }

    public Grafo<Vendedor> getListaContactos(Vendedor vendedor) {
        Grafo<Vendedor> contactosGrafo = null;
        try {
            contactosGrafo = redSocial.getListaContactos(vendedor);
        } catch (GrafoException | NodoException e) {
            System.err.println("Error obteniendo la lista de contactos de " +
                    vendedor.getNombreVendedor() + ": " + e.getMessage());
        }
        return contactosGrafo;
    }

    public void publicarProducto(String nombre, String categoria) {
        redSocial.publicarProducto(nombre, categoria);
    }

    public void mostrarMensaje(String mensaje, Alert.AlertType miA, String titulo, String cabecera, String contenido, Stage escenarioPrincipal )
    {
        Alert alert = new Alert(miA);
        alert.initOwner(escenarioPrincipal);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
