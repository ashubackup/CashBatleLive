package api;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import dbconnection.DbConnection;

import javax.servlet.http.*;


/**
 * Servlet implementation class Loader
 */
public class Loader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static Connection con=null;
    
    public Loader() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		con=DbConnection.getUserCheckDbConn();
		super.init();
	}

}
