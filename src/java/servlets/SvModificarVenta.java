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
import logica.Venta;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvModificarVenta", urlPatterns = {"/SvModificarVenta"})
public class SvModificarVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        
        // traigo los datos
        Integer numero = Integer.parseInt(request.getParameter("numeroVenta"));
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
        
        Controladora control = new Controladora();
        Venta ventaServicio = control.buscarVenta(numero);
        Venta ventaPaquete = control.buscarVenta(numero);
        
        if (serv.getCodigo()==null) {
            ventaPaquete.setMedio_pago(pago);
            ventaPaquete.setFecha_venta(fecha);
            ventaPaquete.setUnEmpleado(emple);
            ventaPaquete.setUnCliente(cli);
            ventaPaquete.setUnServicio(null);
            ventaPaquete.setUnPaquete(paque);
            control.modificarVenta(ventaPaquete);
        }
        
        if (paque.getCodigo()==null) {
            ventaServicio.setMedio_pago(pago);
            ventaServicio.setFecha_venta(fecha);
            ventaServicio.setUnEmpleado(emple);
            ventaServicio.setUnCliente(cli);
            ventaServicio.setUnServicio(serv);
            ventaServicio.setUnPaquete(null);
            control.modificarVenta(ventaServicio);
        }
        
        // actualizo la lista de ventas
        request.getSession().setAttribute("listaVentas", control.traerVentas());
        response.sendRedirect("ventaAdmin.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // traigo el numero de venta
        Integer numeroVenta = Integer.parseInt(request.getParameter("numero"));
        
        Controladora control = new Controladora();
        Venta venta = control.buscarVenta(numeroVenta);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("venta", venta);
        response.sendRedirect("modificarVenta.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
