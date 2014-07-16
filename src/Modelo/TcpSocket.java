package Modelo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class TcpSocket {

    public String name = "";
    public int id = 0;
    public HashMap userData = new HashMap();
    private Socket socket;
    private int timeOut = 30000;
    private SocketAddress socketAddress;
    private boolean running;
    private final ArrayList<TcpSocketListener> listenerList;

    /**
     * Agregar Listener.
     *
     * @param l Listener a agregar
     * @return
     */
    public boolean addListener(TcpSocketListener l) {
        if (!listenerList.contains(l)) {
            return listenerList.add(l);
        }
        return true;
    }

    /**
     * Quiter/Remover Listener
     *
     * @param l Listener a quitar.
     * @return
     */
    public boolean removeListener(TcpSocketListener l) {
        return listenerList.remove(l);
    }

    private void eventLaunch(TcpSocketEvent event) {
        ArrayList<TcpSocketListener> ll = new ArrayList<>();
        ll.addAll(listenerList);
        for (TcpSocketListener l : ll) {
            try {
                l.socketEvent(event);
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
    }

    public void close() {
        try {
            if ((socket != null) && (!socket.isClosed())) {
                socket.close();
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /**
     * Obtener Socket
     *
     * @return socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Obtener Puerto desde el cual se conecta este Socket
     *
     * @return Puertp desde el cual se conecta este socket.
     */
    public int getPort() {
        return (socket != null) ? socket.getPort() : -1;
    }

    public HashMap getUserData() {
        return userData;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public boolean isConnected() {
        if (socket != null) {
            return !socket.isClosed();
        }
        return false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setUserData(HashMap userData) {
        this.userData = userData;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Iniciar proceso de escucha ...
     *
     * @return TRUE proceso de escucha iniciado.
     */
    public boolean open() {
        if (socket != null && !running) {
            running = true;
            socketAddress = null;
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    receptionLoop();
                }
            });
            th.start();
            return true;
        }
        return false;
    }

    /**
     * Iniciar proceso de escucha ...
     *
     * @param port Puerto (Server)
     * @return TRUE proceso de escucha iniciado.
     */
    public boolean open(int port) {
        return open("127.0.0.1", port);
    }

    /**
     * Iniciar proceso de escucha ...
     *
     * @param host Host (Server).
     * @param port port (Server).
     * @return TRUE proceso de escucha iniciado.
     */
    public boolean open(String host, int port) {
        if (!running) {
            running = true;
            socketAddress = new InetSocketAddress(host, port);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    receptionLoop();
                }
            });
            th.start();
            return true;
        }
        return false;
    }

    private void receptionLoop() {
        TcpSocketEvent event;
        if (socket == null) {
            socket = new Socket();
        }
        if (!socket.isConnected()) {
            if (socketAddress != null) {
                try {
                    socket.connect(socketAddress, timeOut);
                } catch (IOException ex) {
                    event = new TcpSocketEvent(this, TcpSocketEvent.SOCKET_EXCEPTION, "", ex);
                    eventLaunch(event);
                    System.out.println(ex);
                }
            }
        }
        if (socket.isConnected()) {
            event = new TcpSocketEvent(this, TcpSocketEvent.OPENED, "", null);
            eventLaunch(event);
            while (socket.isConnected() && !socket.isClosed()) {
                try {
                    int a;
                    byte[] data = new byte[16384];
                    a = socket.getInputStream().read(data);
                    if (a > 0) {
                        byte[] bytes = new byte[a];
                        System.arraycopy(data, 0, bytes, 0, a);
                        event = new TcpSocketEvent(this, TcpSocketEvent.DATA_ARRIVED, "", bytes);
                        eventLaunch(event);
                    } else {
                        break;
                    }
                    synchronized (this) {
                        try {
                            wait(25);
                        } catch (InterruptedException ex) {
                            ex.getMessage();
                        }
                    }
                } catch (IOException ex) {
                    ex.getMessage();
                    System.out.println("1");
                    break;
                }
            }
        }
        close();
        event = new TcpSocketEvent(this, TcpSocketEvent.CLOSED, "", null);
        eventLaunch(event);
        running = false;
    }

    public boolean write(byte[] bytes, int off, int len) {
        try {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.getOutputStream().write(bytes, off, len);
                    socket.getOutputStream().flush();
                    return true;

                } catch (Exception ex) {
                    System.out.println(ex);
                    ex.getMessage();
                    close();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ex.getMessage();
            close();
        }
        return false;
    }

    public boolean write(String txt) {
        byte[] bytes = txt.getBytes();
        return write(bytes, 0, bytes.length);
    }

    public boolean write(byte[] bytes) {
        return write(bytes, 0, bytes.length);
    }

    public boolean send(byte[] bytes, int off, int len) {
        return write(bytes, off, len);
    }

    public boolean send(String txt) {
        return write(txt);
    }

    public boolean send(byte[] bytes) {
        return write(bytes);
    }

    public TcpSocket() {
        super();
        listenerList = new ArrayList<>();
        userData = new HashMap();
    }

    public TcpSocket(Socket socket) {
        super();
        this.socket = socket;
        listenerList = new ArrayList<>();
        userData = new HashMap();
    }
}
