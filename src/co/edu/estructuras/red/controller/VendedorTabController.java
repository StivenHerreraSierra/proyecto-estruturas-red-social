package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class VendedorTabController {
    private Vendedor usuario;
    @FXML
    private Label usuarioLabel;
    @FXML
    private ListView<Vendedor> todosListView;
    private ObservableList<Vendedor> listaTodos;
    @FXML
    private ListView<Vendedor> sugeridosListView;
    private ObservableList<Vendedor> listaSugeridos;
    @FXML
    private ListView<Vendedor> contactosListView;
    private ObservableList<Vendedor> listaContactos;
    @FXML
    private Button actualizarButton;
    @FXML
    private Button actualizarContactosButton;
    @FXML
    private TitledPane panelPublicar;
    @FXML
    private VBox panelPublicaciones;
    private PublicacionListener publicacionListener;
    @FXML
    private TextField buscadorVendedorTFD;
    @FXML
    private Button buscarVendedorButton;

    public void VendedorTabInitializer(Vendedor usuario, VendedorTabListener listenerVendedor,
                                       PublicarListener listenerPublicar, PublicacionListener listenerPublicacion) {
        setUsuario(usuario);
        listaTodos = FXCollections.observableArrayList();
        listaSugeridos = FXCollections.observableArrayList();
        todosListView.setItems(listaTodos);
        sugeridosListView.setItems(listaSugeridos);

        actualizarButton.setOnAction(event -> {
            listaTodos.setAll(listenerVendedor.actualizarListaTodos(usuario).asList());
            listaSugeridos.setAll(listenerVendedor.actualizarListaSugeridos(usuario).asList());
            cargarPublicaciones(listenerVendedor.actualizarMuro(usuario));
        });

        todosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                listenerVendedor.agregarContacto(usuario, newValue);
        });

        sugeridosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                listenerVendedor.agregarContacto(usuario, newValue);
        });

        listaContactos = FXCollections.observableArrayList();
        contactosListView.setItems(listaContactos);

        actualizarContactosButton.setOnAction(event -> {
            listaContactos.setAll(listenerVendedor.actualizarListaContactos(usuario).asList());
        });

        buscarVendedorButton.setOnAction(event -> {
            listenerVendedor.agregarContactoBuscado(usuario, buscadorVendedorTFD.getText());
            buscadorVendedorTFD.clear();
        });

        cargarPublicarView(listenerPublicar);

        this.publicacionListener = listenerPublicacion;
        cargarPublicaciones(listenerVendedor.actualizarMuro(usuario));
    }

    private void setUsuario(Vendedor vendedor) {
        this.usuario = vendedor;
        this.usuarioLabel.setText("Usuario: " + vendedor.getNombreVendedor());
    }

    private void cargarPublicarView(PublicarListener listener) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/PublicarView.fxml"));
            AnchorPane view = loader.load();
            PublicarController controller = loader.getController();
            controller.setListener(listener, usuario);
            panelPublicar.setContent(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarPublicaciones(ArbolBinario<Publicacion> publicaciones) {
        ArrayList<Publicacion> publicacionesOrdenadas = publicaciones.getListaInorden();

        panelPublicaciones.getChildren().clear();
        for(Publicacion publicacion : publicacionesOrdenadas)
            cargarPublicacion(publicacionListener, publicacion);
    }

    private void cargarPublicacion(PublicacionListener listener, Publicacion publicacion) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/PublicacionView.fxml"));
            AnchorPane view = loader.load();
            PublicacionController controller = loader.getController();
            controller.publicacionInitializer(listener, publicacion);
            panelPublicaciones.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
