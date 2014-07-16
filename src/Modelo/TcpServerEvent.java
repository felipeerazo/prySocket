package Modelo;


import java.util.EventObject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipe
 */
public class TcpServerEvent extends EventObject {

  public static final int OPENED = 1;
  public static final int CLOSED = 2;
  public static final int DATA_ARRIVED = 3;
  public static final int SOCKET_EXCEPTION = 4;

  private final int id;
  private final String action;
  private final Object data;

  public int getId() {
    return id;
  }

  public String getAction() {
    return action;
  }

  public Object getData() {
    return data;
  }

  public TcpServerEvent(Object source, int id, String action, Object data) {
    super(source);
    this.id = id;
    this.action = action;
    this.data = data;
  }

}
