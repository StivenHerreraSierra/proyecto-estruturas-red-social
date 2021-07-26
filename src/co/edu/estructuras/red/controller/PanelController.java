package co.edu.estructuras.red.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PanelController {
    @FXML
    private TextField nombreTFD;
    @FXML
    private Button registrarButton;

    public void setEventHandler(PanelListener listener) {
        System.out.println("Ocurrió un evento.");
        registrarButton.setOnAction(event -> listener.registrarListener(nombreTFD.getText()));
    }
}
