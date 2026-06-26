**Plataforma de Orquestación de Ciberdefensa (POC)**

Una infraestructura crítica de defensa necesita implementar un sistema que permita registrar distintas acciones de configuración de tráfico sobre nodos de red y ejecutarlas posteriormente. Además, el sistema deberá evolucionar para que otros componentes de seguridad puedan enterarse automáticamente cuando ocurran variaciones de capacidad en un nodo.

**👨‍🏫 Primera Parte - Operaciones de Orquestación**

**📋 Requerimientos**

**🖥️ Nodo de Red**

De cada nodo de red se conoce:

* Identificador del Nodo
* Capacidad de Tráfico Asignada (Mbps)

Un nodo de red debe permitir:

* Consultar su capacidad actual.
* Ampliar capacidad de tráfico.
* Restringir capacidad de tráfico.

**⚙️ Acciones de Configuración**

El sistema debe soportar inicialmente las siguientes acciones:

* Ampliación de Tráfico
* Restricción de Tráfico

Cada acción conoce:

* El nodo de red sobre el que actúa.
* El caudal (Mbps) involucrado.

**📌 Ejemplo**

* Ampliar $3000 Mbps en el nodo "1234"
* Restringir $5000 Mbps en el nodo "1234"

**📋 Orquestador de Acciones**

La plataforma cuenta con un componente responsable de administrar las acciones, pudiendo ejecutar acciones individuales o pendientes organizadas por lote. Este componente debe permitir:

* Ejecutar una acción individual.
* Registrar acciones en un lote.
* Ejecutar todas las acciones registradas en el lote.
* Vaciar el listado de acciones pendientes luego de ejecutarlas.

**📌 Ejemplo de uso**

Dado un nodo con capacidad inicial de **10,000 Mbps**:

Si se registran las siguientes acciones individuales:

1. Ampliar 70,000 Mbps
2. Restringir 5,000 Mbps
3. Restringir 16,000 Mbps
4. Ampliar 9,000 Mbps

Al ejecutarlas, la capacidad asignada debería evolucionar de la siguiente manera:

* Capacidad inicial: 10,000 Mbps
* Ampliar 70,000 -> Capacidad: 80,000 Mbps
* Restringir 5,000 -> Capacidad: 75,000 Mbps
* Restringir 16,000 -> Capacidad: 59,000 Mbps
* Ampliar 9,000 -> Capacidad: 68,000 Mbps

Si durante la noche, por ejemplo, se registran las siguientes acciones por lote:

1. Restringir 5,000 Mbps
2. Ampliar 300,000 Mbps
3. Restringir 50,000 Mbps

Al ejecutarlas, la capacidad de tráfico debería evolucionar así:

* Capacidad inicial: 68,000 Mbps
* Restringir 5,000 -> Capacidad: 63,000 Mbps
* Ampliar 300,000 -> Capacidad: 363,000 Mbps
* Restringir 50,000 -> Capacidad: 313,000 Mbps

**¿Qué pasa si queremos deshacer una acción?**

* ¿Es posible revertir una acción ejecutada?
* ¿Cómo lo podemos implementar?
* ¿De quién es la responsabilidad de saber revertir el impacto de una acción en el nodo?

**⚙️ Restricciones de Diseño (Primera Parte)**

* El orquestador de acciones no debe conocer cómo se implementa cada acción interna.
* Debe ser posible incorporar nuevos tipos de acciones de red sin modificar el código del orquestador.
* Las responsabilidades deben quedar correctamente distribuidas entre objetos.
* Evitar soluciones basadas en condicionales (if / switch) para distinguir tipos de acciones.
* Las acciones deben poder almacenarse y ejecutarse por lotes en un momento posterior.
* Cada acción debe ser capaz de deshacer su propia transformación sobre el nodo.

**👨‍🏫 Segunda Parte - Notificaciones Automáticas**

La plataforma desea incorporar nuevos sistemas periféricos que reaccionen automáticamente cuando un nodo de red experimente variaciones de tráfico. Para este escenario se requiere:

1. Un **Sistema de Auditoría Central (SIEM)** que registre las acciones de todos los nodos.
2. **Alertas al Administrador del Nodo**, enviadas cada vez que ocurra un cambio de tráfico en su nodo asignado.

**📢 Observadores de Nodos**

Cada vez que un nodo reciba una ampliación o restricción, los sistemas interesados deben ser informados automáticamente. El nodo debe permitir:

* Registrar observadores.
* Eliminar observadores.
* Notificar a todos los observadores registrados cuando ocurra un movimiento de tráfico.

**🔍 Auditoría SIEM**

El sistema de auditoría registra todas las alteraciones realizadas sobre los nodos de red.

**📌 Ejemplo**

Si se realiza: *Ampliar 3000 Mbps en el nodo 1234*, el SIEM debería recibir información suficiente para registrar y guardar el evento de red.

**📱 Alertas al Administrador**

Informa al responsable técnico cada vez que ocurre una variación de caudal en su nodo específico.

**📌 Ejemplo**

* "Se acreditaron 3000 Mbps de ancho de banda en su nodo."
* "Se debitaron 5000 Mbps de ancho de banda de su nodo."

**🚨 Alertas de Sobrecarga Crítica**

Se desea incorporar un sistema que detecte cuándo un nodo queda por debajo del umbral de tolerancia estándar (que es cero Mbps libres).

* La alerta solo "reacciona" si la operación fue exitosa y la capacidad quedó en valores negativos.
* Se admite un modo de sobrecarga (*burst/overdraft*) de hasta **-50,000 Mbps**. No se permite realizar restricciones que fuercen al nodo a quedar con menos de ese límite absoluto de emergencia.

**📌 Ejemplo**

Si luego de una restricción la capacidad residual queda en **-750 Mbps**, el sistema debe disparar automáticamente una alerta de desbordamiento crítico.

**⚙️ Restricciones de Diseño (Segunda Parte)**

* El nodo de red no debe conocer los detalles de implementación de los sistemas externos (SIEM, Alertas, etc.).
* Debe ser posible incorporar nuevos tipos de observadores sin modificar la clase estructural del Nodo.
* Un mismo cambio de caudal puede generar múltiples alertas concurrentes en diferentes sistemas.
* Los observadores deben reaccionar de manera autónoma e inmediata al cambiar el estado del nodo.
* Evitar soluciones basadas en condicionales (if / switch) para identificar o filtrar tipos de observadores.