package logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Alfonso
 */
@Entity
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer num_venta;
    @Basic
    private String medio_pago;
    @Temporal(TemporalType.DATE)
    private Date fecha_venta;
    @ManyToOne
    private Empleado unEmpleado;
    @ManyToOne
    private Cliente unCliente;
    @ManyToOne
    private Servicio unServicio;
    @ManyToOne
    private Paquete_turistico unPaquete;
    

    public Venta() {
    }

    public Venta(Integer num_venta, String medio_pago, Date fecha_venta, Empleado unEmpleado, Cliente unCliente, Servicio unServicio) {
        this.num_venta = num_venta;
        this.medio_pago = medio_pago;
        this.fecha_venta = fecha_venta;
        this.unEmpleado = unEmpleado;
        this.unCliente = unCliente;
        this.unServicio = unServicio;
    }
    
    public Venta(Integer num_venta, String medio_pago, Date fecha_venta, Empleado unEmpleado, Cliente unCliente, Paquete_turistico unPaquete) {
        this.num_venta = num_venta;
        this.medio_pago = medio_pago;
        this.fecha_venta = fecha_venta;
        this.unEmpleado = unEmpleado;
        this.unCliente = unCliente;
        this.unPaquete = unPaquete;
    }

    public Integer getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(Integer num_venta) {
        this.num_venta = num_venta;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Empleado getUnEmpleado() {
        return unEmpleado;
    }

    public void setUnEmpleado(Empleado unEmpleado) {
        this.unEmpleado = unEmpleado;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public Servicio getUnServicio() {
        return unServicio;
    }

    public void setUnServicio(Servicio unServicio) {
        this.unServicio = unServicio;
    }

    public Paquete_turistico getUnPaquete() {
        return unPaquete;
    }

    public void setUnPaquete(Paquete_turistico unPaquete) {
        this.unPaquete = unPaquete;
    }
}