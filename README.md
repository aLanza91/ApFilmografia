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
