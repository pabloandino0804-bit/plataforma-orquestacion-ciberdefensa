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
        this.nodo.ampliarCapacidad(mbps);
    }

    @Override
    public void undo() {
        this.nodo.restringirCapacidad(mbps);
    }
}
