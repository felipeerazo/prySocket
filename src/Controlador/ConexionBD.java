package Controlador;

import Modelo.HistorialVehiculo;
import java.io.Serializable;
import java.sql.*;

public class ConexionBD implements Serializable {

    ResultSet res = null;
    private Connection con = null;
    Statement st = null;
    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://127.0.0.1:5432/db_sae_aquitax";
    String userDB = "postgres";
    String passDB = "admin";

    public Connection getCon() {
        return con;
    }

    public ConexionBD() {
        conectar();
    }

    public int conectar() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el driver :-( " + driver);
            return -1;

        }
        try {
            con = DriverManager.getConnection(url, userDB, passDB);
        } catch (SQLException e) {
            System.out.println("No se pudo realizar la conexion :-(");
            return -2;
        }
        return 1;
    }

    public int insertar(String sql) {
        try {
            CallableStatement cs = getCon().prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            return 1;
        } catch (Exception e) {
            System.out.println(e.toString());
            return -1;
        }
    }

    public StringBuffer consultarPlacaVehiculo(String sql) {
        StringBuffer sb = new StringBuffer();
        try {
            CallableStatement cs = getCon().prepareCall(sql);
            res = cs.executeQuery();
            int x = 1;
            while (res.next()) {
                x = 1;
                while (res.getMetaData().getColumnCount() >= x) {
                    sb.append(res.getString(x));
                    x++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return sb;
    }

    public HistorialVehiculo consultarHistorialVehiculo(String sql) {
        HistorialVehiculo historialVehiculo = new HistorialVehiculo();
        try {
            CallableStatement cs = getCon().prepareCall(sql);
            res = cs.executeQuery();
            int x = 1;
            while (res.next()) {
                x = 1;
                while (res.getMetaData().getColumnCount() >= x) {
                    historialVehiculo = new HistorialVehiculo(res.getString(x), res.getString(x + 1), res.getString(x + 2), res.getString(x + 3), res.getString(x + 4), res.getString(x + 5), res.getString(x + 6), res.getString(x + 7), res.getString(x + 8));
                    x = x + 9;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return historialVehiculo;
    }
}