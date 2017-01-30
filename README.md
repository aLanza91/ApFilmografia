# ApFilmografia
Ejercicio Consulta Directores Cine.

Context root: filmografia.

SQL de la base de tatos en la raíz del proyecto.

DATASOURCE:

            <datasource jta="true" jndi-name="java:jboss/datasources/dscine" pool-name="dscine" enabled="true" use-java-context="true" use-ccm="true">
                <connection-url>jdbc:mysql://localhost:3306/cine</connection-url>
                <driver>mysql5</driver>
                <security>
                    <user-name>root</user-name>
                    <password>mysql</password>
                </security>
            </datasource>

Errores actuales: 1. Esta URL no soporta metodo POST
           
                         
            Exception handling request to /filmografia/consultar: java.lang.NullPointerException
	at org.jboss.as.naming.InitialContext.getURLScheme(InitialContext.java:160)
	at org.jboss.as.naming.InitialContext.getURLOrDefaultInitCtx(InitialContext.java:128)
	at javax.naming.InitialContext.lookup(InitialContext.java:417)
	at javax.naming.InitialContext.lookup(InitialContext.java:417)
	at filmografia.servlet.Consulta.init(Consulta.java:45)                         
