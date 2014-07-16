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
public interface TcpSocketListener extends EventListener {
  public void socketEvent(TcpSocketEvent event);
}
