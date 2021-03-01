package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectBD;
import Model.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static int tentatives = 3; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		try {
			ConnectBD cnx  = new ConnectBD();
			if ((request.getParameter("login")!=null) && (request.getParameter("password")!=null))
			{
			String login= request.getParameter("login");
			String password= request.getParameter("password");
			User e=cnx.getUser(login, password);
			if(e!=null)
			    out.println("Bonjour"+ e.getLogin());
			else
			{ if(tentatives ==0)
			        {
				  out.println("Compte bloqué, merci de contacter l'administrateur admin@nomdomaine.ma");
			       
			        }
			       else
			        {
			    	tentatives -=1;
			        out.println("Erreur, il vous reste "+tentatives +"tentatives");
			        out.println("<script type=\"text/javascript\">");
			        out.println("alert('Erreur, il vous reste "+tentatives +" tentatives');");
			        out.println("location='index.html';");
			        out.println("</script>");
			        
			        }
			}
			}
			
		} catch (SQLException e1) {
		    out.println(e1.getMessage());
			e1.printStackTrace();
		}
	
		
	}

}
