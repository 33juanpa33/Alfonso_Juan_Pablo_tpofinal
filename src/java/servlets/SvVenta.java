package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.Controladora;
import logica.Empleado;
import logica.Paquete_turistico;
import logica.Servicio;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvVenta", urlPatterns = {"/SvVenta"})
public class SvVenta extends HttpServlet {
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
        List<Empleado> listaEmp = (List) misession.getAttribute("listaEmpleados");
        List<Cliente> listaCli = (List) misession.getAttribute("listaClientes");
        List<Servicio> listaServ = (List) misession.getAttribute("listaServicios");
        List<Paquete_turistico> listaPaq = (List) misession.getAttribute("listaPaquetes");
        Empleado emple = new Empleado();
        Cliente cli = new Cliente();
        Servicio serv = new Servicio();
        Paquete_turistico paque = new Paquete_turistico();
        
        String pago = request.getParameter("pago");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formato.parse(request.getParameter("fecha"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer idEmpleado = Integer.parseInt( request.getParameter("empleado"));
        Integer idCliente = Integer.parseInt( request.getParameter("cliente"));
        Integer codigo = Integer.parseInt( request.getParameter("codigo"));
        
        for (Empleado emp : listaEmp) {
            if (idEmpleado.equals(emp.getId_empleado())) {
                emple = emp;
                break;
            }
        }
        
        for (Cliente cl : listaCli) {
            if (idCliente.equals(cl.getId_cliente())) {
                cli = cl;
                break;
            }
        }
        
        for (Servicio ser : listaServ) {
            if (codigo.equals(ser.getCodigo())) {
                serv = ser;
                break;
            }
        }
        
        for (Paquete_turistico paq : listaPaq) {
            if (codigo.equals(paq.getCodigo())) {
                paque = paq;
                break;
            }
        }
        
        control.crearVenta(pago, fecha, emple, cli, serv, paque);
        request.getSession().setAttribute("listaVentas", control.traerVentas());
        response.sendRedirect("index.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
