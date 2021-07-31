package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Producto;
import co.edu.estructuras.red.model.Vendedor;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class PanelController {
    @FXML
    private TextField nombreTFD;
    @FXML
    private Button registrarButton;
    @FXML
    private DatePicker desdeDatePicker;
    @FXML
    private DatePicker hastaDatePicker;
    @FXML
    private Label cantidadProductosFechaLabel;
    @FXML
    private Button contarPublicacionesButton;
    @FXML
    private ComboBox<Vendedor> vendedoresComboBox;
    private ObservableList<Vendedor> vendedoresLista;
    @FXML
    private TableView<Producto> productosVendedorTableView;
    @FXML
    private TableColumn<Producto, String> nombreProductoColumn;
    @FXML
    private TableColumn<Producto, String> categoriaProductoColumn;
    @FXML
    private TableColumn<Producto, Double> precioProductoColumn;
    private ObservableList<Producto> productosLista;
    @FXML
    private Label cantidadProductosVendedorLabel;

    private PanelListener panelListener;

    public void setEventHandler(PanelListener listener) {
        panelListener = listener;

        desdeDatePicker.setValue(LocalDate.now());
        hastaDatePicker.setValue(LocalDate.now());

        registrarButton.setOnAction(event -> {
            String nombre = nombreTFD.getText();
            nombreTFD.clear();
            listener.registrarListener(nombre);

            actualizarListaVendedores();
        });

        contarPublicacionesButton.setOnAction(event -> {
            LocalDate desde = desdeDatePicker.getValue();
            LocalDate hasta = hastaDatePicker.getValue();

            int total = listener.contarProductosFecha(desde, hasta);

            cantidadProductosFechaLabel.setText(String.valueOf(total));
        });

        cargarListaVendedores();
        cargarTablaProductos();
    }

    private void cargarTablaProductos() {
        productosLista = FXCollections.observableArrayList();
        nombreProductoColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(producto.getValue().getNombreProducto()));
        categoriaProductoColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(producto.getValue().getCategoriaProducto()));
        precioProductoColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(producto.getValue().getPrecioProducto()));

        productosVendedorTableView.setItems(productosLista);
    }

    private void cargarListaVendedores() {
        vendedoresLista = FXCollections.observableArrayList();
        actualizarListaVendedores();
        vendedoresComboBox.setItems(vendedoresLista);

        vendedoresComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarProductosLista(newValue);
        });
    }

    private void actualizarProductosLista(Vendedor vendedorSeleccionado) {
        if(vendedorSeleccionado != null && vendedoresLista.contains(vendedorSeleccionado)) {
            ArbolBinario<Producto> productos = vendedorSeleccionado.getProductos();
            productosLista.clear();
            if (productos.getPeso() > 0) {
                for (Producto producto : productos) {
                    productosLista.add(producto);
                }
            }
            cantidadProductosVendedorLabel.setText(String.valueOf(productosLista.size()));
        }
    }

    private void actualizarListaVendedores() {
        Grafo<Vendedor> vendedores = panelListener.getListaVendedores();
        vendedoresLista.setAll(vendedores.asList());
    }

    @FXML
    private void actualizar() {
        Vendedor vendedorSeleccionado = vendedoresComboBox.getValue();
        actualizarListaVendedores();
        if(vendedorSeleccionado != null) {
            vendedoresComboBox.getSelectionModel().select(vendedorSeleccionado);
            actualizarProductosLista(vendedorSeleccionado);
        }
    }
}
