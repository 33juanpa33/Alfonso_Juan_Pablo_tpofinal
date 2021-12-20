<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/login.css" rel="stylesheet" />
    </head>
    <body>
        <div class="login">
            <h1>Bienvenido , Empleado  ! </h1>
            <br>
            <form action="SvUsuario" method="POST">
                <table>
                    <tr>
                        <th>
                            <input type="text" name="u" placeholder="Nombre de Usuario" required="required" />
                        </th>
                        <th> 
                            <input type="password" name="p" placeholder="Contraseña" required="required" /> 
                        </th> 
                    </tr>
                </table>
                <br>
                <button type="submit" class="btn btn-primary btn-block btn-large" > 
                    Acceder!
                </button>    
            </form>
            <br>
            <a class="btn btn-primary btn-block btn-large" style="text-decoration:none" href="empleado.jsp">
                Crear Empleado
            </a>
        </div>
    </body>
</html>
