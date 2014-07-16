/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.HistorialVehiculo;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Felipe
 */
public class Alerta {

    LinkedList<HistorialVehiculo> historialVehiculos = new LinkedList<>();

    public Alerta() {
    }

    //id, placa, longitud, latitud, fecha, recorrido, datoevento, evento, estadogps.
    public void revisar(String dispositivo, String longitud, String latitud, String fecha, String recorrido, String estadogps, String datoEvento, String evento) throws SQLException {
        HistorialVehiculo hv = new HistorialVehiculo();
        DAOHistorialVehiculo daohv = new DAOHistorialVehiculo();
        hv.setDispositivo(dispositivo);
        hv.setLongitud(longitud);
        hv.setLatitud(latitud);
        hv.setFecha(fecha);
        hv.setRecorrido(recorrido);
        hv.setEstadogps(estadogps);
        hv.setDatoEvento(datoEvento);
        hv.setEvento(evento);
        hv.setEstado("TRUE");


        int i = getIndice(dispositivo);
//        if (i != -1) {
//            if (getMaxDate(fecha, historialVehiculos.get(i).getFecha()).equals(fecha)) {
//                int dif = Integer.parseInt(recorrido) - Integer.parseInt(historialVehiculos.get(i).getRecorrido());
//                if (dif < 0) {
//                    dif = dif & 65535;
//                    System.out.println("dif bitwise: " + dif);
//                }
//                if (dif >= 1000) {
//                    System.out.println("r anterior: " + historialVehiculos.get(i).getRecorrido() + " r nuevo: " + recorrido);
//                    historialVehiculos.get(i).setRecorrido(recorrido);
//                }
//            }
//        } else {
            historialVehiculos.add(hv);
            daohv.insertar(hv);
            System.out.println("se guardó un registro");
        //}
    }

//     public void revisar(String dispositivo, String recorrido, String fecha) {
//        HistorialVehiculo hv = new HistorialVehiculo();
//        hv.setDispositivo(dispositivo);
//        hv.setRecorrido(recorrido);
//        hv.setFecha(fecha);        
//        int i = getIndice(dispositivo);
//        if (i != -1) {
//            if (getMaxDate(fecha, historialVehiculos.get(i).getFecha()).equals(fecha)) {
//                int dif=Integer.parseInt(recorrido) - Integer.parseInt(historialVehiculos.get(i).getRecorrido());
//                if(dif<0){
//                    dif=dif & 65535;
//                    System.out.println("dif bitwise: "+dif);
//                }
//                if (dif>=1000) {
//                    System.out.println("r anterior: " + historialVehiculos.get(i).getRecorrido() + " r nuevo: " + recorrido);
//                    historialVehiculos.get(i).setRecorrido(recorrido);
//                }
//            }
//        } else {
//            historialVehiculos.add(hv);
//            System.out.println("se guardó un registro");
//        }
//    }
    /**
     *
     * @param fecha1
     * @param fechaActual
     * @return la fecha mayor entre fecha1 y fechaActual, null en caso de error
     */
    public String getMaxDate(String fecha1, String fechaActual) {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaDate1 = formateador.parse(fecha1);
            Date fechaDate2 = formateador.parse(fechaActual);

            if (fechaDate1.before(fechaDate2)) {
                return fechaActual;
            } else {
                if (fechaDate2.before(fechaDate1)) {
                    return fecha1;
                } else {
                    return fecha1;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    private int getIndice(String dispositivo) {
        for (int i = 0; i < historialVehiculos.size(); i++) {
            if (historialVehiculos.get(i).getDispositivo().equals(dispositivo)) {
                return i;
            }
        }
        return -1;
    }
}
