package co.edu.estructuras.red;

import co.edu.estructuras.red.controller.PanelComentarioController;
import co.edu.estructuras.red.controller.RedSocialController;
import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.estructuras.exception.GrafoException;
import co.edu.estructuras.red.estructuras.exception.NodoException;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import co.edu.estructuras.red.model.exception.PublicacionException;
import co.edu.estructuras.red.model.exception.RedSocialException;
import co.edu.estructuras.red.model.exception.VendedorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import co.edu.estructuras.red.model.Red;
import java.io.IOException;
import java.util.Optional;

public class Principal extends Application {
    private BorderPane root;
    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    private Red redSocial;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
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
            ButtonType resultado = mostrarMensajeConfirmacion("", "Agregar contacto",
                    "¿Quieres agregar a " + nuevoContacto.getNombreVendedor() + "?",
                    "Presiona una de las opciones", null);
            if(resultado == ButtonType.OK)
                redSocial.agregarContacto(usuario, nuevoContacto);
        } catch (GrafoException | RedSocialException | NodoException e) {
            System.err.println(e.getMessage());
        }
    }

    public void agregarContactoBuscado(Vendedor usuario, String nombre) {
        try {
            Vendedor contacto = redSocial.getVendedor(nombre);

            agregarContacto(usuario, contacto);
        } catch (RedSocialException e) {
            System.err.println(e.getMessage());
        }
    }

    public ButtonType mostrarMensajeConfirmacion(String mensaje, String titulo, String cabecera, String contenido, Stage escenarioPrincipal )
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(escenarioPrincipal);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);

        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.get();
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

    public void registrarPublicacion(Vendedor usuario, String nombre, String categoria) {
        try {
            redSocial.registrarPublicacion(usuario, nombre, categoria);
            usuario.getPublicaciones().inorden();
        } catch (RedSocialException | VendedorException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArbolBinario<Publicacion> getPublicacionesVendedor(Vendedor vendedor) {
        ArbolBinario<Publicacion> publicaciones = new ArbolBinario<>();
        try {
            publicaciones = redSocial.getPublicacionesVendedor(vendedor);
        } catch (GrafoException | NodoException e) {
            System.err.println(e.getMessage());
        }
        return publicaciones;
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

    public boolean darMeGusta(Vendedor usuario, Publicacion publicacion) {
         return redSocial.darMeGusta(usuario, publicacion);
    }

    public boolean meGusta(Publicacion publicacion, Vendedor usuario) {
        return redSocial.meGusta(publicacion, usuario);
    }

    public void mostrarVentanaComentarios(Publicacion publicacion, Vendedor usuario) {
        Stage secundaryStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        cargarVentanaComentarios(root, publicacion, usuario);

        secundaryStage.setTitle("Comentarios");
        secundaryStage.setScene(scene);
        secundaryStage.initModality(Modality.APPLICATION_MODAL);
        secundaryStage.showAndWait();
    }

    private void cargarVentanaComentarios(BorderPane root, Publicacion publicacion, Vendedor usuario) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/PanelComentariosView.fxml"));
            AnchorPane view = loader.load();
            PanelComentarioController controller = loader.getController();
            controller.setPrincipal(this);
            controller.panelComentarioInitializer(publicacion, usuario);
            root.setCenter(view);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean publicarComentario(String comentario, Publicacion publicacion, Vendedor usuario) {
        try {
            redSocial.comentar(comentario, publicacion, usuario);
            return true;
        } catch (PublicacionException | RedSocialException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
