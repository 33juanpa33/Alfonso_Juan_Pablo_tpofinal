<%@page import="java.util.Date"%>
<%@page import="logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion de empleados</title>
        <link href="css/empleadoAdmin.css" rel="stylesheet" />
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            String usu = (String) sesion.getAttribute("usuarioActivo");
            if (usu == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Direccion</th>
                        <th>DNI</th>
                        <th>Nacionalidad</th>
                        <th>Celular</th>
                        <th>Email</th>
                        <th>Cargo</th>
                        <th>Sueldo</th>
                        <th>Fecha nacimiento</th>
                        <th>Usuario</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% HttpSession misession = request.getSession();
                    
                    
                    List <Empleado> listaEmpleados = (List) misession.getAttribute("listaEmpleados");
                    for (Empleado emp : listaEmpleados) { %>
                    <tr>
                        <% String nombre = emp.getNombre(); %>
                        <td> <%=nombre %> </td>
                        <% String apellido = emp.getApellido(); %>
                        <td> <%=apellido %> </td>
                        <% String direccion = emp.getDireccion(); %>
                        <td> <%=direccion %> </td>
                        <% String dni = emp.getDni(); %>
                        <td> <%=dni %> </td>
                        <% String nacionalidad = emp.getNacionalidad(); %>
                        <td> <%=nacionalidad %> </td>
                        <% String celular = emp.getCelular(); %>
                        <td> <%=celular %> </td>
                        <% String email = emp.getEmail(); %>
                        <td> <%=email %> </td>
                        <% String cargo = emp.getCargo(); %>
                        <td> <%=cargo %> </td>
                        <% Double sueldo = emp.getSueldo(); %>
                        <td> <%=sueldo %> </td>
                        <% String nacimiento = String.format("%1$td/%1$tm/%1$tY", emp.getFecha_nac()); %>
                        <td> <%=nacimiento %> </td>
                        <% String usuario = emp.getUnUsuario().getNombreUsuario(); %>
                        <td> <%=usuario %> </td>
                        <% Integer idEmpleado = emp.getId_empleado(); %>
                        <% Integer idUsuario = emp.getUnUsuario().getId(); %>
                        <td>
                            <form action="SvModificarEmpleado" method="POST" class="tm-contact-form">
                                <input type="hidden" name="idEmpleado" value="<%=idEmpleado%>">
                                <input type="hidden" name="idUsuario" value="<%=idUsuario%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Modificar</button>
                            </form>
                        </td>
                        <td>
                            <form action="SvEliminarEmpleado" method="POST" class="tm-contact-form">
                                <input type="hidden" name="idEmpleado" value="<%=idEmpleado%>">
                                <input type="hidden" name="idUsuario" value="<%=idUsuario%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
                
            </table>
            <div>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="empleado.jsp">
                Crear Empleado
            </a>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="index.jsp">
                Mi inicio
            </a>
            </div>
        </div>
    <% } %>
    </body>
</html>