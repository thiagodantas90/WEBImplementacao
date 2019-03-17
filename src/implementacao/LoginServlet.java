package implementacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import modelos.Usuarios;
import persistencia.UsuariosDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UsuariosDAO usuario = new UsuariosDAO(); 
		
		String email = request.getParameter("email");
		String pass = request.getParameter("senha");
		out.print(email);
		out.print(pass);
		
		Usuarios cliente = new Usuarios();
			
		cliente = usuario.consultarUsuario(email);
	
		//System.out.printf(cliente.getNome(), cliente.getEmail());
		
		if(cliente==null||!cliente.getSenha().equals(pass)) {
			request.getSession().setAttribute("msg", "E-mail ou senha invalidos");
			//response.sendRedirect("index.jsp");
			
		}else{
			request.getSession().setAttribute(email, cliente);
			response.sendRedirect("ProdutosLista.jsp");
		}
	
		out.close();
	}

}
