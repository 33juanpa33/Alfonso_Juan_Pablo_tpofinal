package persistencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cliente;
import logica.Empleado;
import logica.Paquete_turistico;
import logica.Servicio;
import logica.Usuario;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alfonso
 */
public class ControladoraPersistencia {
    
    EmpleadoJpaController empleJPA = new EmpleadoJpaController();
    UsuarioJpaController usuJPA = new UsuarioJpaController();
    ClienteJpaController cliJPA = new ClienteJpaController();
    ServicioJpaController servJPA = new ServicioJpaController();
    Paquete_turisticoJpaController paqJPA = new Paquete_turisticoJpaController();
    VentaJpaController ventJPA = new VentaJpaController();

    public void crearEmpleado(Empleado emple, Usuario usu) {
        usuJPA.create(usu);
        empleJPA.create(emple);
    }

    public List<Empleado> traerEmpleados() {
        return empleJPA.findEmpleadoEntities();
    }

    public List<Usuario> traerUsuarios() {
        return usuJPA.findUsuarioEntities();
    }

    public void borrarEmpleado(Integer idEmpleado, Integer idUsuario) {
        try {
            empleJPA.destroy(idEmpleado);
            usuJPA.destroy(idUsuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado buscarEmpleado(Integer idEmpleado) {
        return empleJPA.findEmpleado(idEmpleado);
    }

    public Usuario buscarUsuario(Integer idUsuario) {
        return usuJPA.findUsuario(idUsuario);
    }

    public void modificarEmpleado(Empleado emple, Usuario usu) {
        try {
            empleJPA.edit(emple);
            usuJPA.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearCliente(Cliente cli) {
        cliJPA.create(cli);
    }

    public List<Cliente> traerClientes() {
        return cliJPA.findClienteEntities();
    }

    public void borrarCliente(Integer idCliente) {
        try {
            cliJPA.destroy(idCliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente buscarCliente(Integer idCliente) {
        return cliJPA.findCliente(idCliente);
    }

    public void modificarCliente(Cliente cli) {
        try {
            cliJPA.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearServicio(Servicio serv) {
        try {
            servJPA.create(serv);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Servicio> traerServicios() {
        return servJPA.findServicioEntities();
    }

    public Servicio buscarServicio(Integer codigo) {
        return servJPA.findServicio(codigo);
    }
    
    public void borrarServicio(Integer codigo) {
        try {
            servJPA.destroy(codigo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarServicio(Servicio ser) {
        try {
            servJPA.edit(ser);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearPaquete(Paquete_turistico paq) {
        try {
            paqJPA.create(paq);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Paquete_turistico> traerPaquete_turistico() {
        return paqJPA.findPaquete_turisticoEntities();
    }

    public Paquete_turistico buscarPaquete(Integer codigo) {
        return paqJPA.findPaquete_turistico(codigo);
    }

    public void modificarPaquete(Paquete_turistico paq) {
        try {
            paqJPA.edit(paq);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearVenta(Venta ven) {
        ventJPA.create(ven);
    }

    public List<Venta> traerVentas() {
        return ventJPA.findVentaEntities();
    }

    public void borrarVenta(Integer numeroVenta) {
        try {
            ventJPA.destroy(numeroVenta);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Venta buscarVenta(Integer numeroVenta) {
        return ventJPA.findVenta(numeroVenta);
    }

    public void modificarVenta(Venta venta) {
        try {
            ventJPA.edit(venta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
