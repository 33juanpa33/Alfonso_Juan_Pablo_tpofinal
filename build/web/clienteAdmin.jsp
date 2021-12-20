<%@page import="logica.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion de clientes</title>
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
                        <th>Fecha nacimiento</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% HttpSession misession = request.getSession();                    
                    
                    List <Cliente> listaClientes = (List) misession.getAttribute("listaClientes");
                    for (Cliente cli : listaClientes) { %>
                    <tr>
                        <% String nombre = cli.getNombre(); %>
                        <td> <%=nombre %> </td>
                        <% String apellido = cli.getApellido(); %>
                        <td> <%=apellido %> </td>
                        <% String direccion = cli.getDireccion(); %>
                        <td> <%=direccion %> </td>
                        <% String dni = cli.getDni(); %>
                        <td> <%=dni %> </td>
                        <% String nacionalidad = cli.getNacionalidad(); %>
                        <td> <%=nacionalidad %> </td>
                        <% String celular = cli.getCelular(); %>
                        <td> <%=celular %> </td>
                        <% String email = cli.getEmail(); %>
                        <td> <%=email %> </td>
                        <% String nacimiento = String.format("%1$td/%1$tm/%1$tY", cli.getFecha_nac()); %>
                        <td> <%=nacimiento %> </td>
                        <% Integer idCliente = cli.getId_cliente(); %>
                        <td>
                            <form action="SvModificarCliente" method="POST" class="tm-contact-form">
                                <input type="hidden" name="idCliente" value="<%=idCliente%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Modificar</button>
                            </form>
                        </td>
                        <td>
                            <form action="SvEliminarCliente" method="POST" class="tm-contact-form">
                                <input type="hidden" name="idCliente" value="<%=idCliente%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
                
            </table>
            <div>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="cliente.jsp">
                Crear Cliente
            </a>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="index.jsp">
                Mi inicio
            </a>
            </div>
        </div>
    <% } %>
    </body>
</html>