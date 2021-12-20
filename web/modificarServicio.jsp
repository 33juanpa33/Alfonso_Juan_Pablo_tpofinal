<%@page import="logica.Servicio"%>
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
            Servicio serv = (Servicio) misession.getAttribute("servicio");
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
                                            <a class="nav-link tm-nav-link" href="#">Modificar Servicio Turístico<span class="sr-only">(current)</span></a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link tm-nav-link" href="servicioAdmin.jsp">Administrar Servicios</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link tm-nav-link" href="servicio.jsp">Alta Servicio</a>
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
                        <h2 class="tm-text-primary tm-my-1 tm-mb-5 tm-intro-text">Modificar Servicio Turístico</h2>
                    </div>
                </section>
                <div class="row tm-mb-10 tm-contact-row mx-auto">
                    <div class="col-lg-12 ml-auto ">
                        <div class="tm-contact-form-wrap">
                            <form id="contact-form" action="SvModificarServicio" method="get" class="tm-contact-form">
                                <div class="form-group">
                                    <label>Nombre: </label>
                                    <select class="form-control" id="contact-select" name="nombre" placeholder="Seleccione" required="">
                                        <option value="">(Seleccione)</option>
                                        <option value="Hotel por noche" <% if (serv.getNombre().equals("Hotel por noche")) {%>
                                                <%="selected"%> <% } %> >Hotel por noche</option>
                                        <option value="Alquiler de auto" <% if (serv.getNombre().equals("Alquiler de auto")) {%>
                                                <%="selected"%> <% } %> >Alquiler de auto</option>
                                        <option value="Pasaje de colectivo" <% if (serv.getNombre().equals("Pasaje de colectivo")) {%>
                                                <%="selected"%> <% } %> >Pasaje de colectivo</option>
                                        <option value="Pasaje de avion" <% if (serv.getNombre().equals("Pasaje de avion")) {%>
                                                <%="selected"%> <% } %> >Pasaje de avion</option>
                                        <option value="Pasaje de tren" <% if (serv.getNombre().equals("Pasaje de tren")) {%>
                                                <%="selected"%> <% } %> >Pasaje de tren</option>
                                        <option value="Excursion" <% if (serv.getNombre().equals("Excursión")) {%>
                                                <%="selected"%> <% } %> >Excursión</option>
                                        <option value="Entrada a evento" <% if (serv.getNombre().equals("Entrada a evento")) {%>
                                                <%="selected"%> <% }%> >Entrada a evento</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Descripción: </label><input type="text" name="descripcion" class="form-control rounded-0" value="<%=serv.getDescripcion()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Destino: </label><input type="text" name="destino" class="form-control rounded-0" value="<%=serv.getDestino()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <label>Costo: </label><input type="number" name="costo" class="form-control rounded-0" value="<%=serv.getCosto()%>" required="" />
                                </div>
                                <div class="form-group">
                                    <% String fecha = String.format("%1$tY-%1$tm-%1$td", serv.getFecha());%>
                                    <label>Fecha: </label><input type="date" name="fecha" value="<%=fecha%>" class="form-control rounded-0" required="" />
                                </div>
                                <input type="hidden" name="codigo" value="<%=serv.getCodigo()%>">
                                <div class="form-group mb-0">
                                    <button type="submit" class="btn btn-3 rounded-0 d-block ml-auto">Cargar Servicio</button>
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