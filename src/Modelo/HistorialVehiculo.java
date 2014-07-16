/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Felipe
 */
public class HistorialVehiculo {
    
     private String placa;
     private String latitud;
     private String longitud;
     private String fecha;
     private String hora;
     private String recorrido;
     private String estadogps;
     private String datoEvento;
     private String evento;
     private String estado="true";
     private int alertaRecorrido;
     private String dispositivo;

    public HistorialVehiculo() {
    }

    public HistorialVehiculo(String placa, String longitud, String latitud, String fecha, String hora, String recorrido, String estadogps, String datoEvento, String evento, String estado, int alertaRecorrido) {
        this.placa = placa;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.hora = hora;
        this.recorrido = recorrido;
        this.estadogps = estadogps;
        this.datoEvento = datoEvento;
        this.evento = evento;
        this.estado = estado;
        this.alertaRecorrido = alertaRecorrido;
    }

    public HistorialVehiculo(String placa, String longitud, String latitud, String fecha, String recorrido, String estadogps, String datoEvento, String evento, String estado) {
        this.placa = placa;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;    
        this.recorrido = recorrido;
        this.estadogps = estadogps;
        this.datoEvento = datoEvento;
        this.evento = evento;
        this.estado = estado;
    }
    
    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the recorrido
     */
    public String getRecorrido() {
        return recorrido;
    }

    /**
     * @param recorrido the recorrido to set
     */
    public void setRecorrido(String recorrido) {
        this.recorrido = recorrido;
    }

    /**
     * @return the estadogps
     */
    public String getEstadogps() {
        return estadogps;
    }

    /**
     * @param estadogps the estadogps to set
     */
    public void setEstadogps(String estadogps) {
        this.estadogps = estadogps;
    }

    /**
     * @return the datoEvento
     */
    public String getDatoEvento() {
        return datoEvento;
    }

    /**
     * @param datoEvento the datoEvento to set
     */
    public void setDatoEvento(String datoEvento) {
        this.datoEvento = datoEvento;
    }

    /**
     * @return the evento
     */
    public String getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the alertaRecorrido
     */
    public int getAlertaRecorrido() {
        return alertaRecorrido;
    }

    /**
     * @param alertaRecorrido the alertaRecorrido to set
     */
    public void setAlertaRecorrido(int alertaRecorrido) {
        this.alertaRecorrido = alertaRecorrido;
    }

    /**
     * @return the dispositivo
     */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * @param dispositivo the dispositivo to set
     */
    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
    
}
