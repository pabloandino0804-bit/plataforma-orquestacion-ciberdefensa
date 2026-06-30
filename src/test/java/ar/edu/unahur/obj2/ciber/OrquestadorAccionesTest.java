package ar.edu.unahur.obj2.ciber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.ciber.operaciones.AmpliacionTrafico;
import ar.edu.unahur.obj2.ciber.operaciones.RestriccionTrafico;

public class OrquestadorAccionesTest {
    @Test
    void dadoUnOrquestadorDeAcciones_AlEjecutarUnaAccionIndividualLaMismadDebeTenerlaEnSuLista() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        AmpliacionTrafico ampliacion = new AmpliacionTrafico(nodo, 3000);
        RestriccionTrafico restriccion = new RestriccionTrafico(nodo, 5000);
        OrquestadorAcciones orq = new OrquestadorAcciones();
        orq.registrarAccion(restriccion);
        orq.registrarAccion(ampliacion);

        orq.ejecutarAccion(restriccion);

        assertEquals(5000, nodo.getCapacidadActual());

    }

    @Test
    void dadoUnOrquestadorDeAcciones_alEjecutarSuProcesoEnLote_ejecutaTodasLasAccionesQueTieneEnSuListaLuegoBorraTOdasLasAccionesDeLaMisma() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        AmpliacionTrafico ampliacion = new AmpliacionTrafico(nodo, 3000);
        RestriccionTrafico restriccion = new RestriccionTrafico(nodo, 5000);
        OrquestadorAcciones orq = new OrquestadorAcciones();
        orq.registrarAccion(restriccion);
        orq.registrarAccion(ampliacion);

        orq.ejecutarEnLote();

        assertEquals(8000, nodo.getCapacidadActual());
    }

    @Test
    void dadoUnOrquestadorDeAcciones_siUnaAccionFallaNoAlteraLaCapacidadDelNodoDado() {
        NodoRed nodo = new NodoRed("NODO-1", -750);
        AmpliacionTrafico ampliacion = new AmpliacionTrafico(nodo, 3000);
        RestriccionTrafico restriccion = new RestriccionTrafico(nodo, 60000);
        OrquestadorAcciones orq = new OrquestadorAcciones();
        orq.registrarAccion(restriccion);
        orq.registrarAccion(ampliacion);

        orq.ejecutarEnLote();

        assertEquals(2250, nodo.getCapacidadActual());
    }
}
