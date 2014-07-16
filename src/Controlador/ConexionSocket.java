package Controlador;

import Modelo.TcpSocket;
import Modelo.TcpSocketEvent;
import Modelo.TcpSocketListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class ConexionSocket {

    TcpSocket socket;
    String command = "-1";
    LectorJSON lectorJSON = new LectorJSON();
    
    private TcpSocketListener tcpSocketListener = new TcpSocketListener() {
        @Override
        public void socketEvent(TcpSocketEvent event) {
            switch (event.getId()) {
                case TcpSocketEvent.DATA_ARRIVED:
                    byte[] b = (byte[]) event.getData();
                    String s0 = new String(b); 
                    DAOHistorialVehiculo daohistorialvehiculo= new DAOHistorialVehiculo();
                    daohistorialvehiculo.insertarDesdeSocket(s0);
//                    lectorJSON.leer(s0);                    
                    break;
                case TcpSocketEvent.OPENED:
                    System.out.println("abierto");
                    if (!command.equals("-1")) {
                        socket.send(command);
                        command = "-1";
                    }
                    break;
                case TcpSocketEvent.CLOSED:
                    System.out.println("se ha cerrado");
                    break;
                case TcpSocketEvent.SOCKET_EXCEPTION:
                    System.out.println("excepcion " + event.getData());
                    break;
                default:
                    System.out.println("?");
                    break;
            }
        }
    };

    public void abrir() {
        socket = new TcpSocket();
        socket.addListener(tcpSocketListener);
        socket.open("190.156.229.64", 10000);
    }

    public void enviar(String s) {
        socket.open();
        command = "\n"+s+"\r\n";
    }

    public void cerrar() {
        socket.close();
    }
}
