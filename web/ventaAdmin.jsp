<%@page import="logica.Empleado"%>
<%@page import="logica.Venta"%>
<%@page import="logica.Paquete_turistico"%>
<%@page import="logica.Servicio"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion de Ventas</title>
        <link href="css/empleadoAdmin.css" rel="stylesheet" />
    </head>
    <body>
        
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>Num. de venta</th>
                        <th>Medio de pago</th>
                        <th>Fecha</th>
                        <th>Empleado</th>
                        <th>Cliente</th>
                        <th>Servicio turístico</th>
                        <th>Paquete turístico</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% HttpSession misession = request.getSession();
                        List<Venta> listaVentas = (List) misession.getAttribute("listaVentas");

                        for (Venta venta : listaVentas) { %>
                    <tr>
                        <% Integer numero = venta.getNum_venta();%>
                        <td> <%=numero%> </td>
                        <% String pago = venta.getMedio_pago();%>
                        <td> <%=pago%> </td>
                        <% String fecha = String.format("%1$td/%1$tm/%1$tY", venta.getFecha_venta());%>
                        <td> <%=fecha%> </td>
                        <td> <%=venta.getUnEmpleado().getNombre()%>&nbsp;<%=venta.getUnEmpleado().getApellido()%><br>DNI:&nbsp;<%=venta.getUnEmpleado().getDni()%> </td>
                        <td> <%=venta.getUnCliente().getNombre()%>&nbsp;<%=venta.getUnCliente().getApellido()%><br>DNI:&nbsp;<%=venta.getUnCliente().getDni()%> </td>
                            <% if (venta.getUnServicio() != null) {%>
                        <td> <%=venta.getUnServicio().getNombre()%> - <%=venta.getUnServicio().getDescripcion()%> - Destino: <%=venta.getUnServicio().getDestino()%> Fecha: &nbsp;<%=String.format("%1$td/%1$tm/%1$tY", venta.getUnServicio().getFecha())%> </td>
                        <% } else {%> <td> - </td> <% } %>
                        <% if (venta.getUnPaquete() != null) {%>
                        <td> <%=venta.getUnPaquete().getCodigo()%> - $ <%=venta.getUnPaquete().getCosto_paquete()%> - Servicios: 
                            <% for (Servicio ser : venta.getUnPaquete().getListaServicios()) {%> * <%=ser.getNombre() + " - " + ser.getDescripcion() + " - " + String.format("%1$td/%1$tm/%1$tY", ser.getFecha())%> <% } %> </td>
                        <% } else {%> <td> - </td> <% }%>

                        <td>
                            <form action="SvModificarVenta" method="POST" class="tm-contact-form">
                                <input type="hidden" name="numero" value="<%=numero%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Modificar</button>
                            </form>
                        </td>
                        <td>
                            <form action="SvEliminarVenta" method="POST" class="tm-contact-form">
                                <input type="hidden" name="numero" value="<%=numero%>">
                                <button type="submit" class="btn btn-primary btn-block btn-large" >Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>

            </table>
            <div>
                <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="venta.jsp">
                    Crear Venta
                </a>
                <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="index.jsp">
                    Mi inicio
                </a>
            </div>
        </div>
        
    </body>
</html>