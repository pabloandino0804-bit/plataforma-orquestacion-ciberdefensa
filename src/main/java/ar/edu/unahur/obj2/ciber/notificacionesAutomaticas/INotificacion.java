package ar.edu.unahur.obj2.ciber.notificacionesAutomaticas;

import ar.edu.unahur.obj2.ciber.NodoRed;

public interface INotificacion {

    void reaccionar(NodoRed nodoRed, String tipo, Integer mbps);

}