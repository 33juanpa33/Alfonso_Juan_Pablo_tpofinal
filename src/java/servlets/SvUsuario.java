package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("u");
        String contras = request.getParameter("p");
        
        Controladora control = new Controladora();
        boolean autorizado = control.verificarUsuario(usuario,contras);
        
        if (autorizado == true) {
            // obtengo la sesion y le asigno el usuario y contraseña para validarlo
            HttpSession misession = request.getSession(true);
            misession.setAttribute("usuarioActivo",usuario);
            misession.setAttribute("contrasActiva",contras);
            misession.setAttribute("listaEmpleados", control.traerEmpleados());
            misession.setAttribute("listaClientes", control.traerClientes());
            misession.setAttribute("listaServicios", control.traerServicios());
            misession.setAttribute("listaPaquetes", control.traerPaquetes());
            misession.setAttribute("listaVentas", control.traerVentas());
             
            response.sendRedirect("index.jsp");
        }
        else {
            response.sendRedirect("login.jsp");
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
