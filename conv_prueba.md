# Conversación con LLM – Pruebas unitarias (JUnit)

Fecha: 2026-XX-XX  
Asignatura: ESPE  
Parte: C – Pruebas Unitarias  

---

**Usuario:**  
Necesito confeccionar pruebas unitarias con JUnit para la clase `File`.  
Los métodos relevantes son `addProperty(char[] content)` y `getCRC32()`.  
¿Puedes proponer una estrategia de pruebas y casos relevantes?

---

**LLM:**  
Sí. Para probar correctamente esta clase se recomienda:
1. Verificar que `addProperty`:
   - Establece el tipo del fichero como `PROPERTY`.
   - Almacena correctamente el contenido recibido en la estructura interna.
2. Verificar que `getCRC32`:
   - Delegue el cálculo en `FileUtils`.
   - Permita controlar el valor esperado si `FileUtils` ofrece un mecanismo de configuración (ideal para testeo).
3. Mantener un `smokeTest` (si existe) para comprobar que el entorno de pruebas está configurado correctamente.

---

**Usuario:**  
El proyecto ya incluye un `smokeTest()` vacío en `FileTest`. ¿Lo mantengo?

---

**LLM:**  
Sí. Mantenerlo es correcto: no interfiere y documenta que el entorno de test compila y ejecuta.

---

**Usuario:**  
¿Qué test propones para `addProperty`?

---

**LLM:**  
Un test que:
- Cree un `File`.
- Ejecute `addProperty(new char[]{'a','b','c'})`.
- Compruebe:
  - `getType()` devuelve `FileType.PROPERTY`.
  - El tamaño de `getContent()` es 3.
  - Los elementos almacenados en la lista coinciden con `'a'`, `'b'`, `'c'`.

Código del test `addProperty_guardaContenidoYMarcaTipoProperty`.

---

**Usuario:**  
¿Y para `getCRC32()` si `FileUtils` permite fijar el resultado?

---

**LLM:**  
En ese caso se recomienda inyectar un `FileUtils` en el `File` para controlar el CRC esperado:
- Crear un `FileUtils` y fijar un valor con `setCRC(12345L)`.
- Crear el `File` utilizando un constructor que reciba `FileUtils`.
- Añadir contenido con `addProperty(...)` y verificar que `getCRC32()` devuelve exactamente `12345L`.

Código del test `getCRC32_devuelveValorConfiguradoEnFileUtils`.

---

**Usuario:**  
¿Tiene sentido probar el caso de contenido vacío?

---

**LLM:**  
Sí. Es un caso límite importante:
- Configurar `FileUtils` con un CRC conocido (por ejemplo `999L`).
- Crear un `File` con ese `FileUtils` sin añadir contenido.
- Verificar que `getCRC32()` devuelve el valor configurado.

Código del test `getCRC32_conContenidoVacio_devuelveCRCDeFileUtils`.

---

**Usuario:**  
Perfecto. Implementaré los tests en `FileTest.java` manteniendo el `smokeTest` y añadiendo los tres casos anteriores.

---

**LLM:**  
Correcto. Esta batería de pruebas cubre comportamiento nominal y un caso límite, y además valida la delegación a `FileUtils` de forma determinista.
