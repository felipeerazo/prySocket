package Controlador;

import Modelo.HistorialVehiculo;
import Modelo.RespuestaSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class LectorJSON {

//    DAOHistorialVehiculo daohv = new DAOHistorialVehiculo();
    public boolean listen = false;
    int v0 = 0;
    

    public LectorJSON() {
    }
//    Alerta alerta = new Alerta();
    //HistorialVehiculo historialVehiculo = new HistorialVehiculo();

    public HistorialVehiculo leer(String s) {
        try {
            HistorialVehiculo historialVehiculo = new HistorialVehiculo();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(s);
            //cuando el comando es listen se activa la alarma de recorrido                                
            JSONObject device = (JSONObject) jsonObject.get("device");
            historialVehiculo.setDispositivo(device.get("I").toString());
            historialVehiculo.setEvento(device.get("E").toString());
            historialVehiculo.setDatoEvento(device.get("F").toString());
            historialVehiculo.setFecha(device.get("D").toString());
            historialVehiculo.setLatitud(device.get("X").toString());
            historialVehiculo.setLongitud(device.get("Y").toString());
            historialVehiculo.setRecorrido(device.get("R").toString());
            historialVehiculo.setEstadogps(device.get("G").toString());
            return historialVehiculo;
        } catch (Exception e) {
            System.out.println("Error LectorJSON.leer: " + e);
            return null;
        }
    }
}
