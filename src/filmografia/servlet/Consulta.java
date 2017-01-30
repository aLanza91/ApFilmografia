package filmografia.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import filmografia.bbdd.BeanDao;
import filmografia.beans.BeanError;
import filmografia.beans.BeanPelicula;


@SuppressWarnings("serial")
@WebServlet(name="Consulta",urlPatterns="/consultar")
public class Consulta extends HttpServlet{
	
	private DataSource dscine;
	private boolean appOperativa = true;
	private BeanDao beanDao;
	
	
	public Consulta(){
		super();
	}
	
	//dirección datasource, si funciona bien transladar al web.xml -> java:jboss/datasources/dscine
	
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Comienzo de init del servlet Consulta");
    	super.init(config);
    	ServletContext application = config.getServletContext();
    	//String urlDataSource = (String) application.getInitParameter("URLDataSource");
    	//String urlDataSource = (String) application.getInitParameter("java:jboss/datasources/dscine");
    	InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dscine = (DataSource) initCtx.lookup("java:jboss/datasources/dscine");
			System.out.println("DataSource CONECTADO");
		} catch (NamingException e) {
			appOperativa = false;
    	}
		beanDao = new BeanDao(dscine);
		System.out.println("Bendao INICIALIZADO con DataSource dscine");
	}
	
	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BeanPelicula pelicula = null;
		BeanError error = null;
		ArrayList<BeanPelicula> listaPeliculas = new ArrayList<BeanPelicula>();
		//Comprueba si la aplicación puede funcionar.
		if (!appOperativa){
			error = new BeanError(0,"La aplicación no se encuentra operativa en este momento, intentelo más tarde.");
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		}else{
			try {
				String director = request.getParameter("director");
				beanDao.getConexion();
				listaPeliculas = beanDao.getPeliculas(director);
				beanDao.close();
				System.out.println("Conexion con beanDao y realizado metodo getPeliculas");
			} catch (SQLException e) {
				error = new BeanError(1,"Error en conexión a base de datos");
			} catch (BeanError e){
				error = e;
			}
		}
		
		if(error==null){
			request.setAttribute("error", error);
			request.setAttribute("listaPeliculas", listaPeliculas);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/res.jsp");
		    rd.forward(request,response);		
		}else {
			request.setAttribute("error", error);
			request.setAttribute("listaPeliculas",new ArrayList<BeanPelicula>());
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		}
		
	}
	
}
