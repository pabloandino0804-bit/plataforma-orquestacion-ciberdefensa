package ar.edu.unahur.obj2.ciber.operaciones;

import ar.edu.unahur.obj2.ciber.NodoRed;

public class AmpliacionTrafico implements IAccionConf {
    private NodoRed nodo;
    private Integer mbps;

    public AmpliacionTrafico(NodoRed nodoDado, Integer mbps) {
        this.nodo = nodoDado;
        this.mbps = mbps;
    }

    @Override
    public void ejecutar() {
        nodo.ampliarMbps(mbps);
    }

    @Override
    public void undo() {
        nodo.restringirMbps(mbps);
    }
}
