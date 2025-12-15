package controlador;

import datos.GestorDatos;
import modelo.Pelicula;
import modelo.Usuario;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorPeliculas {
    private GestorDatos gestorDatos;
    private Usuario usuarioLogueado;
    private List<Pelicula> todasLasPeliculas;

    public ControladorPeliculas() {
        this.gestorDatos = new GestorDatos();
        this.todasLasPeliculas = gestorDatos.cargarPeliculas();
    }

    public boolean iniciarSesion(String email, String password) {
        List<Usuario> usuarios = gestorDatos.cargarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                this.usuarioLogueado = u;
                return true;
            }
        }
        return false;
    }

    public List<Pelicula> getPeliculasUsuario() {
        return todasLasPeliculas.stream()
                .filter(p -> p.getIdUsuario() == usuarioLogueado.getId())
                .collect(Collectors.toList());
    }

    public void agregarPelicula(String titulo, int anio, String director, String desc, String genero, String url) {
        int maxId = todasLasPeliculas.stream().mapToInt(Pelicula::getId).max().orElse(0);
        Pelicula nueva = new Pelicula(maxId + 1, titulo, anio, director, desc, genero, url, usuarioLogueado.getId());
        todasLasPeliculas.add(nueva);
        gestorDatos.guardarPeliculas(todasLasPeliculas);
    }

    public void eliminarPelicula(int idPelicula) {
        todasLasPeliculas.removeIf(p -> p.getId() == idPelicula && p.getIdUsuario() == usuarioLogueado.getId());
        gestorDatos.guardarPeliculas(todasLasPeliculas);
    }

    public void cerrarSesion() {
        this.usuarioLogueado = null;
    }
}