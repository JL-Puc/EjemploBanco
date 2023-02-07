# Prueba de desempeño

## Requisitos
El proyecto en un inicio consistió en poder crear un programa el cual sirva para ingresar clientes de un banco con sus respectivas cuentas. 
El almacén de datos comenzó con un txt en el cual pudimos insertar a los clientes con sus 4 datos(ID del cliente, nombre del cliente, número de cuenta, saldo) separados con comas. 
No podría existir un usuario sin ninguna cuenta.
El ID del cliente debía ser único, compuesto con una cantidad exacta de 16 dígitos.
El número de cuenta debía ser único con respecto a las cuentas del usuario y compuesto por exactamente 16 dígitos.
___

## Descripción

Como primer acción sobre el proceso del diseño abstraímos al cliente y al objeto cuenta con sus datos explicitos dados, encapsulándolos en sus respectivas clases con sus atributos privados para que no sean susceptibles a cambios. Dicha abstracción ha surgido por la diferencia entre las características de los objetos aun así tienen cierta relación pues la(s) cuenta(s) pertenecen a los clientes y sin cliente no hay cuenta(s). La primera operación requerida, ingresar clientes, nos llegaba como una abstracción procedimental pues detrás de dicha acción se valida antes que los datos de un cliente sean correctos. 

![UMLClases](/Images/UML_Clases.jpg)

Existe un controlador para la cuenta el cual se encarga de manejar los cambios de una cuenta del cliente, validar la inserción de una nueva cuenta, validar el depósito o retiro del saldo. Tenemos el otro controlador para el cliente el cual tiene la función de validar los datos del cliente para poder ser agregado, borrado o modificado (las operaciones del CRUD). Los controladores han sido implementados para poder enfocarse en la lógica de control, las "reglas de negocio" y solamente a ello siguiendo el patrón de diseño MVC (aun sin la implementación de interfaces de usuario) para hacer una "separación de preocupaciones". Los controladores se encuentran modularizados teniendo la capacidad de crear las instancias de los modelos despues de la validación de los datos.

![MVC](/Images/DAO.jpg)

Despues se encuentran los DAO's, creados con la finalidad de seguir el patrón DAO (Data Access Object) y poder separar la lógica del negocio de la lógica para acceder a los datos. En ellos se encuentran las operaciones del CRUD (insertar, actualizar, borrar y consultar) y es quien se encarga de tener tener el acceso a la base de datos completando así la base del programa el cual tendría como DB archivos txt que serían comunicados directamente con el DAO, el controlador se encargaría de validar los datos enviados al DAO con los modelos correspondientes y porsupuesto las excepciones para el manejo de acciones o datos fuera de lo que corresponde la funcionalidad del programa. 

![DAO](/Images/MVC.jpg)

El acoplamiento sobre el diseño considero que no se ha logrado implementar de una buena manera debido al tardío del entendimiento en la codificación. Es claro verlo es en la clase -ListaDeClientes- donde se tiene el método para poder cargar a los clientes en esa misma clase. Donde hago una comunicación del DAO con dicha clase, cuando no debería ser así pues mientras menos conexiones se encuentren tendriamos un acoplamiento débil que es lo que se busca;
~~~~ java
    public void cargarClientes( ) throws FileNotFoundException, ExcepcionCliente{
        DaoFichero daoFichero = new DaoFichero();
        listaClientes = daoFichero.traerClientes();
    }
~~~~
___

## Tres conceptos generales de diseño

El manejo de los datos se había pensando por medio de archivos txt pero para poder hacer una consulta más rapida y eficiente se implementó la serialización. Pensando en descomposición, que hace referencia a "divide y vencerás" y separación de preocupaciones, se decidió serializar a un objeto cliente con solo su ID y su nombre dentro de un archivo txt y aparte serializar sus cuentas en otro txt con el objetivo de poder consultar unicamente la información necesitada. Cada archivo creado tanto del cliente como sus cuentas tendría como nombre el ID del cliente. Lo que crearía una busqueda más fácil del dato necesitado, además de no tener que leer todos los datos totales del banco.

![Serialización](/Images/Serializaci%C3%B3n.png)


El concepto de modularización el cual es definido por Pierre Bourque (SWEBOK V3.0) como: El software grande se divide en varios componentes más pequeños con nombres que tienen interfaces bien definidas que describen las interacciones de los componentes. En mi implementación se encuentra de forma explícita en la separación de la lógica de negocio de la lógica para acceder a los datos. Cada uno de estos componentes (controlador y dao) se enfocan en diferentes funcionalidades pero interaccionan por un bien común: agregar a un cliente válido. Por ejemplo, el controlador se encarga de validar los datos ingresados del cliente y el DAO solamente de acceder al "DB".

![Modularización](/Images/Modularizaci%C3%B3n.jpg) 
~~~~java
    //Validar datos
    public void agregarClienteValido(String nombreCliente,  String idCliente, String idCuenta, String saldo ) throws ExcepcionCliente, ExcepcionCuenta, IOException {

        if( validarDatosCliente(nombreCliente, idCliente, idCuenta, saldo)) {
            
            if(verificarIdClienteExistencia(idCliente) == false) { //Verificar el ID del cliente, si es falso significa que el nombre y el ID no coincide con alguna existencia.

                    controladorCuenta.agregarCuentaNueva(idCliente, idCuenta, saldo);

                    Cliente cliente = new Cliente(idCliente, nombreCliente);

                    daoFichero.agregarCliente(cliente);
   
            }
     
        }
    }
~~~~
El concepto de ocultación de información y encapsulamiento definido por Richar E. (SWEBOK V3.0) como: significa agrupar y empaquetar los detalles internos de una abstracción y hacer que esos detalles sean inaccesibles para las entidades externas. La ocultación  de información es aplicada en mi implementación con la ayuda de la técnica del encapsulamiento de clases pues el programa se ha elaborado con un lenguaje orientado a objetos (Java) y cada objeto es representado con un encapsulamiento (su clase), igualmente es presenta la descomposición al abstraer las clases de cliente y cuentas, y la composición en el momento de asignarles los atributos que llevarán cada clase. 

___

## Actividad de diseño 

La toma de decisiones y resolución de problemas ha sido una actividad del diseño para poder implementar la serialización. Para la operación -borrar cliente- del CRUD parecía haber cierta deficiencia con el manejo del archivo txt. Por ello al haber planteado otros diseños de solución(dejar chimuelo el txt, manejar una columna de activo/inactivo) e investigando otras formas de implementación, se optó por uso de la serialización para el manejo rápido de los datos. 



## Elemento del diseño

Entre los elementos del diseño, en mi criterio, encontré una restricción. Dicho concepto es definido por Zhu,H.(2005): 

"Algunas restricciones son descubiertas y/o introducidas por el diseñador durante el curso del diseño. Las restricciones se dividen en dos dominios, externo e interno. Las restricciones externas son impuestas por los factores que no están bajo el control del diseñador, mientras que las restricciones internas le dan al diseñador al menos cierta capacidad para controlarlas"

En el programa se dio a conocer en el momento que la DB fue impuesta con la manera de manejar los datos, pidiendo no sobreescribir toda la DB. La restricción es puede ser clasificada como externa pues como se mencionó, fue impuesta por un tercero y no por propia elección.

___
## Error de diseño

Parnas clasifica los errores de diseño en 4 tipo, el que se ha encontrado en el diseño implementado es la inferioridad, pues presenta una pequeña ineficiencia sobre el manejo de mi DB, por ejemplo para cambiar a otra base de datos tendríamos que hacer varios cambios sobre el actual el cual está pensando para utilizar la serialización. Otro ejemplo es al hacer consulta de una cuenta, a pesar de que las cuentas han sido separadas de los clientes para el manejo de datos, la lista de cuentas de un cliente son serialidas en conjunto (la lista de un cliente en un solo archivo) entonces cuando se desea hacer la modificación de una cuenta, se deben traer toda la lista del cliente de nuevo para encontrar la cuenta, hacer la actualización y de nuevo "reescribir" toda la lista y desde mi punto de vista estas dos situaciones crean inflexibilidad.   

## Implementar funcionalidad al diseño de implementación:

### Generar los reportes de lista de clientes en PDF:

Para poder implementar esta funcionalidad, debemos de crear otro paquete en nuestro proyecto Java el cual se encargaría de los reportes en archivos de los clientes. 
En este nuevo paquete se crearía un clase llamada reportesClientes donde tendría comunicación con nuestros DAO's. 
Para la generación del PDF en Java, se debe implementar una librería llamada -itextpdf-.
La nueva clase debe tener el método para generar PDF, el cual consistirá en comunicarse con el daoFichero(almacena a los clientes) y el daoCuentas para hacer la asignación cuentas al cliente. Crear el documento con la nueva librería y escribir los datos de todos los registros existentes. Se puede crear otra carpeta donde serán almacenados los reportes.

#### Dificultad de la implementación:

Investigando sobre librerías que permitieran crear el archivo pdf, me encontré con la librería iText, se intentó la implementación pero no se logró entender el porque creaba archivos pdf's que no se podían leer. Despues la librería utilizada "jPDFWritter" nunca antes la había usado y el sitio web no tenía una buena explicación a mi parecer de la documentación de dicha librería. 

Existían dos formar para poder crear el PDF, una haciendo un "dibujo" y plasmarlo en el PDF, el otro convertir un archivo txt directamente en un pdf.

Opté por el segundo, para ello apliqué lo antes mencionado. Cree un paquete llamado "reportes" donde se encuentran 3 clases: GenerarTxt el cual sirve para crear el archivo txt que despues será hecho pdf, TextPrinter el cual ayuda a crear un texto que implemente la interfaz "Printable" y por último el ConvertidorPDF que toma al TextPrinter y genera el PDF.

Lo que ha costado en esta implementación fue el usar la nueva librería y la desición de las formas para crear el PDF (antes mencionadas), pues la comunicación de los datos de los clientes era fácil de obtener con los DAO's.

### Mecanismo de seguridad vía encripción de datos para la DB

Crear un mecanismo de seguridad a grandes rasgos debe ser parte o función de otro componente. Debido al desconocimiento del tema, me he dado la tarea de investigar sobre clases o librerías que implementen esta función en Java. Existen API's como JCA (Java Cryptography Architecture ) y JCE (Java Cryptography Extension) que añaden facilidades de encriptación y desencriptación de datos. 
Por lo visto, será necesario crear una clase el cual se encargue de encriptar y desincriptar lo que le pidamos, para este caso podríamos pensar en dos modos: 
- Encriptar/desencriptar los datos de los modelos para ser serializado.
- Encriptar/desencriptar el objeto completo para luego ser serializado.

Sin importar cual de los dos casos sea, los objetivos serán crear un nuevo modulo que se encargue del mecanismo, este modulo solo necesitaría del DAO pues antes de ser serializado debería de ser encriptado, además ayudaría a no necesitar de muchos cambios para su implementación.

#### Dificultad de la implementación:

Para la implementación, la dificultad se ha encontrado de nueva cuenta en la fatal de conocimiento de esta área, sin embargo se ha logrado la funcionalidad aunque por lo antes mencionado no tendría la certeza de haber creado la funcionalidad efectiva. Como en el diseño en el párrafo antes mencionado, se ha creado un paquete llamado "seguridad" donde se encuentra una clase que se encarga del encriptado y desencriptado, se estuvo buscando la manera de encriptar un objeto completo y nos encontramos con la clase SealedObject el cual encripta un objeto que tenga la propiedad de ser serializable así que en nuestro proyecto nos vino muy bien.

Despues de crear la clase, fue fácil la implementación pues solo debimos hacer una agregación de la clase -Encriptador- en la clase -SerializarObjeto- para que se serializó sea encriptado y despues guardado en el archivo, que es diferente a lo que habíamos pensado (pensamos que primero se encriptaría y despues serializaba), lo mismo para poder deserializarlo. 
Así es como se logró pocos cambios para el mecanismo pedido. Sin haber tocado los DAO's, controladores o modelos existentes.






