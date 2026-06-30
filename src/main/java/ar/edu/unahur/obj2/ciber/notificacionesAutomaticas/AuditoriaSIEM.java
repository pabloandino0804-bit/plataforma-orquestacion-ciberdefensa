package ar.edu.unahur.obj2.ciber.notificacionesAutomaticas;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class AuditoriaSIEM implements IObservadorNodo {

    @Override
    public void reaccionar(NodoRed nodoRed, String tipo, Integer mbps) {
        if (tipo != "Alerta")
            System.out.println(
                    tipo + " " + mbps + " Mbps " + "en el nodo " + nodoRed.getID());
    }
}
