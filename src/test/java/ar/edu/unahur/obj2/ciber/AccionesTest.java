package ar.edu.unahur.obj2.ciber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.ciber.operaciones.AmpliacionTrafico;
import ar.edu.unahur.obj2.ciber.operaciones.RestriccionTrafico;

public class AccionesTest {
    @Test
    void dadaUnaAmpliacion_cuandoSeEjecuta_entoncesAumentaLaCapacidadDelNodo() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        AmpliacionTrafico ampliacion = new AmpliacionTrafico(nodo, 3000);

        // When
        ampliacion.ejecutar();

        // Then
        assertEquals(13000, nodo.getCapacidadActual());
    }

    @Test
    void dadaUnaAmpliacion_cuandoSeDeshace_entoncesVuelveAlEstadoOriginal() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        AmpliacionTrafico ampliacion = new AmpliacionTrafico(nodo, 3000);
        ampliacion.ejecutar(); // 13000

        // When
        ampliacion.undo();

        // Then
        assertEquals(10000, nodo.getCapacidadActual());
    }

    @Test
    void dadaUnaRestriccion_cuandoSeEjecuta_entoncesReduceLaCapacidadDelNodo() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        RestriccionTrafico restriccion = new RestriccionTrafico(nodo, 3000);

        // When
        restriccion.ejecutar();

        // Then
        assertEquals(7000, nodo.getCapacidadActual());
    }

    @Test
    void dadaUnaRestriccion_cuandoSeDeshace_entoncesVuelveAlEstadoOriginal() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        RestriccionTrafico restriccion = new RestriccionTrafico(nodo, 3000);
        restriccion.ejecutar(); // 7000

        // When
        restriccion.undo();

        // Then
        assertEquals(10000, nodo.getCapacidadActual());
    }
}
