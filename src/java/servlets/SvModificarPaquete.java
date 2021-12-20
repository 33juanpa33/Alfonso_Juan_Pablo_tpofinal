package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Paquete_turistico;
import logica.Servicio;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvModificarPaquete", urlPatterns = {"/SvModificarPaquete"})
public class SvModificarPaquete extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // traigo los datos
        Integer codigoPaq = Integer.parseInt(request.getParameter("codigoPaq"));
        Controladora control = new Controladora();
        Paquete_turistico paqueteAModif = control.buscarPaquete(codigoPaq);
        HttpSession misession = request.getSession();
        List<Servicio> listaServ = (List) misession.getAttribute("listaServicios");
        List<Servicio> listaServEnPaquete = new ArrayList();
        Double costo = 0.0;

        for (Object o : request.getParameterValues("codigo")) {
            for (Servicio serv : listaServ) {
                if (o.equals(serv.getCodigo().toString())) {
                    listaServEnPaquete.add(serv);
                    costo += serv.getCosto();
                }}}
        
        paqueteAModif.setListaServicios(listaServEnPaquete);
        paqueteAModif.setCosto_paquete(costo*0.9);
        control.modificarPaquete(paqueteAModif);       
        
        
        // actualizo la lista de servicios
        request.getSession().setAttribute("listaPaquetes", control.traerPaquetes());
        response.sendRedirect("paqueteAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // traigo el id
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));
        
        Controladora control = new Controladora();
        Paquete_turistico paq = control.buscarPaquete(codigo);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("paquete", paq);
        response.sendRedirect("modificarPaquete.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
