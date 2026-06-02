package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private Cliente cliente;
    private Barbero barbero;
    private Servicio servicio;

    public Cita() {
    }

    public Cita(int idCita, LocalDate fecha, LocalTime hora, String estado,
                Cliente cliente, Barbero barbero, Servicio servicio) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.cliente = cliente;
        this.barbero = barbero;
        this.servicio = servicio;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}