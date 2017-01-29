package filmografia.bbdd;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class BeanDao {
	
	/**
	 * Información de la base de datos
	 */
	private DataSource dscine;

	/**
	 * Representa la conexión con la base de datos
	 */
	private Connection conexion = null;

	/**
	 * Constructor que recibe el DataSource
	 * @param dsBdValidaLogin
	 */
	public BeanDao(DataSource dscine) {
		this.dscine = dscine;
	}

	/**
	 * Proceso de conexión con la base de datos
	 * @return
	 * @throws SQLException
	 */
	public Connection getConexion() throws SQLException {
		conexion = dscine.getConnection();
		return conexion;
	}

	/**
	 * Proceso que cierra la conexión con la base de datos
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
		conexion = null;
	}	
	
}
