# DomiExpress - Justificación de los patrones

**Taller integrador: patrones creacionales**
David Santiago Tellez Melo - 20242020107
Luis Alejandro Ruiz Gomez - 20242020134
## Singleton (`GeneradorConsecutivo`)

En el fragmento hay un `private static int contador = 0` que se repite en cada clase que necesita numerar algo. El problema es que cada clase lleva su propia cuenta, entonces dos partes del programa pueden terminar generando el mismo `PED-0001`. Con el Singleton solo existe un generador en todo el programa y todos le piden el número a él, así que los consecutivos nunca se repiten. Además, como el número se pide dentro de `construir()` después de validar, un pedido rechazado no gasta número. No lo usaríamos en una clase que no necesita ser única ni guarda un estado compartido, por ejemplo `ItemPedido`: ahí tiene que haber muchos objetos distintos y obligar a que exista uno solo no tendría sentido.

## Builder (`Pedido.Builder`)

El constructor del fragmento recibe 8 parámetros seguidos y al leerlo no se sabe qué significa cada uno: el `10` y el `3000` podrían ser cupón y propina o al revés, y si se cambian de orden el programa compila igual. También obliga a mandar `""` para los datos opcionales y no valida nada. Con el Builder cada dato se pone con un método que dice qué es (`conCupon(10)`, `conPropina(3000)`), los opcionales ya tienen su valor por defecto y la validación se hace en un solo lugar, en `construir()`. Si mañana llega un dato nuevo, solo se agrega un método y el código que ya existe sigue funcionando. No lo usaríamos en una clase con pocos datos y todos obligatorios, como `ItemPedido`, que tiene tres: ahí un constructor normal es más corto y se entiende igual.

## Factory Method (`ProcesadorPago`)

En el fragmento los medios de pago están en un `if / else if` que compara textos. Cada medio nuevo obliga a abrir ese bloque y modificarlo, con el riesgo de dañar los que ya funcionaban, y si ese bloque está en varias partes hay que cambiarlo en todas. Con Factory Method el flujo de cobro está escrito una sola vez en `ProcesadorPago` y cada subclase solo decide qué pasarela crea. Por eso para agregar Nequi se escribieron dos archivos nuevos (`ProcesadorNequi` y `PasarelaNequi`) y no se modificó ninguno de los existentes. No lo usaríamos si la empresa solo aceptara un medio de pago y eso no fuera a cambiar: la clase abstracta y las subclases serían código de más y bastaría con crear la pasarela directamente.

## Prototype (reto extra, `Pedido.clonar()`)

Este no sale del fragmento sino del botón "Repetir mi último pedido". Sin el patrón habría que volver a pasar todos los datos del pedido anterior por el Builder. Con `clonar()` el mismo pedido se copia: pide un número nuevo, deja el cupón en 0 y crea una lista de ítems nueva para que agregarle algo al clon no cambie el original. No lo usaríamos cuando el objeto es fácil de crear desde cero o cuando casi todos sus datos van a ser diferentes, porque copiar para después cambiar todo no ahorra nada.
