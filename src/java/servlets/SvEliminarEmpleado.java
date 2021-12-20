package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvEliminarEmpleado", urlPatterns = {"/SvEliminarEmpleado"})
public class SvEliminarEmpleado extends HttpServlet {

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
        
        
        
        // traigo los id
        Integer idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        
        Controladora control = new Controladora();
        control.borrarEmpleado(idEmpleado, idUsuario);
        
        // actualizo la lista de empleados
        request.getSession().setAttribute("listaEmpleados", control.traerEmpleados());
        response.sendRedirect("empleadoAdmin.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
