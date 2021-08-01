package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.model.Publicacion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TopMeGustaPanelController {
    @FXML
    private TableView<Publicacion> publicacionesTableView;
    @FXML
    private TableColumn<Publicacion, String> nombreProductoColumn;
    @FXML
    private TableColumn<Publicacion, String> categoriaProductoColumn;
    @FXML
    private TableColumn<Publicacion, Double> precioProductoColumn;
    @FXML
    private TableColumn<Publicacion, Integer> meGustaColumn;
    private ObservableList<Publicacion> publicacionesLista;

    private TopMeGustaPanelListener listener;

    public void setListener(TopMeGustaPanelListener listener) {
        this.listener = listener;

        cargarTablaPublicaciones();
    }

    private void cargarTablaPublicaciones() {
        publicacionesLista = FXCollections.observableArrayList();
        actualizarLista();

        nombreProductoColumn.setCellValueFactory(publicacion -> new ReadOnlyObjectWrapper<>(publicacion.getValue().getProductoDePublicacion().getNombreProducto()));
        categoriaProductoColumn.setCellValueFactory(publicacion -> new ReadOnlyObjectWrapper<>(publicacion.getValue().getProductoDePublicacion().getCategoriaProducto()));
        precioProductoColumn.setCellValueFactory(publicacion -> new ReadOnlyObjectWrapper<>(publicacion.getValue().getProductoDePublicacion().getPrecioProducto()));
        meGustaColumn.setCellValueFactory(publicacion -> new ReadOnlyObjectWrapper<>(publicacion.getValue().getCantidadMeGusta()));

        publicacionesTableView.setItems(publicacionesLista);
    }

    @FXML
    public void actualizarLista() {
        publicacionesLista.setAll(listener.getTopPublicaciones());
    }
}
