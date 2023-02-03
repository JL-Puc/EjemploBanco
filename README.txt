### Historial Proyecto Banco

        ##Inicio
El proyecto en un inicio consistió en poder crear un programa el cual sirva para ingresar clientes de un banco con sus cuentas. 
El almacén de datos comenzó con un txt en el cual pudimos insertar usuarios con sus 4 datos(idCliente, nombreCLiente, idCuenta, numeroCuenta) separados con comas. No puede existir un usuario sin ninguna cuenta.

Para ello creamos las clases de Clientes, Cuentas y el DaoFichero el cual se encargaría de poder dar acceso al archivo.txt donde leería a los clientes ya registrados. 

Posteriormente se nos pide validar los datos de los Clientes y las Cuentas para ello creamos las Excepciones: ExcepcionCliente y ExcepcionCuenta.

Nuestra validación la hacemos desde cada clase que la necesita, es decir si queremos validar el nombre de un cliente entonces en un setNombre() hemos puesto como validar si es correcto o no el nombre.
Asi con cada apartado para validar: nombre del cliente(debe comenzar con Mayuscula, solo acepta una nombre), idCliente(debe ser un numero con exactamente 5 digitos), idCuenta(el numero de cuenta debe contener 16 digitos) y por último el saldo(Apesar de que validamos que el saldo sea valido con una ExcepcionCuenta, en Java existe el NumberFormatException para ello) de la cuenta. 

La forma de agregar a un cliente es añadir una linea con los datos en el archivo.txt. 

Ahora debemos imprimir a todos los clientes, repitiendo el idCliente solo una vez, seguidamente de su nombre solo una vez y debajo todas las cuentas que tiene ese cliente.

Nuestra forma para manejar al archivo es recorrer el archivo y encontrar las coincidencias del cliente o lo que se pide. 

        ## Implementar borrarCliente, borrarCuenta y modificarSaldo sin sobreescribir el archivo;
Es en este punto cuando cuestionamos nuestra manera de estar manejando nuestros archivo.

Hasta ahora nuestro agregar cliente no sobreescribe el archivo, solemente añadimos esa nueva linea, pero no sabemos como borrar algo del archivo.
Podemos sustituir lo que no queremos por alguna marca especial como: ********* o podemos solo dejar un espacio entre las comas como: ", ,"
Podemos borrar toda una linea pero el archivo quedaría chimuelo pues tendría saltos de línea. Podemos crear una nueva columna el cual sea "activo"(1) para un cliente que esté en existencia y un "desactivo"(0) para un cliente que ha sido borrado. 

El problema es que si queremos borrar algo, no tendría porque seguir en el archivo pues ya no lo necesitamos; dejas el archivo chimuelo puede ser una opción pero no parece la más redituable.

Se decide implementar la SERIALIZACION de manera que podamos serializar a un cliente con todas sus cuentas en un archivo el cual lleve el nombre del ID del cliente por serializar y con terminación ".txt
".

Tenemos que borrar nuestra lógica del DAO pues ya no nos servirá.

Implementamos dos clases nuevas: ListaDeClientes y ListaDeCuentas, pensando que nos ayudará en la manipulación de los clientes y las cuentas.

Sin tener muy en claro "no sobreescribir" decidimos utilizar la clase ListaDeClientes para poder cargar de una vez a todos los clientes registrados, poder maninipularlos y sobreescribir de nuevo todos los archivos que se necesiten. Aquí tenemos claramente un error pues sobreescribimos y además cargamos todos los clientes. 

Lo que se busca es poder tener a un objeto que queramos en el momento que lo queramos. ¿Para qué queremos cargar a todos los clientes si solo modificaremos a uno?

Hasta este punto hemos implementado la serialización, agregando clientes, modificando clientes, sus cuentas y sus saldos (con retiro y depósito);

