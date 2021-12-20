package logica;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
/**
 *
 * @author Alfonso
 */
@Entity
public class Paquete_turistico implements Serializable {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    @Basic
    private Double costo_paquete;
    private Boolean disponible;
    @ManyToMany
    private List<Servicio> listaServicios;
    @OneToMany
    private List<Venta> listaVentas;

    // Constructores
    public Paquete_turistico() {
    }

    public Paquete_turistico(Integer codigo, Boolean disponible, List<Servicio> listaServicios, List<Venta> listaVentas) {
        this.codigo = codigo;
        this.disponible = disponible;
        this.listaServicios = listaServicios;
        this.listaVentas = listaVentas;
    }
        
    // Método por el cual se pueden agregar los servicios que incluye el paquete
    public void agregarServicio(Servicio serv) {
        listaServicios.add(serv);
        this.costo_paquete += serv.getCosto();
    }
    
    public Double obtenerCostoConDescuento() {
        return costo_paquete*0.9;
    }

    // Getters y setters
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public Double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(Double costo_paquete) {
        this.costo_paquete = costo_paquete;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
}
