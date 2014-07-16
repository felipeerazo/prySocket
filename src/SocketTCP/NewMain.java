/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketTCP;

import Controlador.ConexionBD;
import Controlador.DAOHistorialVehiculo;
import Modelo.HistorialVehiculo;
import java.sql.SQLException;

/**
 *
 * @author Camilo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        DAOHistorialVehiculo dhv= new DAOHistorialVehiculo();
        HistorialVehiculo his=  dhv.consultarHistorialVehiculoReciente("AF1431");
        System.out.println("");
    }
}
