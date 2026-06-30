package ar.edu.unahur.obj2.ciber.operaciones;

import java.util.ArrayList;
import java.util.List;

public class AccionLote implements IAccionConf {
    List<IAccionConf> loteAcciones;

    public AccionLote() {
        this.loteAcciones = new ArrayList<IAccionConf>();
    }

    public void registrarAccion(IAccionConf accion) {
        this.loteAcciones.add(accion);
    }

    @Override
    public void ejecutar() {
        this.loteAcciones.stream().forEach(accion -> accion.ejecutar());
    }

    @Override
    public void undo() {
        this.loteAcciones.stream().forEach(accion -> accion.undo());
    }

}