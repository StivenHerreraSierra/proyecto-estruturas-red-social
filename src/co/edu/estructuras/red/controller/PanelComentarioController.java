package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.Principal;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * @author Stiven Herrera Sierra
 */
public class PanelComentarioController {
    @FXML
    private TextArea comentariosTextArea;
    @FXML
    private TextArea campoComentarioTextArea;
    @FXML
    private Button publicarButton;

    private Publicacion publicacion;

    private Vendedor usuario;

    private Principal principal;

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void panelComentarioInitializer(Publicacion publicacion, Vendedor usuario) {
        this.publicacion = publicacion;
        this.usuario = usuario;

        cargarComentarios();
    }

    private void cargarComentarios() {
        comentariosTextArea.clear();
        comentariosTextArea.setText(publicacion.getListaComentariosString());
    }

    @FXML
    private void publicarComentario() {
        boolean publicado = principal.publicarComentario(campoComentarioTextArea.getText(), publicacion, usuario);

        if(publicado) {
            cargarComentarios();
            campoComentarioTextArea.clear();
        }
    }
}
