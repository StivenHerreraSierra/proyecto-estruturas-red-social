package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.Principal;
import co.edu.estructuras.red.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RedSocialController {
    private Principal principal;
    @FXML
    private TabPane pestanasRedSocial;

    public void setPrincipal(Principal principal) {
        this.principal = principal;
        loadPanel();
    }

    private void loadPanel(){
        try {
            Tab panelTab = new Tab();
            panelTab.setText("Panel de control");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/PanelView.fxml"));
            AnchorPane view = loader.load();
            PanelController controller = loader.getController();
            PanelListener listener = nombreUsuario -> registrarUsuario(nombreUsuario);
            controller.setEventHandler(listener);

            panelTab.setContent(view);
            pestanasRedSocial.getTabs().add(panelTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarUsuario(String nombre) {
        Vendedor vendedor = principal.registrarVendedor(nombre);

        if(vendedor != null)
            crearMuro(vendedor);
    }

    private void crearMuro(Vendedor vendedor) {
        try {
            Tab panelTab = new Tab();
            panelTab.setText(vendedor.getNombreVendedor());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/VendedorTabView.fxml"));
            AnchorPane view = loader.load();
            VendedorTabController controller = loader.getController();
            controller.setVendedor(vendedor);

            panelTab.setContent(view);
            pestanasRedSocial.getTabs().add(panelTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
