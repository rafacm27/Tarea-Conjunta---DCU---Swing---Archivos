package modelo;

public class Usuario {
    private int id;
    private String email;
    private String password;

    public Usuario(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}