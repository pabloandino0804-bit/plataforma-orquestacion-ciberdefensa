package ar.edu.unahur.obj2.ciber;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.ciber.notificacionesAutomaticas.INotificacion;

public class NodoRed {
    private String idNodo;
    private Integer mbps;
    private Integer limite = -50_000;
    private List<INotificacion> observadores = new ArrayList<>();

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

    public void ampliarCapacidad(Integer mbpsDado) {

        this.mbps += mbpsDado;
        notificarObservadores("Ampliar", mbpsDado);
    }

    public void restringirCapacidad(Integer mbpsDado) {
        if (mbps - mbpsDado < limite) {

        }
        this.mbps -= mbpsDado;
        notificarObservadores("Restringir", mbpsDado);
    }

    // Notificaciones/Observadores
    public void registrarObservador(INotificacion notif) {
        this.observadores.add(notif);
    }

    public void quitarObservador(INotificacion notif) {
        this.observadores.remove(notif);
    }

    private void notificarObservadores(String tipo, Integer capacidadMbps) {
        this.observadores.stream().forEach(o -> o.reaccionar(this, tipo, capacidadMbps));
    }
}
