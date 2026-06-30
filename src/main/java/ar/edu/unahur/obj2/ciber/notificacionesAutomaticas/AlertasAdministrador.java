package ar.edu.unahur.obj2.ciber.notificacionesAutomaticas;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class AlertasAdministrador implements IObservadorNodo {

    @Override
    public void reaccionar(NodoRed nodoRed, String tipo, Integer mbps) {
        if (tipo == "Ampliar")
            System.out.print(
                    "[ADMIN] Se ampliaron " + mbps + " Mbps " + "de ancho de banda en su nodo " + nodoRed.getID());

        if (tipo == "Restringir")
            System.out.print(
                    "[ADMIN] Se redujeron " + mbps + " Mbps " + "de ancho de banda de su nodo " + nodoRed.getID());
    }

}
