package co.edu.estructuras.red;

import co.edu.estructuras.red.controller.ChatController;
import co.edu.estructuras.red.controller.PanelComentarioController;
import co.edu.estructuras.red.controller.RedSocialController;
import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.estructuras.exception.GrafoException;
import co.edu.estructuras.red.estructuras.exception.NodoException;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import co.edu.estructuras.red.model.exception.ChatException;
import co.edu.estructuras.red.model.exception.PublicacionException;
import co.edu.estructuras.red.model.exception.RedSocialException;
import co.edu.estructuras.red.model.exception.VendedorException;
import co.edu.estructuras.red.persistencia.Persistencia;
import co.edu.estructuras.red.persistencia.exception.PersistenciaException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import co.edu.estructuras.red.model.Red;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Stiven Herrera Sierra
 */
public class Principal extends Application {
    private BorderPane root;
    private Stage primaryStage;
    private Stage secondaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    private Red redSocial;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.redSocial = new Red();
        this.secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);

        loadRoot();
        loadRedSocialView();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Principal.class.getResource("view/FlatBee.css").toExternalForm());

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
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error cargando la GUI",
                    e.getMessage(), primaryStage);
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
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error cargando la GUI",
                    e.getMessage(), primaryStage);
        }
    }

    public Vendedor registrarVendedor(String nombre) {
        Vendedor vendedor = null;
        try {
            vendedor = redSocial.registrarVendedor(nombre);
        } catch (GrafoException | RedSocialException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error en registro",
                    e.getMessage(), primaryStage);
        }

        return  vendedor;
    }

    public Grafo<Vendedor> getListaTodosVendedoresAgregar(Vendedor vendedor) {
        Grafo<Vendedor> vendedoresGrafo = null;
        try {
            vendedoresGrafo = redSocial.getListaVendedoresAgregar(vendedor);
        } catch (GrafoException | NodoException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion",
                    "Error obteniendo lista de vendedores para agregar", e.getMessage(), primaryStage);
        }
        return vendedoresGrafo;
    }

    public Grafo<Vendedor> getListaVendedoresSugeridosAgregar(Vendedor vendedor) {
        Grafo<Vendedor> vendedoresGrafo = null;
        try {
            vendedoresGrafo = redSocial.getListaSugeridosAgregar(vendedor);
        } catch (GrafoException | NodoException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion",
                    "Error obteniendo lista de vendedores para agregar", e.getMessage(), primaryStage);
        }
        return vendedoresGrafo;
    }

    public void agregarContacto(Vendedor usuario, Vendedor nuevoContacto) {
        try {
            ButtonType resultado = mostrarMensajeConfirmacion("Agregar contacto",
                    "¿Quieres agregar a " + nuevoContacto.getNombreVendedor() + "?",
                    "Presiona una de las opciones", null);
            if(resultado == ButtonType.OK)
                redSocial.agregarContacto(usuario, nuevoContacto);
        } catch (GrafoException | RedSocialException | NodoException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion",
                    "Error agregando contacto", e.getMessage(), primaryStage);
        }
    }

    public void agregarContactoBuscado(Vendedor usuario, String nombre) {
        try {
            Vendedor contacto = redSocial.getVendedor(nombre);

            agregarContacto(usuario, contacto);
        } catch (RedSocialException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion",
                    "Error agregando contacto", e.getMessage(), primaryStage);
        }
    }

    public ButtonType mostrarMensajeConfirmacion(String titulo, String cabecera, String contenido, Stage escenarioPrincipal )
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
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion",
                    "Error obteniendo contactos de " + vendedor.getNombreVendedor(), e.getMessage(), primaryStage);
        }
        return contactosGrafo;
    }

    public void registrarPublicacion(Vendedor usuario, String nombre, String categoria, String precio) {
        try {
            double precioDouble = Double.parseDouble(precio);

            redSocial.registrarPublicacion(usuario, nombre, categoria, precioDouble);
            usuario.getPublicaciones().inorden();
        } catch (RedSocialException | VendedorException | PublicacionException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion",
                    "Error agregando publicacion", e.getMessage(), primaryStage);
        } catch (NumberFormatException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error agregando publicacion",
                    "Error, el precio no fue ingresado o contiene caracteres invalidos.", primaryStage);
        }
    }

    public ArbolBinario<Publicacion> getPublicacionesVendedor(Vendedor vendedor) {
        ArbolBinario<Publicacion> publicaciones = new ArbolBinario<>();
        try {
            publicaciones = redSocial.getPublicacionesVendedor(vendedor);
        } catch (GrafoException | NodoException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error obteniendo publicaciones",
                    e.getMessage(), primaryStage);
        }
        return publicaciones;
    }

    public void mostrarMensaje(Alert.AlertType miA, String titulo, String cabecera, String contenido, Stage escenarioPrincipal )
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

    //====================Cargando GUI para comentarios====================
    public void mostrarVentanaComentarios(Publicacion publicacion, Vendedor usuario) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Principal.class.getResource("view/FlatBee.css").toExternalForm());

        cargarVentanaComentarios(root, publicacion, usuario);

        secondaryStage.setTitle("Comentarios");
        secondaryStage.setScene(scene);
        secondaryStage.showAndWait();
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
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error cargando GUI",
                    e.getMessage(), primaryStage);
        }
    }

    public boolean publicarComentario(String comentario, Publicacion publicacion, Vendedor usuario) {
        try {
            redSocial.comentar(comentario, publicacion, usuario);
            return true;
        } catch (PublicacionException | RedSocialException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error comentando publicacion",
                    e.getMessage(), secondaryStage);
        }
        return false;
    }

    public int contarProductosFecha(LocalDate desde, LocalDate hasta) {
        int cantidad = 0;
        try {
            cantidad = this.redSocial.contarProductosFecha(desde, hasta);
        } catch (RedSocialException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error contando publicaciones",
                    e.getMessage(), primaryStage);
        }
        return cantidad;
    }

    public Grafo<Vendedor> getVendedores() {
        return redSocial.getVendedores();
    }

    public List<Publicacion> getTopPublicaciones() {
        return redSocial.getTopPublicaciones();
    }

    //====================Cargando GUI para chat====================
    public void iniciarChat(Vendedor usuario1, Vendedor usuario2) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Principal.class.getResource("view/FlatBee.css").toExternalForm());

        cargarVentanaChat(root, usuario1, usuario2);

        secondaryStage.setTitle("Chat");
        secondaryStage.setScene(scene);
        secondaryStage.showAndWait();
    }

    private void cargarVentanaChat(BorderPane root, Vendedor usuario1, Vendedor usuario2) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/ChatView.fxml"));
            AnchorPane view = loader.load();
            ChatController controller = loader.getController();
            controller.setPrincipal(this);
            controller.setUsuarios(usuario1, usuario2);
            root.setCenter(view);
        } catch (IOException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error cargando GUI",
                    e.getMessage(), primaryStage);
        }
    }

    public boolean enviarMensaje(String mensaje, Vendedor usuario1, Vendedor usuario2) {
        boolean enviado = false;
        try {
            this.redSocial.agregarMensajeChat(mensaje, usuario1, usuario2);
            enviado = true;
        } catch (GrafoException | RedSocialException | ChatException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error enviando mensaje",
                    e.getMessage(), primaryStage);
        }
        return enviado;
    }

    public String getMensajesChat(Vendedor usuario1, Vendedor usuario2) {
        String mensajes = "";
        try {
            mensajes = this.redSocial.getMensajesChatString(usuario1, usuario2);
        } catch (RedSocialException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error obteniendo mensajes",
                    e.getMessage(), primaryStage);
        }
        return mensajes;
    }

    public void cantidadMensajesIntercambiados(Vendedor usuario1, Vendedor usuario2) {
        try {
            int cantidad = this.redSocial.getCantidadMensajesIntercambiados(usuario1, usuario2);
            mostrarMensaje(Alert.AlertType.INFORMATION, "Mensajes intercambiados", "Cantidad de mensajes de chat enviados",
                    usuario1.getNombreVendedor() + " y " + usuario2.getNombreVendedor() + " se han enviado " +
                            cantidad + " mensaje(s).", primaryStage);
        } catch (RedSocialException | GrafoException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error obteniendo cantidad de mensajes",
                    e.getMessage(), primaryStage);
        }
    }

    public void guardarSesion() {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Guardar sesión");
            File ubicacion = fc.showSaveDialog(primaryStage);
            Persistencia.writeObject(redSocial, ubicacion);
        } catch (PersistenciaException | IOException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error guardando sesion",
                    e.getMessage(), primaryStage);
            e.printStackTrace();
        }
    }

    public boolean cargarSesion() {
        boolean cargado = true;
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Guardar sesión");
            File ubicacion = fc.showOpenDialog(primaryStage);
            redSocial = (Red) Persistencia.readObject(ubicacion);
        } catch (PersistenciaException | IOException | ClassNotFoundException e) {
            cargado = false;
            mostrarMensaje(Alert.AlertType.ERROR, "Error en la aplicacion", "Error cargando sesion",
                    e.getMessage(), primaryStage);
            e.printStackTrace();
        }
        return cargado;
    }
}
