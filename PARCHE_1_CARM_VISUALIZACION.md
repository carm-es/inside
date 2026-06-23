# PARCHE 1 CARM - Desactivación Selectiva de Visualización de Expediente

**Fecha:** 2026-06-23  
**Versión:** InSide 2.0.8.1+  
**Ámbito:** Servicio de Visualización (modelo1 = expediente, modelo2 = documento)

---

## 📋 Descripción del Problema

InSide disponía de un único interruptor global `visualizacion.activo` que desactivaba **toda** la visualización (expediente + documentos). No existía forma de desactivar selectivamente solo la visualización de expediente (`modelo1`) mientras se mantiene activa la visualización de documentos (`modelo2`).

**Limitación anterior:**
- `visualizacion.activo=S` → Ambas visualizaciones activas
- `visualizacion.activo=N` → Ambas visualizaciones desactivadas (no hay opción intermedia)

---

## ✅ Solución Implementada

Se añade un nuevo flag de configuración **`visualizacion.expediente.activo`** que permite controlar independientemente la visualización de expediente, sin afectar la visualización de documentos.

**Cambios mínimos:** 3 puntos de edición únicamente.

---

## 🔧 Cambios Técnicos

### 1. Archivo de Configuración
**Ruta:** `resources/config/visualizacion.properties`

```properties
# PARCHE 1 CARM: Flag para desactivar selectivamente visualizacion de expediente (modelo1)
# Mantiene activa la visualizacion de documentos (modelo2)
# Valores: S (activo por defecto) o N (desactivado)
visualizacion.expediente.activo=S
```

- **Nueva propiedad:** `visualizacion.expediente.activo`
- **Valores válidos:** `S` (activo) | `N` (desactivado)
- **Defecto:** `S` (para compatibilidad hacia atrás)

### 2. Clase de Servicio
**Ruta:** `fuentes/inside-services/src/main/java/es/mpt/dsic/inside/service/visualizacion/impl/InsideServiceVisualizacionImpl.java`

#### Campo privado añadido:
```java
// PARCHE 1 CARM: Flag para desactivar selectivamente visualizacion de expediente (modelo1)
// mientras mantiene activa la visualizacion de documentos (modelo2)
private String expedienteActivo;
```

#### Carga en método `configure()`:
```java
// PARCHE 1 CARM: Cargar flag para desactivar selectivamente expediente
expedienteActivo = properties.getProperty("visualizacion.expediente.activo");
if (expedienteActivo == null) {
  expedienteActivo = VALUE_SI; // Compatibilidad: si no existe, asumir activo (S)
}

logger.debug(String.format("Visualizacion de expediente (modelo1): %s",
    expedienteActivo));
```

#### Bloqueo en método `expedienteConVisualizacionIndice()`:
```java
// PARCHE 1 CARM: Bloquear visualizacion de expediente (modelo1) si esta desactivada
// Mantiene activa la visualizacion de documentos (modelo2)
if (VALUE_NO.equalsIgnoreCase(expedienteActivo)) {
  logger.info("Visualizacion de expediente (modelo1) desactivada. "
      + "Devolviendo expediente sin visualizacion de indice. "
      + "Identificador expediente: " + expediente.getIdentificador());
  return expediente;
}
```

---

## 📊 Matriz de Comportamiento

| `visualizacion.activo` | `visualizacion.expediente.activo` | Expediente (modelo1) | Documentos (modelo2) | Comportamiento |
|------------------------|-----------------------------------|----------------------|----------------------|---|
| `S` | `S` | ✅ Activo | ✅ Activo | Ambas visualizaciones operativas |
| `S` | `N` | ❌ Desactivado | ✅ Activo | **PARCHE 1: Solo documentos** |
| `S` | no existe | ✅ Activo | ✅ Activo | Compatibilidad (asume `S`) |
| `N` | `S` | ❌ Bloqueado | ❌ Bloqueado | Visualización global desactivada |
| `N` | `N` | ❌ Bloqueado | ❌ Bloqueado | Visualización global desactivada |
| (legacy sin propiedad nueva) | - | ✅ Activo | ✅ Activo | Comportamiento original intacto |

---

## 🎯 Casos de Uso

### Caso 1: Desactivar solo índice de expediente
**Objetivo:** Reduce llamadas al WS externo para expediente, mantiene visualización de documento.

```properties
visualizacion.activo=S
visualizacion.expediente.activo=N
```

**Resultado:**
- ❌ No se genera visualización de índice de expediente
- ✅ Visualización de documentos sigue operativa
- ✅ Las operaciones `getExpedienteEniFile(...)` devuelven expediente sin `VisualizacionIndice`

### Caso 2: Desactivación global completa
**Objetivo:** Desactivar completamente la visualización (estado anterior).

```properties
visualizacion.activo=N
```

**Resultado:**
- ❌ Ambas visualizaciones desactivadas
- ⚠️ El flag `visualizacion.expediente.activo` se ignora cuando `visualizacion.activo=N`

### Caso 3: Compatibilidad (sin cambios en config)
**Objetivo:** Despliegue en entorno legacy sin modificar `visualizacion.properties`.

```properties
visualizacion.activo=S
# visualizacion.expediente.activo no definida
```

**Resultado:**
- ✅ Comportamiento original: ambas visualizaciones activas
- ✅ No hay ruptura de compatibilidad

---

## 📝 Trazabilidad en Logs

El parche registra operaciones clave en logs para auditoría:

```
[DEBUG] Visualizacion de expediente (modelo1): N
[INFO] Visualizacion de expediente (modelo1) desactivada. Devolviendo expediente sin visualizacion de indice. Identificador expediente: EXP-2026-001
```

---

## ⚠️ Consideraciones Importantes

### 1. Impacto en Operaciones de Descarga
Si se desactiva `modelo1`, las operaciones que dependan explícitamente de `VisualizacionIndice` pueden requerir ajustes:
- `getExpedienteEniFile(..., visualizacionIndice=true, ...)` → Devolverá `VisualizacionIndice = null`
- Clientes deben tolerar este campo vacío/nulo

### 2. Llamadas al WS Externo (eeutils)
- Con `expedienteActivo=N`, se **evita completamente** la llamada a `eeutil-vis-docexp` para expediente
- Reduce latencia y dependencia del WS externo
- Mejora robustez ante caídas del servicio de visualización

### 3. Compatibilidad Hacia Atrás
- Si `visualizacion.expediente.activo` no está definida, el sistema asume `S` (activo)
- Despliegues existentes sin cambios en config mantienen comportamiento original
- **No requiere cambios obligatorios en configuración legacy**

---

## 🧪 Plan de Pruebas Recomendado

### Pruebas Funcionales

#### Test 1: Desactivación selectiva
```gherkin
Given visualizacion.activo = S
And visualizacion.expediente.activo = N
When se invoca expedienteConVisualizacionIndice()
Then el expediente se devuelve sin VisualizacionIndice
And se registra log de desactivación
And NO se invoca al WS eeutil-vis-docexp
```

#### Test 2: Documentos siguen activos
```gherkin
Given visualizacion.expediente.activo = N
When se invoca visualizacionDocumento()
Then se genera visualización de documento normalmente
And se invoca al WS eeutil-vis-docexp para modelo2
```

#### Test 3: Compatibilidad legacy
```gherkin
Given visualizacion.properties sin visualizacion.expediente.activo
When se carga el servicio
Then expedienteActivo = S (por defecto)
And comportamiento idéntico a v2.0.8.0
```

### Pruebas de Regresión
- ✅ Con `expedienteActivo=S`: Validar comportamiento idéntico a versión previa
- ✅ Sin propiedad nueva: Validar comportamiento idéntico a versión previa
- ✅ Operaciones de PDF de expediente: Verificar manejo de `VisualizacionIndice = null`

---

## 📦 Despliegue

### Pasos de Actualización

1. **Actualizar código:** Aplicar cambios en `InsideServiceVisualizacionImpl.java`
2. **Actualizar configuración:** Añadir línea en `visualizacion.properties`:
   ```properties
   visualizacion.expediente.activo=S
   ```
3. **Compilar:** `mvn clean install` (o según proceso local)
4. **Reiniciar Tomcat:** `stop-tomcat-delfos.cmd` + `start-tomcat-delfos.cmd`

### Rollback
- Revertir cambios en código
- Eliminar propiedad `visualizacion.expediente.activo` de config (o dejar con valor `S`)
- Reiniciar Tomcat

---

## 📚 Referencias

- **Informe técnico original:** `INFORME_DESACTIVACION_MODELO1_VISUALIZACION_2026-06-22 (1).md`
- **Servicio principal:** `InsideServiceVisualizacionImpl.java`
- **Propiedades:** `resources/config/visualizacion.properties`
- **WS externo:** `eeutil-vis-docexp` (sin cambios requeridos)

---

## ✨ Resumen de Impacto

| Aspecto | Descripción |
|--------|-------------|
| **Complejidad** | ⭐ Mínima - Solo 3 cambios localizados |
| **Riesgo** | 🟢 Bajo - Compatible hacia atrás |
| **Beneficio** | 📉 Reduce latencia y carga en WS de visualización |
| **Documentación** | ✅ Inline (comentarios PARCHE 1 CARM) |
| **Testing** | ✅ Recomendado según plan anterior |

---

**Fin del PARCHE 1 CARM**
