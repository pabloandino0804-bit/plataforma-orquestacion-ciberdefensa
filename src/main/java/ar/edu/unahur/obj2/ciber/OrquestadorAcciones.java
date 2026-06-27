package ar.edu.unahur.obj2.ciber;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.ciber.operaciones.IAccionConf;

public class OrquestadorAcciones {
    private List<IAccionConf> loteAcciones = new ArrayList<>();

    public void ejecutarAccion(IAccionConf accion) {
        if (loteAcciones.contains(accion))
            accion.ejecutar();
    }

    public void registrarAccion(IAccionConf accion) {
        loteAcciones.add(accion);
    }

    public void ejecutarEnLote() {
        for (IAccionConf accion : loteAcciones) {
            accion.ejecutar();
        }
        vaciarLoteAcciones();
    }

    private void vaciarLoteAcciones() {
        loteAcciones.clear();
    }
}
