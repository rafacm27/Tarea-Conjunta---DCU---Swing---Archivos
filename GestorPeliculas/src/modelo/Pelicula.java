package modelo;

public class Pelicula {
    private int id;
    private String titulo;
    private int anio;
    private String director;
    private String descripcion;
    private String genero;
    private String imagenUrl;
    private int idUsuario;

    public Pelicula(int id, String titulo, int anio, String director, String descripcion, String genero, String imagenUrl, int idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.director = director;
        this.descripcion = descripcion;
        this.genero = genero;
        this.imagenUrl = imagenUrl;
        this.idUsuario = idUsuario;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getAnio() { return anio; }
    public String getDirector() { return director; }
    public String getDescripcion() { return descripcion; }
    public String getGenero() { return genero; }
    public String getImagenUrl() { return imagenUrl; }
    public int getIdUsuario() { return idUsuario; }

    public String toCSV() {
        return id + "," + titulo + "," + anio + "," + director + "," + descripcion + "," + genero + "," + imagenUrl + "," + idUsuario;
    }
}