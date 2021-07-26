package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Vendedor;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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

    public void VendedorTabInitializer(Vendedor usuario, VendedorTabListener listener) {
        setUsuario(usuario);
        listaTodos = FXCollections.observableArrayList();
        listaSugeridos = FXCollections.observableArrayList();
        todosListView.setItems(listaTodos);
        sugeridosListView.setItems(listaSugeridos);

        actualizarButton.setOnAction(event -> {
            listaTodos.setAll(listener.actualizarListaTodos(usuario).asList());
            listaSugeridos.setAll(listener.actualizarListaSugeridos(usuario).asList());
        });

        todosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                listener.agregarContacto(usuario, newValue);
        });

        sugeridosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                listener.agregarContacto(usuario, newValue);
        });

        listaContactos = FXCollections.observableArrayList();
        contactosListView.setItems(listaContactos);

        actualizarContactosButton.setOnAction(event -> {
            listaContactos.setAll(listener.actualizarListaContactos(usuario).asList());
        });
    }

    private void setUsuario(Vendedor vendedor) {
        this.usuario = vendedor;
        this.usuarioLabel.setText("Usuario: " + vendedor.getNombreVendedor());
    }

}
