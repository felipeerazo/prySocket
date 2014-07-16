/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.HistorialVehiculo;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class DAOHistorialVehiculo extends HistorialVehiculo {

    ConexionBD conexionBD = new ConexionBD();

    public int insertar(HistorialVehiculo hv) throws SQLException {
        String sql = "{call insertarhistorialvehiculo('" + consultarPlacaVehiculo(hv.getDispositivo()) + "'," + hv.getLongitud() + ", " + hv.getLatitud() + ", '" + hv.getFecha() + "', " + hv.getRecorrido() + ", " + hv.getEstadogps() + ", " + hv.getDatoEvento() + ", " + hv.getEvento() + ")}";
        return conexionBD.insertar(sql);
    }

    public String consultarPlacaVehiculo(String dispositivo) throws SQLException {
        String sql = "{call consultarplacavehiculo('" + dispositivo + "')}";
        return conexionBD.consultarPlacaVehiculo(sql).toString();
    }

    public HistorialVehiculo consultarHistorialVehiculoReciente(String placa) throws SQLException {
        String sql = "{call consultar_historial_vehiculo_reciente('" + placa + "')}";
        return conexionBD.consultarHistorialVehiculo(sql);
    }

    public void insertarDesdeSocket(String s) {
        try {
            LectorJSON lectorJSON = new LectorJSON();
            String v[] = s.split("\n");
            if (v.length == 2) {
                HistorialVehiculo historialVehiculoTemporal = lectorJSON.leer(v[0]);
                if (!sonRegistrosIguales(historialVehiculoTemporal, consultarHistorialVehiculoReciente(historialVehiculoTemporal.getPlaca()))) {
                    insertar(historialVehiculoTemporal);
                }

                historialVehiculoTemporal = lectorJSON.leer(v[1]);
                if (!sonRegistrosIguales(historialVehiculoTemporal, consultarHistorialVehiculoReciente(historialVehiculoTemporal.getPlaca()))) {
                    insertar(historialVehiculoTemporal);
                }

            } else {
                String[] vec = v[0].split("\"");
                int contvec = 0;
                for (int i = 0; i < vec.length; i++) {
                    if (vec[i].equals("device")) {
                        contvec++;
                        break;
                    }
                }
                if (contvec == 1) {
                    HistorialVehiculo historialVehiculoTemporal = lectorJSON.leer(v[0]);
                    historialVehiculoTemporal.setPlaca(consultarPlacaVehiculo(historialVehiculoTemporal.getDispositivo()));
                    if (!sonRegistrosIguales(historialVehiculoTemporal, consultarHistorialVehiculoReciente(historialVehiculoTemporal.getPlaca()))) {
                        insertar(historialVehiculoTemporal);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean sonRegistrosIguales(HistorialVehiculo hv1, HistorialVehiculo hv2) {
        if (hv1.getPlaca().equals(hv2.getPlaca())) {
            if (hv1.getDatoEvento().equals(hv2.getDatoEvento())) {
                if (hv1.getEstadogps().equals(hv2.getEstadogps())) {
                    if (hv1.getEvento().equals(hv2.getEvento())) {
                        if (hv1.getFecha().equals(hv2.getFecha())) {
                            if (hv1.getLatitud().equals(hv2.getLatitud())) {
                                if (hv1.getLongitud().equals(hv2.getLongitud())) {
                                    if (hv1.getRecorrido().equals(hv2.getRecorrido())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
