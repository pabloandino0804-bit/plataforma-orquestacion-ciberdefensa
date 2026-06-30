package ar.edu.unahur.obj2.ciber.notificacionesAutomaticas;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class AlertaSobrecarga implements IObservadorNodo {
    private Integer umbralCritico = -50_000;

    @Override
    public void reaccionar(NodoRed nodoRed, String tipo, Integer mbps) {
        if (tipo == "Alerta" && nodoRed.getCapacidadActual() - mbps < -50000)
            System.out.print(
                    "La capacidad del nodo dado " + nodoRed.getID() + " paso hasta mas de "
                            + this.umbralCritico);
    }
}
