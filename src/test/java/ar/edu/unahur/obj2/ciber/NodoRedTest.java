package ar.edu.unahur.obj2.ciber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.ciber.notificacionesAutomaticas.AlertaSobrecarga;
import ar.edu.unahur.obj2.ciber.notificacionesAutomaticas.AlertasAdministrador;
import ar.edu.unahur.obj2.ciber.notificacionesAutomaticas.AuditoriaSIEM;

public class NodoRedTest {
    // Tests Parte 1
    @Test
    void dadoUnNodoNuevo_cuandoConsultoCapacidad_entoncesDevuelveLaInicial() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);

        Integer capacidad = nodo.getCapacidadActual();

        assertEquals(10000, capacidad);
    }

    @Test
    void dadoUnNodo_cuandoAmplioTrafico_entoncesAumentaSuCapacidad() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);

        nodo.ampliarCapacidad(3000);

        assertEquals(13000, nodo.getCapacidadActual());
    }

    @Test
    void dadoUnNodo_cuandoRestrinjoTrafico_entoncesDisminuyeSuCapacidad() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);

        nodo.restringirCapacidad(5000);

        assertEquals(5000, nodo.getCapacidadActual());
    }

    // Parte 2
    @Test
    void dadoUnObservadorAuditoria_cuandoEjecutaUnaFuncion_elSistemaRegistraLaAlteracionDelNodo() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        AuditoriaSIEM auditoriaSIEM = new AuditoriaSIEM();
        nodo.registrarObservador(auditoriaSIEM);

        nodo.ampliarCapacidad(3000);

        nodo.restringirCapacidad(2000);

        assertEquals(nodo.getCapacidadActual(), 11000);
    }

    @Test
    void dadoUnObservadorAlertaAdministrador_cuandoEjecutaUnaFuncion_elSistemaRegistraLaAlteracionDelNodo() {
        NodoRed nodo = new NodoRed("NODO-1", 10000);
        AlertasAdministrador alertasAdministrador = new AlertasAdministrador();
        AlertaSobrecarga alertaSobrecarga = new AlertaSobrecarga();
        nodo.registrarObservador(alertaSobrecarga);
        nodo.registrarObservador(alertasAdministrador);

        nodo.ampliarCapacidad(3000);

        nodo.restringirCapacidad(2000);

        assertEquals(nodo.getCapacidadActual(), 11000);
    }

    @Test
    void dadoUnNodo_cuandoIntentaRestringirTraficoSuperaAlLimiteDado_LanzaraUnaAlerta() {
        // Given
        NodoRed nodo = new NodoRed("NODO-1", -750);
        AlertasAdministrador alertasAdministrador = new AlertasAdministrador();
        AlertaSobrecarga alertaSobrecarga = new AlertaSobrecarga();
        nodo.registrarObservador(alertaSobrecarga);
        nodo.registrarObservador(alertasAdministrador);

        assertThrows(SobrecargaException.class, () -> nodo.restringirCapacidad(60000));
    }
}
