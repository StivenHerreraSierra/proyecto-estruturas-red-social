package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VendedorTabController {
    private Vendedor vendedor;
    @FXML
    private Label saludoLabel;

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        this.saludoLabel.setText(this.saludoLabel.getText() + " " + vendedor.getNombreVendedor());
    }
}
