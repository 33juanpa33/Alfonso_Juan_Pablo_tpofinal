package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "SvPaquete", urlPatterns = {"/SvPaquete"})
public class SvPaquete extends HttpServlet {
    Controladora control = new Controladora();

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

        HttpSession misession = request.getSession();
        List<Servicio> listaServ = (List) misession.getAttribute("listaServicios");
        List<Servicio> listaServEnPaquete = new ArrayList();
        Double costo = 0.0;

        for (Object o : request.getParameterValues("codigo")) {
            for (Servicio serv : listaServ) {
                if (o.equals(serv.getCodigo().toString())) {
                    listaServEnPaquete.add(serv);
                    costo += serv.getCosto();
                }
            }
        }
        Boolean disponible = true;
        control.crearPaquete(listaServEnPaquete, costo, disponible);
        request.getSession().setAttribute("listaPaquetes", control.traerPaquetes());
        response.sendRedirect("index.jsp");
    }

@Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
