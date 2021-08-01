package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.Principal;
import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatController {
    @FXML
    private TextArea mensajesArea;
    @FXML
    private TextArea campoMensaje;
    @FXML
    private Label usuario1Label;
    @FXML
    private Label usuario2Label;

    private Principal principal;

    private Vendedor usuario1;
    private Vendedor usuario2;

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void setUsuarios(Vendedor usuario1, Vendedor usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;

        usuario1Label.setText(usuario1.getNombreVendedor());
        usuario2Label.setText(usuario2.getNombreVendedor());

        actualizarMensajes();
    }

    @FXML
    public void enviarMensaje() {
        String mensaje = usuario1.getNombreVendedor() + " - " +
                        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(LocalDateTime.now()) + "\n" +
                        campoMensaje.getText();
        if(principal.enviarMensaje(mensaje, usuario1, usuario2)) {
            campoMensaje.clear();
            actualizarMensajes();
        }
    }

    public void actualizarMensajes() {
        this.mensajesArea.setText(principal.getMensajesChat(usuario1, usuario2));
    }
}
