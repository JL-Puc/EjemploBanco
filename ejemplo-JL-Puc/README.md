# Prueba de desempeño

## Requisitos
El proyecto en un inicio consistió en poder crear un programa el cual sirva para ingresar clientes de un banco con sus respectivas cuentas. 
El almacén de datos comenzó con un txt en el cual pudimos insertar a los clientes con sus 4 datos(ID del cliente, nombre del cliente, número de cuenta, saldo) separados con comas. 
No podría existir un usuario sin ninguna cuenta.
El ID del cliente debía ser único, compuesto con una cantidad exacta de 16 dígitos.
El número de cuenta debía ser único con respecto a las cuentas del usuario y compuesto por exactamente 16 dígitos.
___

## Descripción

Como primer acción sobre el diseño abstraímos al cliente y al objeto cuenta con sus datos explicitos dados, creando sus respectivas clases con sus atributos privados para que no sean susceptibles a cambios. La primera operación requerida, ingresar clientes, nos llegaba como una abstracción procedimental pues detrás de dicha acción se valida antes que los datos de un cliente sean correctos. 

Existe un controlador para la cuenta el cual se encarga de manejar los cambios de una cuenta del cliente, validar la inserción de una nueva cuenta, validar el depósito o retiro del saldo. Tenemos el otro controlador para el cliente el cual tiene la función de validar los datos del cliente para poder ser agregado, borrado o modificado. Los controladores han sido implementados para poder enfocarse en la lógica de control, las "reglas de negocio" y solamente a ello siguiendo el patrón de diseño MVC (aun sin la implementación de interfaces de usuario) para hacer una "separación de preocupaciones".

Despues se encuentran los DAO's, creados con la finalidad de seguir el patrón DAO y poder separar la lógica del negocio de la lógica para acceder a los datos. En ellos se encuentran las operaciones del CRUD (insertar, actualizar, borrar y consultar).

___

## Tres conceptos generales de diseño

El manejo de los datos se había pensando por medio de archivos txt pero para poder hacer una consulta más rapida y eficiente se implementó la serialización. Pensando en descomposición, que hace referencia a "divide y vencerás" y separación de preocupaciones, se decidió serializar a un objeto cliente con solo su ID y su nombre dentro de un archivo txt y aparte serializar sus cuentas en otro txt con el objetivo de poder consultar unicamente la información necesitada en el momento que sea necesitado.

El concepto de modularización el cual es definido por Pierre Bourque (SWEBOK V3.0) como: El software grande se divide en varios componentes más pequeños con nombres que tienen interfaces bien definidas que describen las interacciones de los componentes. En mi implementación se encuentra de forma explícita en la separación de la lógica de negocio de la lógica para acceder a los datos. Cada uno de estos componentes (controlador y dao) se enfocan en diferentes funcionalidades pero interaccionan por un bien común: agregar a un cliente válido.

El concepto de ocultación de información y encapsulamiento definido por Richar E. (SWEBOK V3.0) como: significa agrupar y empaquetar los detalles internos de una abstracción y hacer que esos detalles sean inaccesibles para las entidades externas. La ocultación  de información es aplicada en mi implementación con la ayuda de la técnica del encapsulamiento de clases pues el programa se ha elaborado con un lenguaje orientado a objetos (Java) y cada objeto es representado con un encapsulamiento (su clase), igualmente es presenta la descomposición al abstraer las clases de cliente y cuentas, y la composición en el momento de asignarles los atributos que llevarán cada clase. 


## Actividad de diseño 

La toma de decisiones y resolución de problemas ha sido una actividad del diseño para poder implementar la serialización. Para la operación -borrar cliente- del CRUD parecía haber cierta deficiencia con el manejo del archivo txt. Por ello al haber planteado otros diseños de solución e investigado otras formas de implementación, se optó por usar la serialización para el manejo rápido de los datos.

## Elemento del diseño

Un elemento con baja estructuración ha sido la declaración del problema de diseño y objetivos, 
no se tuvo un buen análisis y mucho menos un buen diseño, no sé sabía cual era el objetivo del programa y a lo largo de su creación se estuvo codificando y diseñando, explicitamente podemos verlo en la clase -ListaDeClientes- el cual al principio fue pensado para poder cargar a todos los clientes de la base de datos. Otro de ellos fue la restricción del tiempo, las fechas de cada entrega sobre una nueva funcionalidad no permitió el análisis requerido para su gestión.

## Error de diseño

Un error encontrado en el diseño implementado es la inferioridad, pues presenta ineficiencia cuando se desea hacer hacer consulta de una cuenta, a pesar de que las cuentas han sido separadas de los clientes para el manejo de datos, la lista de cuentas de un cliente son serialidas en conjunto (la lista de un cliente en un solo archivo) entonces cuando se desea hacer la modificación de una cuenta, se deben traer todas de nuevo para encontrar la cuenta, hacer la actualización y denuevo "reescribir" toda la lista.  

## Implementar funcionalidad al diseño de implementación:

### Generar los reportes de lista de clientes en PDF

Para poder implementar esta funcionalidad, debemos de crear otro paquete en nuestro proyecto Java el cual se encargaría de los reportes en archivos de los clientes. 
En este nuevo paquete se crearía un clase llamada reportesClientes donde tendría comunicación con nuestros DAO's. 
Para la generación del PDF en Java, se debe implementar una librería llamada -itextpdf-.
La nueva clase debe tener el método para generar PDF, el cual consistirá en comunicarse con el daoFichero(almacena a los clientes) y el daoCuentas para hacer la asignación cuentas al cliente. Crear el documento con la nueva librería y escribir los datos de todos los registros existentes. Se puede crear otra carpeta donde serán almacenados los reportes.

#### Dificultad de la implementación:

Investigando sobre librerías que permitieran crear el archivo pdf, me encontré con la librería iText, se intentó la implementación pero no se logró entender el porque creaba archivos pdf's que no se podían leer. Despues la librería utilizada "jPDFWritter" nunca antes la había usado y el sitio web no tenía una buena explicación a mi parecer de la documentación de dicha librería. 

Existían dos formar para poder crear el PDF, una haciendo un "dibujo" y plasmarlo en el PDF, el otro convertir un archivo txt directamente en un pdf.

Opté por el segundo, para ello apliqué lo antes mencionado. Cree un paquete llamado "reportes" donde se encuentran 3 clases: GenerarTxt el cual sirve para crear el archivo txt que despues será hecho pdf, TextPrinter el cual ayuda a crear un texto que implemente la interfaz "Printable" y por último el ConvertidorPDF que toma al TextPrinter y genera el PDF.

Lo que ha costado en esa implementación fue el usar la nueva librería, pues la comunicación de los datos de los clientes era fácil de obtener con los DAO's.

### Mecanismo de seguridad vía encripción de datos para la DB








