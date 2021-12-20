package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Servicio;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvModificarServicio", urlPatterns = {"/SvModificarServicio"})
public class SvModificarServicio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // traigo los datos
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String destino = request.getParameter("destino");
        Double costo = Double.parseDouble(request.getParameter("costo"));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formato.parse(request.getParameter("fecha"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Controladora control = new Controladora();
        Servicio serv = control.buscarServicio(codigo);
        serv.setNombre(nombre);
        serv.setDescripcion(descripcion);
        serv.setDestino(destino);
        serv.setCosto(costo);
        serv.setFecha(fecha);
        
        control.modificarServicio(serv);
        // actualizo la lista de servicios
        request.getSession().setAttribute("listaServicios", control.traerServicios());
        response.sendRedirect("servicioAdmin.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // traigo el codigo
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));
        
        Controladora control = new Controladora();
        Servicio serv = control.buscarServicio(codigo);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("servicio", serv);
        response.sendRedirect("modificarServicio.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
