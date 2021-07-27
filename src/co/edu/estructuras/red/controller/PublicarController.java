package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PublicarController {
    @FXML
    private TextField nombreTFD;
    @FXML
    private TextField categoriaTFD;
    @FXML
    private Button publicarButton;

    private Vendedor usuario;

    public void setListener(PublicarListener listener, Vendedor usuario) {
        this.usuario = usuario;

        publicarButton.setOnAction(event -> {
            if(listener.publicar(usuario, nombreTFD.getText(), categoriaTFD.getText()))
                limpiarCampos();
        });
    }

    @FXML
    public void limpiarCampos() {
        nombreTFD.clear();
        categoriaTFD.clear();
    }
}
