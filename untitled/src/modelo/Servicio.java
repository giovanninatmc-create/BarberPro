package modelo;

public class Servicio {

    private int idServicio;
    private String nombre;
    private String descripcion;
    private int duracion;
    private double precio;
    private String estado;

    public Servicio() {
    }

    public Servicio(int idServicio, String nombre, String descripcion, int duracion, double precio, String estado) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.precio = precio;
        this.estado = estado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
        return nombre;
    }


}
