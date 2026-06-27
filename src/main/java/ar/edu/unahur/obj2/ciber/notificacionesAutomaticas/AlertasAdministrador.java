package ar.edu.unahur.obj2.ciber.notificacionesAutomaticas;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class AlertasAdministrador implements INotificacion {

    @Override
    public void reaccionar(NodoRed nodoRed, String tipo, Integer mbps) {
        if (tipo == "Ampliar")
            System.out.print(
                    "[ADMIN] Se acreditaron " + mbps + " Mbps " + "de ancho de banda en su nodo " + nodoRed.getID());

        if (tipo == "Restringir")
            System.out.print(
                    "[ADMIN] Se debitaron " + mbps + " Mbps " + "de ancho de banda de su nodo " + nodoRed.getID());
    }

}
