package vista;

import controlador.ControladorPeliculas;
import modelo.Pelicula;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private ControladorPeliculas controlador;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;

    public VentanaPrincipal(ControladorPeliculas controlador) {
        this.controlador = controlador;
        configurarVentana();
        iniciarComponentes();
        cargarDatosTabla();
    }

    private void configurarVentana() {
        setTitle("Mi Colección de Películas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void iniciarComponentes() {
        String[] columnas = {"ID", "Título", "Año", "Director", "Género"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPeliculas = new JTable(modeloTabla);
        add(new JScrollPane(tablaPeliculas), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnDetalle = new JButton("Ver Detalle");
        JButton btnAgregar = new JButton("Añadir Película");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLogout = new JButton("Cerrar Sesión");

        btnDetalle.addActionListener(e -> mostrarDetalle());
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnEliminar.addActionListener(e -> eliminarPeliculaSeleccionada());
        btnLogout.addActionListener(e -> {
            controlador.cerrarSesion();
            new VentanaLogin(controlador).setVisible(true);
            this.dispose();
        });

        panelBotones.add(btnDetalle);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLogout);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<Pelicula> lista = controlador.getPeliculasUsuario();
        for (Pelicula p : lista) {
            modeloTabla.addRow(new Object[]{p.getId(), p.getTitulo(), p.getAnio(), p.getDirector(), p.getGenero()});
        }
    }

    private void mostrarDetalle() {
        int fila = tablaPeliculas.getSelectedRow();
        if (fila >= 0) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            Pelicula p = controlador.getPeliculasUsuario().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
            if (p != null) new VentanaDetalle(this, p).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una película.");
        }
    }

    private void eliminarPeliculaSeleccionada() {
        int fila = tablaPeliculas.getSelectedRow();
        if (fila >= 0) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Borrar película?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controlador.eliminarPelicula(id);
                cargarDatosTabla();
            }
        }
    }

    private void mostrarDialogoAgregar() {
        JTextField titulo = new JTextField();
        JTextField anio = new JTextField();
        JTextField director = new JTextField();
        JTextField desc = new JTextField();
        JTextField genero = new JTextField();
        JTextField url = new JTextField("https://via.placeholder.com/150");

        Object[] message = {
                "Título:", titulo, "Año:", anio, "Director:", director,
                "Descripción:", desc, "Género:", genero, "URL Imagen:", url
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nueva Película", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                controlador.agregarPelicula(titulo.getText(), Integer.parseInt(anio.getText()),
                        director.getText(), desc.getText(), genero.getText(), url.getText());
                cargarDatosTabla();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El año debe ser un número.");
            }
        }
    }
}