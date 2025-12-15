package vista;

import modelo.Pelicula;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class VentanaDetalle extends JDialog {

    public VentanaDetalle(JFrame parent, Pelicula pelicula) {
        super(parent, "Detalle: " + pelicula.getTitulo(), true);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel panelInfo = new JPanel(new GridLayout(0, 1));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInfo.add(new JLabel("Título: " + pelicula.getTitulo()));
        panelInfo.add(new JLabel("Director: " + pelicula.getDirector() + " (" + pelicula.getAnio() + ")"));
        panelInfo.add(new JLabel("Género: " + pelicula.getGenero()));

        JTextArea txtDesc = new JTextArea(pelicula.getDescripcion());
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setEditable(false);
        txtDesc.setBackground(this.getBackground());
        panelInfo.add(txtDesc);

        add(panelInfo, BorderLayout.CENTER);

        JLabel lblImagen = new JLabel("Cargando imagen...", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(300, 400));
        add(lblImagen, BorderLayout.NORTH);

        new Thread(() -> {
            try {
                URL url = new URL(pelicula.getImagenUrl());
                Image image = ImageIO.read(url);
                if (image != null) {
                    Image scaled = image.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
                    SwingUtilities.invokeLater(() -> lblImagen.setIcon(new ImageIcon(scaled)));
                    SwingUtilities.invokeLater(() -> lblImagen.setText(""));
                }
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> lblImagen.setText("No se pudo cargar la imagen."));
            }
        }).start();

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }
}