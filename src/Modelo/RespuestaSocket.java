/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.LinkedList;

/**
 *
 * @author Felipe
 */
public class RespuestaSocket {
    
    private String message;
    private String status;
    private String command;    
    private LinkedList<HistorialVehiculo> lista= new LinkedList<>();

    public RespuestaSocket() {
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the lista
     */
    public LinkedList<HistorialVehiculo> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(LinkedList<HistorialVehiculo> lista) {
        this.lista = lista;
    }
    
    
            
}
