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
        this.loteAcciones.add(accion);
    }

    public void ejecutarEnLote() {
        for (IAccionConf accion : this.loteAcciones) {
            try {
                accion.ejecutar();
            } catch (SobrecargaException e) {
                System.out.print("Operacion en lote fallida" + e.getMessage());
            }
        }
        vaciarLoteAcciones();
    }

    private void vaciarLoteAcciones() {
        this.loteAcciones.clear();
    }
}
