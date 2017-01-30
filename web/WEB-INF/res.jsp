<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*, java.util.*"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Peliculas de el director: <%=request.getParameter("director")%></h1>
<%ArrayList<BeanPelicula> listaPeliculas= (ArrayList<BeanPelicula>) request.getAttribute("listaPeliculas");
	if (listaPeliculas!=null){
		for(BeanPelicula p:listaPeliculas){
			out.write("Título: "+p.getTitulo()+", fecha: "+p.getFecha()+"<br>");	
		}
	}
 %>
</body>
</html>