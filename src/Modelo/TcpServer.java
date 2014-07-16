package Modelo;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
public class TcpServer extends ServerSocket {

  public String name = "";
  public HashMap userData = new HashMap();
  private SocketAddress serverAddress;
  private boolean running;
  private final ArrayList<TcpServerListener> listenerList = new ArrayList<TcpServerListener>();

  /**
   * Agregar Listener.
   *
   * @param l Listener a agregar
   * @return
   */
  public boolean addListener(TcpServerListener l) {
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
  public boolean removeListener(TcpServerListener l) {
    return listenerList.remove(l);
  }

  public void eventLaunch(TcpServerEvent event) {
    for (TcpServerListener l : listenerList) {
      try {
        l.serverEvent(event);
      } catch (Exception ex) {
      }
    }
  }

  @Override
  public void close() {
    try {
      if (!isClosed()) {
        super.close();
      }
    } catch (IOException ex) {
      ex.getMessage();
    }
  }

  public boolean isRunning() {
    return running;
  }

  private void receptionLoop() {
    TcpServerEvent event;
    if (!isBound()) {
      try {
        bind(serverAddress);
      } catch (IOException ex) {
          System.out.println(ex);
        event = new TcpServerEvent(this, TcpServerEvent.SOCKET_EXCEPTION, "", ex);
        eventLaunch(event);
      }
    }
    if (isBound()) {
      try {
        event = new TcpServerEvent(this, TcpServerEvent.OPENED, "", null);
        eventLaunch(event);
        while (true) {
          Socket socket = accept();
          if (socket != null) {
            event = new TcpServerEvent(this, TcpServerEvent.DATA_ARRIVED, "", socket);
            eventLaunch(event);
          } else break;
        }
      } catch (IOException ex) {
          System.out.println(ex);
        event = new TcpServerEvent(this, TcpServerEvent.SOCKET_EXCEPTION, "", ex);
        eventLaunch(event);
      }
    }
    close();
    event = new TcpServerEvent(this, TcpServerEvent.CLOSED, "", null);
    eventLaunch(event);
    running = false;
  }

  public void start() {
    if (!running) {
      running = true;
      Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
          receptionLoop();
        }
      });
      th.start();
    }
  }

  public void start(String host, int port) {
    if (!running) {
      running = true;
      serverAddress = new InetSocketAddress(host, port);
      Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
          receptionLoop();
        }
      });
      th.start();
    }
  }

  public void start(int port) throws IOException {
    String host = "127.0.0.1";
    start(host, port);
  }

  //<editor-fold defaultstate="collapsed" desc="Constructores ">
  /**
   * Creates an unbound server socket.
   *
   * @exception IOException IO error when opening the socket.
   * @revised 1.4
   */
  public TcpServer() throws IOException {
    super();
  }

  /**
   * Creates a server socket, bound to the specified port. A port number of
   * <code>0</code> means that the port number is automatically allocated,
   * typically from an ephemeral port range. This port number can then be
   * retrieved by calling {@link #getLocalPort getLocalPort}.
   * <p>
   * The maximum queue length for incoming connection indications (a request to
   * connect) is set to <code>50</code>. If a connection indication arrives when
   * the queue is full, the connection is refused.
   * <p>
   * If the application has specified a server socket factory, that factory's
   * <code>createSocketImpl</code> method is called to create the actual socket
   * implementation. Otherwise a "plain" socket is created.
   * <p>
   * If there is a security manager, its <code>checkListen</code> method is
   * called with the <code>port</code> argument as its argument to ensure the
   * operation is allowed. This could result in a SecurityException.
   *
   *
   * @param port the port number, or <code>0</code> to use a port number that is
   *             automatically allocated.
   *
   * @exception IOException              if an I/O error occurs when opening the
   *                                     socket.
   * @exception SecurityException        if a security manager exists and its
   *                                     <code>checkListen</code> method doesn't
   *                                     allow the operation.
   * @exception IllegalArgumentException if the port parameter is outside the
   *                                     specified range of valid port values,
   *                                     which is between 0 and 65535,
   *                                     inclusive.
   *
   * @see java.net.SocketImpl
   * @see java.net.SocketImplFactory#createSocketImpl()
   * @see java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory)
   * @see SecurityManager#checkListen
   */
  public TcpServer(int port) throws IOException {
    super(port);
  }

  /**
   * Creates a server socket and binds it to the specified local port number,
   * with the specified backlog. A port number of <code>0</code> means that the
   * port number is automatically allocated, typically from an ephemeral port
   * range. This port number can then be retrieved by calling
   * {@link #getLocalPort getLocalPort}.
   * <p>
   * The maximum queue length for incoming connection indications (a request to
   * connect) is set to the <code>backlog</code> parameter. If a connection
   * indication arrives when the queue is full, the connection is refused.
   * <p>
   * If the application has specified a server socket factory, that factory's
   * <code>createSocketImpl</code> method is called to create the actual socket
   * implementation. Otherwise a "plain" socket is created.
   * <p>
   * If there is a security manager, its <code>checkListen</code> method is
   * called with the <code>port</code> argument as its argument to ensure the
   * operation is allowed. This could result in a SecurityException.
   *
   * The <code>backlog</code> argument is the requested maximum number of
   * pending connections on the socket. Its exact semantics are implementation
   * specific. In particular, an implementation may impose a maximum length or
   * may choose to ignore the parameter altogther. The value provided should be
   * greater than <code>0</code>. If it is less than or equal to <code>0</code>,
   * then an implementation specific default will be used.
   * <P>
   *
   * @param port    the port number, or <code>0</code> to use a port number that
   *                is automatically allocated.
   * @param backlog requested maximum length of the queue of incoming
   *                connections.
   *
   * @exception IOException              if an I/O error occurs when opening the
   *                                     socket.
   * @exception SecurityException        if a security manager exists and its
   *                                     <code>checkListen</code> method doesn't
   *                                     allow the operation.
   * @exception IllegalArgumentException if the port parameter is outside the
   *                                     specified range of valid port values,
   *                                     which is between 0 and 65535,
   *                                     inclusive.
   *
   * @see java.net.SocketImpl
   * @see java.net.SocketImplFactory#createSocketImpl()
   * @see java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory)
   * @see SecurityManager#checkListen
   */
  public TcpServer(int port, int backlog) throws IOException {
    super(port, backlog);
  }

  /**
   * Create a server with the specified port, listen backlog, and local IP
   * address to bind to. The <i>bindAddr</i> argument can be used on a
   * multi-homed host for a ServerSocket that will only accept connect requests
   * to one of its addresses. If <i>bindAddr</i> is null, it will default
   * accepting connections on any/all local addresses. The port must be between
   * 0 and 65535, inclusive. A port number of <code>0</code> means that the port
   * number is automatically allocated, typically from an ephemeral port range.
   * This port number can then be retrieved by calling
   * {@link #getLocalPort getLocalPort}.
   *
   * <P>
   * If there is a security manager, this method calls its
   * <code>checkListen</code> method with the <code>port</code> argument as its
   * argument to ensure the operation is allowed. This could result in a
   * SecurityException.
   *
   * The <code>backlog</code> argument is the requested maximum number of
   * pending connections on the socket. Its exact semantics are implementation
   * specific. In particular, an implementation may impose a maximum length or
   * may choose to ignore the parameter altogther. The value provided should be
   * greater than <code>0</code>. If it is less than or equal to <code>0</code>,
   * then an implementation specific default will be used.
   * <P>
   * @param port     the port number, or <code>0</code> to use a port number
   *                 that is automatically allocated.
   * @param backlog  requested maximum length of the queue of incoming
   *                 connections.
   * @param bindAddr the local InetAddress the server will bind to
   *
   * @throws SecurityException        if a security manager exists and its
   *                                  <code>checkListen</code> method doesn't
   *                                  allow the operation.
   *
   * @throws IOException              if an I/O error occurs when opening the
   *                                  socket.
   * @exception IllegalArgumentException if the port parameter is outside the
   *                                     specified range of valid port values,
   *                                     which is between 0 and 65535,
   *                                     inclusive.
   *
   * @see SocketOptions
   * @see SocketImpl
   * @see SecurityManager#checkListen
   * @since JDK1.1
   */
  public TcpServer(int port, int backlog, InetAddress bindAddr) throws IOException {
    super(port, backlog, bindAddr);
  }
  //</editor-fold>

}
