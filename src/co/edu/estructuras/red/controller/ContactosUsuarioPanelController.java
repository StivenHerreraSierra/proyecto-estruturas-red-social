package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Vendedor;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactosUsuarioPanelController {
    @FXML
    private ComboBox<Vendedor> vendedoresComboBoxContacto;
    private ObservableList<Vendedor> vendedoresListaContacto;
    @FXML
    private TableView<Vendedor> contactosVendedorTableView;
    @FXML
    private TableColumn<Vendedor, String> nombreContactoColumn;
    @FXML
    private TableColumn<Vendedor, Integer> cantidadMensajesColumn;
    private ObservableList<Vendedor> contactosLista;
    @FXML
    private Label cantidadContactosVendedorLabel;

    private ContactosUsuarioPanelListener listener;

    public void setListener(ContactosUsuarioPanelListener listener) {
        this.listener = listener;

        cargarListaVendedoresContacto();
        cargarTablaContactos();
    }

    private void actualizarListaVendedores(ObservableList<Vendedor> lista) {
        Grafo<Vendedor> vendedores = listener.getListaVendedores();
        lista.setAll(vendedores.asList());
    }

    private void cargarListaVendedoresContacto() {
        vendedoresListaContacto = FXCollections.observableArrayList();
        actualizarListaVendedores(vendedoresListaContacto);
        vendedoresComboBoxContacto.setItems(vendedoresListaContacto);

        vendedoresComboBoxContacto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarContactosLista(newValue);
        });
    }

    private void actualizarContactosLista(Vendedor vendedorSeleccionado) {
        if(vendedorSeleccionado != null && vendedoresListaContacto.contains(vendedorSeleccionado)) {
            Grafo<Vendedor> vendedores = listener.getListaContactos(vendedorSeleccionado);
            contactosLista.clear();
            if (vendedores.size() > 0) {
                for (Vendedor vendedor : vendedores) {
                    contactosLista.add(vendedor);
                }
            }
            cantidadContactosVendedorLabel.setText(String.valueOf(contactosLista.size()));
        }
    }

    private void cargarTablaContactos() {
        contactosLista = FXCollections.observableArrayList();
        nombreContactoColumn.setCellValueFactory(contacto -> new ReadOnlyObjectWrapper<>(contacto.getValue().getNombreVendedor()));
        cantidadMensajesColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(0));

        contactosVendedorTableView.setItems(contactosLista);
    }

    @FXML
    private void actualizarContactos() {
        Vendedor vendedorSeleccionado = vendedoresComboBoxContacto.getValue();
        actualizarListaVendedores(vendedoresListaContacto);
        if(vendedorSeleccionado != null) {
            vendedoresComboBoxContacto.getSelectionModel().select(vendedorSeleccionado);
            actualizarContactosLista(vendedorSeleccionado);
        }
    }
}
