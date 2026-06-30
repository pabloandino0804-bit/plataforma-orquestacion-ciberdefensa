package ar.edu.unahur.obj2.ciber;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.ciber.notificacionesAutomaticas.IObservadorNodo;

public class NodoRed {
    private String idNodo;
    private Integer mbps;
    private List<IObservadorNodo> observadores = new ArrayList<>();

    public NodoRed(String id, Integer mbps) {
        this.idNodo = id;
        this.mbps = mbps;
    }

    public String getID() {
        return idNodo;
    }

    public Integer getCapacidadActual() {
        return mbps;
    }

    public void ampliarCapacidad(Integer caudal) {
        this.mbps += caudal;
        notificarObservadores("Ampliar", caudal);
    }

    public void restringirCapacidad(Integer caudal) {
        if (this.mbps - caudal < -50000) {
            notificarObservadores("Alerta", caudal);
            throw new SobrecargaException("El nodo ya supero la capacidad umbral");
        }
        this.mbps -= caudal;
        notificarObservadores("Restringir", caudal);
    }

    // Notificaciones/Observadores
    public void registrarObservador(IObservadorNodo notif) {
        this.observadores.add(notif);
    }

    public void quitarObservador(IObservadorNodo notif) {
        this.observadores.remove(notif);
    }

    private void notificarObservadores(String tipo, Integer capacidadMbps) {
        this.observadores.stream().forEach(o -> o.reaccionar(this, tipo, capacidadMbps));
    }
}
