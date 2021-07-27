package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Producto;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    private Publicacion publicacion;

    public void publicacionInitializer(PublicacionListener listener, Publicacion publicacion) {
        Vendedor propietario = publicacion.getPropietario();
        Producto producto = publicacion.getProductoDePublicacion();

        usuario.setText(propietario.getNombreVendedor());
        fecha.setText(publicacion.getFechaPublicacionString());
        nombreProducto.setText(producto.getNombreProducto());
        categoriaProducto.setText("Categoria: " + producto.getCategoriaProducto());
        setTotalMeGusta(publicacion.getCantidadMeGusta());

        meGustaButton.setOnAction(event -> {
            if(listener.darMeGusta(propietario, publicacion))
                setTotalMeGusta(publicacion.getCantidadMeGusta());
        });
    }

    public void setTotalMeGusta(int totalMeGusta) {
        this.totalMeGusta.setText(String.valueOf(totalMeGusta));
    }
}
