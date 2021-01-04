# practica2-SAD

### EJECUCIÓN

Para probar la aplicación se tiene que ejecutar el servidor
y los clientes por separado. Los clientes se deben ejecutar en
modo paralelo en tu entorno de programación (puedes ejecutar
todos los que quieras a la vez).

Un ejemplo de ejecución sería:

1) Hacer un run de la clase Server
2) Hacer un run de la clase Client e introducir primer nombre
3) Hacer otro run de la clase Client e introducir segundo nombre
4) Escribir entre clientes

*Si introduces un nombre que ya se esté usando automáticamente
se te pondrá un número al final del nombre para distinguiros*

_Formato de mensaje al iniciar el cliente:_

    [CLIENT] Please introduce your nick
    > campo_para_introucir_nickname

_Formato de mensaje al unirse al chat:_

    [CLIENT] Hello tu_nickname! Now you are talking with everyone :)
    ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
    Connected at dd-MM-yyyy HH:mm:ss

_Formato de mensaje enviado:_

    HH:mm << [ME]: "mensaje_enviado"

_Formato de mensaje recibido:_

    HH:mm >> [nickname_cliente]: "mensaje_recibido"



