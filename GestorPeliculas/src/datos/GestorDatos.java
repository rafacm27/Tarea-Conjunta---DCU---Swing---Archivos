package datos;

import modelo.Pelicula;
import modelo.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {
    private final String ARCHIVO_USUARIOS = "usuarios.csv";
    private final String ARCHIVO_PELICULAS = "peliculas.csv";

    public List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    usuarios.add(new Usuario(Integer.parseInt(datos[0]), datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public List<Pelicula> cargarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PELICULAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
                    peliculas.add(new Pelicula(
                            Integer.parseInt(datos[0]),
                            datos[1],
                            Integer.parseInt(datos[2]),
                            datos[3],
                            datos[4],
                            datos[5],
                            datos[6],
                            Integer.parseInt(datos[7])
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar películas o archivo no encontrado.");
        }
        return peliculas;
    }

    public void guardarPeliculas(List<Pelicula> peliculas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PELICULAS))) {
            for (Pelicula p : peliculas) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}