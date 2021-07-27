package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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

    public void VendedorTabInitializer(Vendedor usuario, VendedorTabListener listenerVendedor, PublicarListener listenerPublicar) {
        setUsuario(usuario);
        listaTodos = FXCollections.observableArrayList();
        listaSugeridos = FXCollections.observableArrayList();
        todosListView.setItems(listaTodos);
        sugeridosListView.setItems(listaSugeridos);

        actualizarButton.setOnAction(event -> {
            listaTodos.setAll(listenerVendedor.actualizarListaTodos(usuario).asList());
            listaSugeridos.setAll(listenerVendedor.actualizarListaSugeridos(usuario).asList());
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

        cargarPublicarView(listenerPublicar);
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
}
