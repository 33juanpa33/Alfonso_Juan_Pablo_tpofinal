<%@page import="logica.Servicio"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion de Servicios</title>
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
                        <th>Descripción</th>
                        <th>Destino</th>
                        <th>Costo</th>
                        <th>Fecha</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% HttpSession misession = request.getSession();                    
                    
                    List <Servicio> listaServ = (List) misession.getAttribute("listaServicios");
                    for (Servicio serv : listaServ) { %>
                    <tr>
                        <% String nombre = serv.getNombre(); %>
                        <td> <%=nombre %> </td>
                        <% String descripcion = serv.getDescripcion(); %>
                        <td> <%=descripcion %> </td>
                        <% String destino = serv.getDestino(); %>
                        <td> <%=destino %> </td>
                        <% Double costo = serv.getCosto(); %>
                        <td> <%=costo %> </td>
                        <% String fecha = String.format("%1$td/%1$tm/%1$tY", serv.getFecha()); %>
                        <td> <%=fecha %> </td>
                        <% Integer codigo = serv.getCodigo(); %>
                        <td>
                            <form action="SvModificarServicio" method="POST" class="tm-contact-form">
                                <input type="hidden" name="codigo" value="<%=codigo%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Modificar</button>
                            </form>
                        </td>
                        <td>
                            <form action="SvEliminarServicio" method="POST" class="tm-contact-form">
                                <input type="hidden" name="codigo" value="<%=codigo%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
                
            </table>
            <div>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="servicio.jsp">
                Crear Servicio
            </a>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="index.jsp">
                Mi inicio
            </a>
            </div>
        </div>
    <% } %>
    </body>
</html>