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
import logica.Empleado;
import logica.Usuario;
/**
 *
 * @author Alfonso
 */
@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // traigo los datos
        Integer id = Integer.parseInt(request.getParameter("idEmpleado"));
        Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");
        Double sueldo= Double.parseDouble(request.getParameter("sueldo"));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNac = null;
        try {
            fechaNac = formato.parse(request.getParameter("fechaNac"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }        
        String nombreUsu = request.getParameter("nombreUsu");
        String contrasenia = request.getParameter("contrasenia");
        
        Controladora control = new Controladora();
        Empleado emple = control.buscarEmpleado(id);
        Usuario usu = control.buscarUsuario(idUsuario);
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setDni(dni);
        emple.setNacionalidad(nacionalidad);
        emple.setCelular(celular);
        emple.setFecha_nac(fechaNac);
        emple.setEmail(email);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        usu.setNombreUsuario(nombreUsu);
        usu.setContrasenia(contrasenia);
        
        control.modificarEmpleado(emple, usu);
        // actualizo la lista de personas
        request.getSession().setAttribute("listaEmpleados", control.traerEmpleados());
        response.sendRedirect("empleadoAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // traigo los id
        Integer idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        
        Controladora control = new Controladora();
        Empleado emple = control.buscarEmpleado(idEmpleado);
        Usuario usu = control.buscarUsuario(idUsuario);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("empleado", emple);
        misession.setAttribute("usuario", usu);
        response.sendRedirect("modifEmpleYUsu.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
