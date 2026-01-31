package ticket;

public class Usuario {
    private int id;
    private String nombre;
    private RolUsuario rol;


    public Usuario(int id, String nombre, RolUsuario rol){
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public RolUsuario getRol() { return this.rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }

}
