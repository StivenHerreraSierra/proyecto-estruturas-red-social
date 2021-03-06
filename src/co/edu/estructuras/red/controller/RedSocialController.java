package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.Principal;
import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/**
 * @author Stiven Herrera Sierra
 */
public class RedSocialController {
    private Principal principal;
    @FXML
    private TabPane pestanasRedSocial;

    public void setPrincipal(Principal principal) {
        this.principal = principal;
        loadPanel();
    }

    private PanelListener listener = new PanelListener() {
        @Override
        public void registrarListener(String nombreUsuario) {
            registrarUsuario(nombreUsuario);
        }

        @Override
        public int contarProductosFecha(LocalDate desde, LocalDate hasta) {
            return principal.contarProductosFecha(desde, hasta);
        }

        @Override
        public Grafo<Vendedor> getListaVendedores() {
            return principal.getVendedores();
        }

        @Override
        public Grafo<Vendedor> getListaContactos(Vendedor vendedor) {
            return principal.getListaContactos(vendedor);
        }

        @Override
        public List<Publicacion> getTopPublicaciones() {
            return principal.getTopPublicaciones();
        }

        @Override
        public void cantidadMensajesIntercambiados(Vendedor usuario1, Vendedor usuario2) {
            principal.cantidadMensajesIntercambiados(usuario1, usuario2);
        }

        @Override
        public void guardarSesion() {
            principal.guardarSesion();
        }

        @Override
        public void cargarSesion() {
            if(principal.cargarSesion())
                cargarMuroUsuarios();
        }
    };

    private void loadPanel(){
        try {
            Tab panelTab = new Tab();
            panelTab.setText("Panel de control");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/PanelView.fxml"));
            AnchorPane view = loader.load();
            PanelController controller = loader.getController();

            controller.setEventHandler(listener);

            panelTab.setContent(view);
            pestanasRedSocial.getTabs().add(panelTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarMuroUsuarios() {
        Grafo<Vendedor> usuariosCargados = principal.getVendedores();
        Iterator<Vendedor> it = usuariosCargados.iterator();

        pestanasRedSocial.getTabs().clear();
        loadPanel();

        while(it.hasNext()) {
            crearMuro(it.next());
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
            controller.VendedorTabInitializer(vendedor, muroListener, publicarListener);

            panelTab.setContent(view);
            pestanasRedSocial.getTabs().add(panelTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            principal.agregarContacto(usuario, nuevoContacto);
        }

        @Override
        public Grafo<Vendedor> actualizarListaContactos(Vendedor usuario) {
            return principal.getListaContactos(usuario);
        }

        @Override
        public ArbolBinario<Publicacion> actualizarMuro(Vendedor usuario) {
            return principal.getPublicacionesVendedor(usuario);
        }

        @Override
        public void agregarContactoBuscado(Vendedor usuario, String nombre) {
            principal.agregarContactoBuscado(usuario, nombre);
        }

        @Override
        public boolean darMeGusta(Vendedor usuario, Publicacion publicacion) {
            return principal.darMeGusta(usuario, publicacion);
        }

        @Override
        public void comentar(Vendedor usuario, Publicacion publicacion, String nuevoComentario) {

        }

        @Override
        public boolean meGusta(Publicacion publicacion, Vendedor usuario) {
            return principal.meGusta(publicacion, usuario);
        }

        @Override
        public void mostrarComentarios(Publicacion publicacion, Vendedor usuario) {
            principal.mostrarVentanaComentarios(publicacion, usuario);
        }

        @Override
        public void iniciarChat(Vendedor usuario1, Vendedor usuario2) {
            principal.iniciarChat(usuario1, usuario2);
        }
    };

    private PublicarListener publicarListener = new PublicarListener() {
        @Override
        public boolean publicar(Vendedor usuario, String nombre, String categoria, String precio) {
            principal.registrarPublicacion(usuario, nombre, categoria, precio);
            return true;
        }
    };
}
