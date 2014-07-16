package SocketTCP;

import Controlador.Alerta;
import Controlador.ConexionSocket;
import Controlador.DAOHistorialVehiculo;
import Controlador.LectorJSON;
import Modelo.HistorialVehiculo;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        ConexionSocket conexion = new ConexionSocket();
        
        conexion.abrir();
        conexion.enviar("command=listen");
        

    }
}
