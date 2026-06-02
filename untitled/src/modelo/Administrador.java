package modelo;

public class Administrador extends Usuario {

    @Override
    public void mostrarPanel() {
        System.out.println("Panel del Administrador");
    }

    public void generarReportes() {
        System.out.println("Generando reportes...");
    }
}