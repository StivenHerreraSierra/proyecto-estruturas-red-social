package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Producto;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class PublicacionController {
    @FXML
    private Label usuario;
    @FXML
    private Label fecha;
    @FXML
    private Label nombreProducto;
    @FXML
    private Label categoriaProducto;
    @FXML
    private Label totalMeGusta;
    @FXML
    private Button meGustaButton;
    @FXML
    private Button comentarButton;
    @FXML
    private Tooltip tooltipMeGusta;

    private Publicacion publicacion;

    public void publicacionInitializer(PublicacionListener listener, Publicacion publicacion, boolean meGustaActivado) {
        this.publicacion = publicacion;
        Vendedor propietario = publicacion.getPropietario();
        Producto producto = publicacion.getProductoDePublicacion();
        tooltipMeGusta = new Tooltip();
        totalMeGusta.setTooltip(tooltipMeGusta);
        cargarTootip();

        usuario.setText(propietario.getNombreVendedor());
        fecha.setText(publicacion.getFechaPublicacionString());
        nombreProducto.setText(producto.getNombreProducto());
        categoriaProducto.setText("Categoria: " + producto.getCategoriaProducto());
        setTotalMeGusta(publicacion.getCantidadMeGusta());

        darEstiloBotonMeGusta(meGustaActivado);
        meGustaButton.setOnAction(event -> {
            darEstiloBotonMeGusta(listener.darMeGusta(publicacion));
            setTotalMeGusta(publicacion.getCantidadMeGusta());
            cargarTootip();
        });
    }

    private void darEstiloBotonMeGusta(boolean meGustaActivado) {
        if(meGustaActivado)
            meGustaButton.setStyle("-fx-background-color: #00C1D4;");
        else
            meGustaButton.setStyle("-fx-background-color: #EEEEEE;");
    }

    public void setTotalMeGusta(int totalMeGusta) {
        this.totalMeGusta.setText(String.valueOf(totalMeGusta));
    }

    public void cargarTootip() {
        tooltipMeGusta.setText(publicacion.getListaMeGustaString());
    }
}
