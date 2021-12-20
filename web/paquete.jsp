<%@page import="logica.Servicio"%>
<%@page import="java.util.List"%>
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
                                            <a class="nav-link tm-nav-link" href="#">Alta Paquete Turístico<span class="sr-only">(current)</span></a>
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
                        <h2 class="tm-text-primary tm-my-1 tm-mb-5 tm-intro-text">Alta Paquete Turístico</h2>
                    </div>
                </section>
                <div class="row tm-mb-10 tm-contact-row mx-auto">
                    <div class="col-lg-12 ml-auto ">
                        <div class="tm-contact-form-wrap">
                            <form id="contact-form" action="SvPaquete" method="POST" class="tm-contact-form" onsubmit="return verificar()">
                                <div class="form-group">
                                    <p>Servicios: </p>
                                    <% List<Servicio> listaServ = (List) misession.getAttribute("listaServicios");
                                        for (Servicio serv : listaServ) {%>
                                    <label>
                                        <input type="checkbox" name="codigo" value="<%=serv.getCodigo()%>" >
                                        <% String fecha = String.format("%1$td-%1$tm-%1$tY", serv.getFecha());%>
                                        <%=" - "%> <%=serv.getNombre() + " - " + serv.getDescripcion() + " - " + fecha + " - $" + serv.getCosto()+" -Codigo del servicio: "+serv.getCodigo()%>
                                    </label>
                                    <br>
                                    <% }; %>
                                </div>
                                <div class="form-group mb-0">
                                    <button type="submit" class="btn btn-3 rounded-0 d-block ml-auto">Agregar Paquete Turístico</button>
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
        <script type="text/javascript">
            function verificar() {
                var suma = 0;
                var losChboxs = document.getElementsByName('codigo');
                for (var i = 0, j = losChboxs.length; i < j; i++) {
                    if (losChboxs[i].checked === true) {
                        suma++;
                    }
                }
                if (suma <= 1) {
                    alert('Debe seleccionar 2 o más Servicios Turísticos');
                    return false;
                } else {
                    alert('La cantidad de servicios seleccionados fueron: '+suma);
                }
            } 
        </script>

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