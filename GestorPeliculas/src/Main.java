import controlador.ControladorPeliculas;
import vista.VentanaLogin;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorPeliculas controlador = new ControladorPeliculas();
            new VentanaLogin(controlador).setVisible(true);
        });
    }
}