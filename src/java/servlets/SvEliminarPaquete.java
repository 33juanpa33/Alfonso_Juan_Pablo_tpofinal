package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Paquete_turistico;
import logica.Servicio;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvEliminarPaquete", urlPatterns = {"/SvEliminarPaquete"})
public class SvEliminarPaquete extends HttpServlet {

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
        
        // traigo el codigo
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));
        
        Controladora control = new Controladora();
        Paquete_turistico paq = control.buscarPaquete(codigo);
        paq.setDisponible(false);
        control.modificarPaquete(paq);
        
        // actualizo la lista de paquetes turisticos
        request.getSession().setAttribute("listaPaquetes", control.traerPaquetes());
        response.sendRedirect("paqueteAdmin.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
