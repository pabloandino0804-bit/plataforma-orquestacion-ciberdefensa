package ar.edu.unahur.obj2.ciber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NodoRedTest {
    // Tests Parte 1
    @Test
    void dadoUnNodoNuevo_cuandoConsultoCapacidad_entoncesDevuelveLaInicial() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        // When
        Integer capacidad = nodo.getCapacidadActual();
        // Then
        assertEquals(10000, capacidad);
    }

    @Test
    void dadoUnNodo_cuandoAmplioTrafico_entoncesAumentaSuCapacidad() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        // When
        nodo.ampliarCapacidad(3000);
        // Then
        assertEquals(13000, nodo.getCapacidadActual());
    }

    @Test
    void dadoUnNodo_cuandoRestrinjoTrafico_entoncesDisminuyeSuCapacidad() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        // When
        nodo.restringirCapacidad(5000);
        // Then
        assertEquals(5000, nodo.getCapacidadActual());
    }
}
