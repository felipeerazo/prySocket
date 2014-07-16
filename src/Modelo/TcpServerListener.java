package Modelo;


import java.util.EventListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipe
 */
public interface TcpServerListener extends EventListener {
  public void serverEvent(TcpServerEvent event);
}
