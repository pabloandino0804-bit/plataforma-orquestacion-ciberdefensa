package ar.edu.unahur.obj2.ciber.notificacionesAutomaticas;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class AuditoriaSIEM implements INotificacion {

    @Override
    public void reaccionar(NodoRed nodoRed, String tipo, Integer mbps) {
        System.out.println(
                tipo + mbps + " Mbps " + "en el nodo " + nodoRed.getID());
    }
}
