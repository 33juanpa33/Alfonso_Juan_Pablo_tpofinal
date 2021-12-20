package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Servicio;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvEliminarServicio", urlPatterns = {"/SvEliminarServicio"})
public class SvEliminarServicio extends HttpServlet {

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
        Servicio serv = control.buscarServicio(codigo);
        serv.setDisponible(false);
        control.modificarServicio(serv);
        
        // actualizo la lista de servicios
        request.getSession().setAttribute("listaServicios", control.traerServicios());
        response.sendRedirect("servicioAdmin.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
