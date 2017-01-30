package filmografia.bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import filmografia.beans.BeanError;
import filmografia.beans.BeanPelicula;

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
	
	public ArrayList<BeanPelicula> getPeliculas(HttpServletRequest request) throws SQLException, BeanError{
		BeanPelicula pelicula = null;
		ArrayList<BeanPelicula> listaPeliculas = new ArrayList<BeanPelicula>();
		String director = request.getParameter("director");
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("select director,titulo,fecha from login where director = '" + director + "'");
		if (rs.next()){
			while(rs.next()){
				pelicula = new BeanPelicula (rs.getString("director"),rs.getString("titulo"),rs.getDate("fecha"));
				listaPeliculas.add(pelicula);
			}
		}else{
			throw new BeanError(3, "El director "+director+" no tiene ninguna pelicula registrada en la base de datos");
		}
		if (st!=null){
			close();
		}
		
		return listaPeliculas;
	}
	
}
