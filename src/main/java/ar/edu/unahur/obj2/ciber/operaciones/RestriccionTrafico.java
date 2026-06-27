package ar.edu.unahur.obj2.ciber.operaciones;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class RestriccionTrafico implements IAccionConf {
    private NodoRed nodo;
    private Integer mbps;

    public RestriccionTrafico(NodoRed nodoDado, Integer mbps) {
        this.nodo = nodoDado;
        this.mbps = mbps;
    }

    public void ejecutar() {
        nodo.restringirMbps(mbps);
    }

    public void undo() {
        nodo.ampliarMbps(mbps);
    }

}
