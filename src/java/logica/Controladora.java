package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;

/**
 *
 * @author Alfonso
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearEmpleado(String nombre, String apellido, String direccion, String dni, String nacionalidad, String celular, String email, String cargo, Double sueldo, Date fechaNac, String nombreUsu, String contrasenia) {

        Empleado emple = new Empleado();
        Usuario usu = new Usuario();

        // asigno valores a empleado
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setDni(dni);
        emple.setNacionalidad(nacionalidad);
        emple.setCelular(celular);
        emple.setEmail(email);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        emple.setFecha_nac(fechaNac);

        // asigno valores a usuario
        usu.setNombreUsuario(nombreUsu);
        usu.setContrasenia(contrasenia);

        // asigno usuario a empleado
        emple.setUnUsuario(usu);

        controlPersis.crearEmpleado(emple, usu);
    }

    public List<Empleado> traerEmpleados() {
        return controlPersis.traerEmpleados();
    }

    public boolean verificarUsuario(String usuario, String contras) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        if (listaUsuarios != null) {
            for (Usuario usu : listaUsuarios) {
                if (usu.getNombreUsuario().equals(usuario) && usu.getContrasenia().equals(contras)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void borrarEmpleado(Integer idEmpleado, Integer idUsuario) {
        controlPersis.borrarEmpleado(idEmpleado, idUsuario);
    }

    public Empleado buscarEmpleado(Integer idEmpleado) {
        return controlPersis.buscarEmpleado(idEmpleado);
    }

    public Usuario buscarUsuario(Integer idUsuario) {
        return controlPersis.buscarUsuario(idUsuario);
    }

    public void modificarEmpleado(Empleado emple, Usuario usu) {
        controlPersis.modificarEmpleado(emple, usu);
    }

    public void crearCliente(String nombre, String apellido, String direccion, String dni, String nacionalidad, String celular, String email, Date fechaNac) {

        Cliente cli = new Cliente();

        // asigno valores a cliente
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setDireccion(direccion);
        cli.setDni(dni);
        cli.setNacionalidad(nacionalidad);
        cli.setCelular(celular);
        cli.setEmail(email);
        cli.setFecha_nac(fechaNac);

        controlPersis.crearCliente(cli);

    }

    public List<Cliente> traerClientes() {
        return controlPersis.traerClientes();
    }

    public void borrarCliente(Integer idCliente) {
        controlPersis.borrarCliente(idCliente);
    }

    public Cliente buscarCliente(Integer idCliente) {
        return controlPersis.buscarCliente(idCliente);
    }

    public void modificarCliente(Cliente cli) {
        controlPersis.modificarCliente(cli);
    }

    public void crearServicio(String nombre, String descripcion, String destino, Double costo, Date fecha, Boolean disponible) {
        Servicio serv = new Servicio();
        
        // asigno valores a servicio
        serv.setNombre(nombre);
        serv.setDescripcion(descripcion);
        serv.setDestino(destino);
        serv.setCosto(costo);
        serv.setFecha(fecha);
        serv.setDisponible(disponible);

        controlPersis.crearServicio(serv);
    }

    public List<Servicio> traerServicios() {
        List<Servicio> listaServ = new ArrayList();
        for (Servicio serv : controlPersis.traerServicios()) {
            if (serv.getDisponible()){
                listaServ.add(serv);
            }
        }
        return listaServ;
    }

    public void borrarServicio(Integer codigo) {
        controlPersis.borrarServicio(codigo);
    }

    public Servicio buscarServicio(Integer codigo) {
        return controlPersis.buscarServicio(codigo);
    }
    
    public void modificarServicio(Servicio ser) {
        controlPersis.modificarServicio(ser);
    }

    public void crearPaquete(List<Servicio> listaServEnPaquete, Double costo, Boolean disponible) {
        Paquete_turistico paq = new Paquete_turistico();
        
        // asigno valores a paquete
        paq.setCosto_paquete(costo*0.9);
        /*for (Servicio serv : listaServEnPaquete) {
            paq.agregarServicio(serv);
        }*/
        paq.setListaServicios(listaServEnPaquete);
        paq.setDisponible(disponible);

        controlPersis.crearPaquete(paq);
    }

    public List<Paquete_turistico> traerPaquetes() {
        List<Paquete_turistico> listaPaq = new ArrayList();
        for (Paquete_turistico paq : controlPersis.traerPaquete_turistico()) {
            if (paq.getDisponible()){
                listaPaq.add(paq);
            }
        }
        return listaPaq;
        
    }

    public Paquete_turistico buscarPaquete(Integer codigo) {
        return controlPersis.buscarPaquete(codigo);
    }

    public void modificarPaquete(Paquete_turistico paq) {
        controlPersis.modificarPaquete(paq);
    }

    public void crearVenta(String pago, Date fecha, Empleado emple, Cliente cli, Servicio serv, Paquete_turistico paque) {
        
        Venta ven = new Venta();
        
        // asigno valores a venta
        ven.setMedio_pago(pago);
        ven.setFecha_venta(fecha);
        ven.setUnEmpleado(emple);
        ven.setUnCliente(cli);
        
        if (serv.getCodigo() == null) {
            ven.setUnPaquete(paque);
            controlPersis.crearVenta(ven);
        }

        if (paque.getCodigo() == null) {
            ven.setUnServicio(serv);
            controlPersis.crearVenta(ven);
        }
        
    }

    public List<Venta> traerVentas() {
        return controlPersis.traerVentas();
    }

    public void borrarVenta(Integer numeroVenta) {
        controlPersis.borrarVenta(numeroVenta);
    }

    public Venta buscarVenta(Integer numeroVenta) {
        return controlPersis.buscarVenta(numeroVenta);
    }

    public void modificarVenta(Venta venta) {
        controlPersis.modificarVenta(venta);
    }
}
