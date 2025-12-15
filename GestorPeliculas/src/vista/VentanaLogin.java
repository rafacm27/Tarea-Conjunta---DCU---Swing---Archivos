package vista;

import controlador.ControladorPeliculas;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private ControladorPeliculas controlador;

    public VentanaLogin(ControladorPeliculas controlador) {
        this.controlador = controlador;
        configurarVentana();
        iniciarComponentes();
    }

    private void configurarVentana() {
        setTitle("Login - Mis Películas");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));
    }

    private void iniciarComponentes() {
        JPanel panelEmail = new JPanel();
        panelEmail.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField("rafa.cesur.@example.com", 15);
        panelEmail.add(txtEmail);

        JPanel panelPass = new JPanel();
        panelPass.add(new JLabel("Pass:"));
        JPasswordField txtPass = new JPasswordField("rafa123", 15);
        panelPass.add(txtPass);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String pass = new String(txtPass.getPassword());
            if (controlador.iniciarSesion(email, pass)) {
                new VentanaPrincipal(controlador).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });

        add(panelEmail);
        add(panelPass);
        add(btnLogin);
    }
}