<%@page import="logica.Empleado"%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Mi Empresa de Turismo</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="fontawesome/css/all.min.css" rel="stylesheet" />
        <link href="slick/slick.css" rel='stylesheet' />
        <link href="slick/slick-theme.css" rel='stylesheet' />
        <link href="css/templatemo-real-dynamic.css" rel="stylesheet" />
    </head>

    <body>
        <%
            HttpSession misession = request.getSession();
            String usu = (String) misession.getAttribute("usuarioActivo");
            if (usu == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
        <%
            Empleado emple = (Empleado) misession.getAttribute("empleado");
            Usuario usuarioAmodif = (Usuario) misession.getAttribute("usuario");
            String usuActivo = (String) misession.getAttribute("usuarioActivo");
        %>
        <div class="tm-container">
            <div class="tm-site-header"></div> <!-- tm-site-header -->
            <div class="tm-site-header-overlay">
                <div class="tm-header-stripe ml-auto"></div>
                <div class="tm-header-stripe tm-header-stripe-short ml-auto"></div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4 tm-site-header-left">
                            <h1 class="text-uppercase tm-site-name">Mi Empresa de Turismo</h1>
                            <p class="text-white mb-0 tm-site-desc">Desarrollo Web Fullstack con Java</p>
                        </div>
                        <div class="col-lg-8 tm-site-header-right">
                            <!--Navbar-->
                            <nav class="navbar navbar-expand-lg">
                                <!-- Collapse button -->
                                <button class="navbar-toggler toggler-example" type="button" data-toggle="collapse"
                                        data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                        aria-label="Toggle navigation"><span class="dark-blue-text"><i class="fas fa-bars text-white"></i></span></button>
                                <!-- Collapsible content -->
                                <div class="collapse navbar-collapse tm-nav" id="navbarNav">
                                    <!-- Links -->
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a class="nav-link tm-nav-link" href="index.jsp"> Inicio </a>
                                        </li>
                                        <li class="nav-item active">
                                            <a class="nav-link tm-nav-link" href="#">Modificar Empleado<span class="sr-only">(current)</span></a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link tm-nav-link" href="empleadoAdmin.jsp">Administrar empleados</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link tm-nav-link" href="empleado.jsp">Alta Empleado</a>
                                        </li>
                                        <li class="nav-item">
                                            <form action="SvLogOut" method="POST" class="nav-link tm-nav-link">
                                                <button  type="submit" > 
                                                    Cerrar Sesión
                                                </button>
                                            </form>
                                        </li>
                                    </ul>
                                    <!-- Links -->
                                </div>
                                <!-- Collapsible content -->
                            </nav>
                            <!--/.Navbar-->
                        </div> <!-- col -->
                    </div> <!-- row -->
                </div> <!-- container fluid -->
            </div> <!-- tm-site-header-overlay -->
            <div class="tm-header-stripe w-100"></div>
        </div>
        <div class="tm-container">
            <div class="parallax-window" data-parallax="scroll" data-image-src="img/real-dynamic-banner-04.jpg">
            </div>
        </div>

        <div class="tm-container bg-white">
            <div class="tm-header-stripe w-100"></div>
            <div class="container-fluid">
                <!--Intro-->
                <section class="row tm-mb-1">
                    <div class="col-12 text-center mx-auto tm-about-box">
                        <h2 class="tm-text-primary tm-my-1 tm-mb-5 tm-intro-text">Modificar Empleado</h2>
                    </div>
                </section>
                <div class="row tm-mb-10 tm-contact-row mx-auto">
                    <div class="col-lg-12 ml-auto ">
                        <div class="tm-contact-form-wrap">
                            <form id="contact-form" action="SvModificarEmpleado" method="get" class="tm-contact-form">
                                <div class="form-group">
                                    <label>Nombre: </label><input type="text" name="nombre" class="form-control rounded-0" value="<%=emple.getNombre()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Apellido: </label><input type="text" name="apellido" class="form-control rounded-0" value="<%=emple.getApellido()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Dirección: </label><input type="text" name="direccion" class="form-control rounded-0" value="<%=emple.getDireccion()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>DNI: </label><input type="text" name="dni" class="form-control rounded-0" value="<%=emple.getDni()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Nacionalidad: </label><input type="text" name="nacionalidad" class="form-control rounded-0" value="<%=emple.getNacionalidad()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Celular: </label><input type="text" name="celular" class="form-control rounded-0" value="<%=emple.getCelular()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Email: </label><input type="email" name="email" class="form-control rounded-0" value="<%=emple.getEmail()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Cargo: </label><input type="text" name="cargo" class="form-control rounded-0" value="<%=emple.getCargo()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Sueldo: </label><input type="number" name="sueldo" class="form-control rounded-0" value="<%=emple.getSueldo()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <% String nacimiento = String.format("%1$tY-%1$tm-%1$td", emple.getFecha_nac());%>
                                    <label>Fecha de nacimiento: </label><input type="date" name="fechaNac" value="<%=nacimiento%>" class="form-control rounded-0" required="" />
                                </div>
                                <%
                                    String nombreUsu = usuarioAmodif.getNombreUsuario();
                                    if (nombreUsu.equals(usuActivo)) {
                                %>
                                <div class="form-group">
                                    <label>Nombre de usuario: </label><input type="text" name="nombreUsu" class="form-control rounded-0" value="<%=emple.getUnUsuario().getNombreUsuario()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Contraseña: </label><input type="password" name="contrasenia" class="form-control rounded-0" value="<%=emple.getUnUsuario().getContrasenia()%>" required="" />
                                </div>
                                <% } else {%>

                                <label></label><input type="hidden" name="nombreUsu" class="form-control rounded-0" value="<%=emple.getUnUsuario().getNombreUsuario()%>" required="" />


                                <label></label><input type="hidden" name="contrasenia" class="form-control rounded-0" value="<%=emple.getUnUsuario().getContrasenia()%>" required="" />

                                <% }%>
                                <input type="hidden" name="idEmpleado" value="<%=emple.getId_empleado()%>">
                                <input type="hidden" name="idUsuario" value="<%=emple.getUnUsuario().getId()%>">

                                <div class="form-group mb-0">
                                    <button type="submit" class="btn btn-3 rounded-0 d-block ml-auto">Modificar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Testimonials -->
                <div class="tm-mb-2">
                    <h2 class="text-center tm-text-primary tm-h2-big tm-mb-6">El placer de vacacionar</h2>
                </div>
                <footer class="row">
                    <p class="mb-0 w-100 text-center col-12">
                        Company &copy; Desarrollo Web Fullstack con Java 2021 

                        - "Mi empresa de turismo" by <a rel="nofollow" href="" class="tm-text-link"> Alfonso Juan Pablo </a>
                    </p>
                </footer>
            </div> <!-- container-fluid -->
        </div> <!-- tm-container -->

        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/parallax.min.js"></script>
        <script src="slick/slick.min.js"></script>
        <script src="js/tooplate-script.js"></script>
        <script>
            $(document).ready(function () {
                // Testimonials carousel
                $('.tm-carousel').slick({
                    dots: true,
                    infinite: false,
                    speed: 300,
                    slidesToShow: 1,
                    slidesToScroll: 1
                });
            });

        </script>
        <% }%>
    </body>
</html>