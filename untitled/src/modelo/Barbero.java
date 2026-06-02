package modelo;

public class Barbero extends Usuario {

    private String especialidad;

    @Override
    public void mostrarPanel() {
        System.out.println("Panel del Barbero");
    }

    public void consultarAgenda() {
        System.out.println("Consultando agenda...");
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}