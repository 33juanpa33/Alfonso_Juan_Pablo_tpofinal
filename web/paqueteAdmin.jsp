<%@page import="logica.Paquete_turistico"%>
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
                        <th>Codigo</th>
                        <th>Servicios</th>
                        <th>Costo</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% HttpSession misession = request.getSession();

                        List<Paquete_turistico> listaPaq = (List) misession.getAttribute("listaPaquetes");
                        for (Paquete_turistico paq : listaPaq) { %>
                    <tr>
                        <% Integer codigo = paq.getCodigo();%>
                        <td> <%=codigo%> </td>
                        <% List<Servicio> servicios = paq.getListaServicios(); %>
                        <td><% for (Servicio ser : servicios) {
                                String fecha = String.format("%1$td/%1$tm/%1$tY", ser.getFecha());%>
                            * <%=ser.getNombre() + " - " + ser.getDescripcion() + " - " + fecha%> <br>
                            <% } %></td>
                            <% Double costo = paq.getCosto_paquete();%>
                        <td> <%=costo%> </td>
                        <td>
                            <form action="SvModificarPaquete" method="POST" class="tm-contact-form">
                                <input type="hidden" name="codigo" value="<%=codigo%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Modificar</button>
                            </form>
                        </td>
                        <td>
                            <form action="SvEliminarPaquete" method="POST" class="tm-contact-form">
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
                    Crear Paquete
                </a>
                <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="index.jsp">
                    Mi inicio
                </a>
            </div>
        </div>
    <% } %>
    </body>
</html>