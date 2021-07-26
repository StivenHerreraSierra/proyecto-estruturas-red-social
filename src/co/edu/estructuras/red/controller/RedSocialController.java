package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.Principal;
import co.edu.estructuras.red.estructuras.Grafo;
import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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

    private VendedorTabListener muroListener = new VendedorTabListener() {
        @Override
        public Grafo<Vendedor> actualizarListaTodos(Vendedor usuario) {
            return principal.getListaTodosVendedoresAgregar(usuario);
        }

        @Override
        public Grafo<Vendedor> actualizarListaSugeridos(Vendedor usuario) {
            return principal.getListaVendedoresSugeridosAgregar(usuario);
        }

        @Override
        public void agregarContacto(Vendedor usuario, Vendedor nuevoContacto) {
            ButtonType resultado = mostrarMensajeConfirmacion("", "Agregar contacto",
                    "¿Quieres agregar a " + nuevoContacto.getNombreVendedor() + "?",
                    "Presiona una de las opciones", null);
            if(resultado == ButtonType.OK)
                principal.agregarContacto(usuario, nuevoContacto);
        }
    };
    private void crearMuro(Vendedor vendedor) {
        try {
            Tab panelTab = new Tab();
            panelTab.setText(vendedor.getNombreVendedor());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/VendedorTabView.fxml"));
            AnchorPane view = loader.load();
            VendedorTabController controller = loader.getController();
            controller.VendedorTabInitializer(vendedor, muroListener);

            panelTab.setContent(view);
            pestanasRedSocial.getTabs().add(panelTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ButtonType mostrarMensajeConfirmacion(String mensaje, String titulo, String cabecera, String contenido, Stage escenarioPrincipal )
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(escenarioPrincipal);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);

        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.get();
    }
}
