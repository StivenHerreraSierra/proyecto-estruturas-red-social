package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Stiven Herrera Sierra
 */
public class PublicarController {
    @FXML
    private TextField nombreTFD;
    @FXML
    private TextField categoriaTFD;
    @FXML
    private TextField precioTFD;
    @FXML
    private Button publicarButton;

    public void setListener(PublicarListener listener, Vendedor usuario) {
        publicarButton.setOnAction(event -> {
            if(listener.publicar(usuario, nombreTFD.getText(), categoriaTFD.getText(), precioTFD.getText()))
                limpiarCampos();
        });
    }

    @FXML
    public void limpiarCampos() {
        nombreTFD.clear();
        categoriaTFD.clear();
        precioTFD.clear();
    }
}
