package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.Principal;
import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Producto;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
    private ComboBox<Vendedor> vendedoresComboBoxProducto;
    private ObservableList<Vendedor> vendedoresListaProducto;
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

            actualizarListaVendedores(vendedoresListaProducto);
        });

        contarPublicacionesButton.setOnAction(event -> {
            LocalDate desde = desdeDatePicker.getValue();
            LocalDate hasta = hastaDatePicker.getValue();

            int total = listener.contarProductosFecha(desde, hasta);

            cantidadProductosFechaLabel.setText(String.valueOf(total));
        });

        cargarListaVendedoresProducto();
        cargarTablaProductos();

        cargarContactosUsuarioPanel();

        cargarTopMeGustaPanel();
    }

    private void cargarTablaProductos() {
        productosLista = FXCollections.observableArrayList();
        nombreProductoColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(producto.getValue().getNombreProducto()));
        categoriaProductoColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(producto.getValue().getCategoriaProducto()));
        precioProductoColumn.setCellValueFactory(producto -> new ReadOnlyObjectWrapper<>(producto.getValue().getPrecioProducto()));

        productosVendedorTableView.setItems(productosLista);
    }

    private void cargarListaVendedoresProducto() {
        vendedoresListaProducto = FXCollections.observableArrayList();
        actualizarListaVendedores(vendedoresListaProducto);
        vendedoresComboBoxProducto.setItems(vendedoresListaProducto);

        vendedoresComboBoxProducto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarProductosLista(newValue);
        });
    }

    private void actualizarProductosLista(Vendedor vendedorSeleccionado) {
        if(vendedorSeleccionado != null && vendedoresListaProducto.contains(vendedorSeleccionado)) {
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

    private void actualizarListaVendedores(ObservableList<Vendedor> lista) {
        Grafo<Vendedor> vendedores = panelListener.getListaVendedores();
        lista.setAll(vendedores.asList());
    }

    @FXML
    private void actualizarProductos() {
        Vendedor vendedorSeleccionado = vendedoresComboBoxProducto.getValue();
        actualizarListaVendedores(vendedoresListaProducto);
        if(vendedorSeleccionado != null) {
            vendedoresComboBoxProducto.getSelectionModel().select(vendedorSeleccionado);
            actualizarProductosLista(vendedorSeleccionado);
        }
    }

    //===================================Controlador para Contactos por usuario===================================
    private ContactosUsuarioPanelListener listenerContactos = new ContactosUsuarioPanelListener() {
        @Override
        public Grafo<Vendedor> getListaVendedores() {
            return panelListener.getListaVendedores();
        }

        @Override
        public Grafo<Vendedor> getListaContactos(Vendedor vendedorSeleccionado) {
            return panelListener.getListaContactos(vendedorSeleccionado);
        }

        @Override
        public void cantidadMensajesIntercambiados(Vendedor usuario1, Vendedor usuario2) {
            panelListener.cantidadMensajesIntercambiados(usuario1, usuario2);
        }
    };

    @FXML
    private TitledPane contactosPanel;

    private void cargarContactosUsuarioPanel() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("view/ContactosUsuarioPanelView.fxml"));
            AnchorPane view = loader.load();
            contactosPanel.setContent(view);
            ContactosUsuarioPanelController controller = loader.getController();
            controller.setListener(listenerContactos);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //===================================Controlador para Top Publicaciones===================================
    private TopMeGustaPanelListener topMeGustaListener = new TopMeGustaPanelListener() {
        @Override
        public List<Publicacion> getTopPublicaciones() {
            return panelListener.getTopPublicaciones();
        }
    };

    @FXML
    private TitledPane topMeGustaPanel;

    private void cargarTopMeGustaPanel() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("view/TopMeGustaPanelView.fxml"));
            AnchorPane view = loader.load();
            topMeGustaPanel.setContent(view);
            TopMeGustaPanelController controller = loader.getController();
            controller.setListener(topMeGustaListener);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
