FUNCIONAMIENTO DEL APP:

1)SE DEBE   CORRER CON WILD FLY EL PROYECTO EN ECLIPSE

2)SE DEBE ABRIR POSTMAN  Y MANDAR LA SIGUIENTE URL:


http://localhost:8080/VideoAndes/rest/Administrador

SE RESIVIRÁ AL ADMINISTRADOR DE TODO ROTONDANDES

3) SI SE DESEA CONOCER A LOS ADMINISTRADORES DE LOS RESTAURANTES DE ROTONADNDES
   SE DEBE INTRODUCIR LA SIGUIENTE URL :

http://localhost:8080/VideoAndes/rest/Administrador/AdmiRestaurante

O SI SE DESEAN CONOCER LOS USUSARIOS ACTIVOS , MANDE LA SIGUIENTE URL

http://localhost:8080/VideoAndes/rest/Administrador/AdmiRestaurante

4)AHORA PARA PODER CREAR UN ADMIRESTAURANTE , SE DEBE MANDAR EL SIGUIENTE JSON

{
	
	"nombre":"santiaguini",
	
		"correo":"santi.fff@gjo.com",
	
		"cedula":13,

		"restaurante":2
}

5) EN TERMINOS DEL CLIENTE (UNA VEZ REGISTRADOS) , SE PUEDE MANDAR LA SUIGUENTE URL 
PARA PODER OBTENER LOS RESTAURANTES , LOS MENUS O LOS PRODUCTOS QUE ESTÁN DIVIDIDOS EN VARIAS CATEGORÍAS



http://localhost:8080/VideoAndes/rest/Clientes/Bebidas

note que las palabra bebidas puede ser cambiada por cualquier otro producto:
menu, postre, entrada, platoFuerte




6)  PARA VER LOS PEDIDOS DESDE UN CLIENTE SE  ENVÍA:

http://localhost:8080/VideoAndes/rest/Clientes/Pedidos

7) PARA CREAR UN PEDIDO
CON EL MISMO URL

{
        	"id": 3,
    
    		"fecha": "1997/03/12",
    
    		"nombre_Rest": "el restaurante",
    
    		"id_prod": 1,
       
 		"id_cliente": 12
    } 