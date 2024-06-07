# Alke Wallet digital (Globewallet)

## Características

- **Inicio de sesión**: Proceso de autenticación para acceder a las cuentas personales..
- **Registro de usuario**: Permite a los nuevos usuarios registrarse en la plataforma para utilizar la aplicación.
- **Agregar cuentas**: Permite agregar tarjetas ficticias de otros bancos para que la aplicación las pueda gestionar.
- **Gestión de fondos**: Capacidad para depositar y retirar fondos.
- **Transferir fondos**: Capacidad para transferir fondos entre distintos usuarios de la cuenta digital.
- **Consulta de saldo**: Visualización del saldo actual.
- **Conversión de saldo**: funcionalidad de convertir el saldo entre CLP y USD.

## Tecnologías Utilizadas

- **Java SE 21**: Lenguaje de programación utilizado para la lógica del servidor.
- **Spring Boot**: Framework para la construcción de aplicaciones Java, utilizado para gestionar la configuración y el despliegue del proyecto.
- **Thymeleaf**: Motor de plantillas para la construcción de vistas web.
- **MySQL**: Sistema de gestión de bases de datos para el almacenamiento de datos.
- **HTML/CSS**: Utilizados para la estructuración y diseño de la interfaz de usuario.
- **Bootstrap**: Framework de CSS para diseños responsivos y estilizados.
- **JavaScript**: Utilizado para añadir interactividad a la interfaz de usuario. Incluye funciones para la manipulación de elementos del DOM, como alternar la gestionar alertas dinámicas.
- **Spring Web**: Módulo de Spring que facilita la creación de aplicaciones web basadas en MVC y RESTful.
- **Spring Data JPA**: Abstracción para trabajar con JPA y bases de datos relacionales, simplificando el acceso a datos.
- **Spring Security:** Framework que proporciona autenticación y autorización para aplicaciones Spring.
- **MySQL Driver**: Conector JDBC que permite la interacción entre Java y la base de datos MySQL.

## Requisitos

Para ejecutar se requiere lo siguiente:

- **Java Development Kit (JDK) versión 21**.
- **Eclipse IDE**: Con soporte para Spring Boot y Maven, utilizado para el desarrollo del proyecto.

## Configuración

1. **Clonar el repositorio**: Descarga o clona el repositorio en tu entorno local.
2. **Configurar la base de datos**:
   - Descargar el script el cual puedes encontrar en src/main/resources/db/dbwallet.sql
   - Importa el script `dbwallet.sql` a tu servidor MySQL para crear las tablas necesarias.
   - Asegúrate de ajustar las credenciales de conexión a la base de datos en el archivo `application.properties`.
3. **Desplegar en el servidor**:
   - Despliega la aplicación con el servidor de Spring Boot.
   - Ejecuta la aplicación utilizando `mvn spring-boot:run` o directamente desde tu IDE.

## Uso

Para comenzar a usar la aplicación, siga estos pasos:

1. **Desplegar la aplicación:** Asegúrese de que la aplicación esté correctamente desplegada y que la base de datos esté conectada.
2. **Crear una cuenta:** Regístrese en la aplicación para crear una cuenta de usuario.
3. **Acceder a las características:** Inicie sesión con su nueva cuenta para utilizar todas las funcionalidades disponibles, como la gestión de fondos, transferencias, consultas y conversiones de saldo.
