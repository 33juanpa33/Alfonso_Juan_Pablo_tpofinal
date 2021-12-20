package logica;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Alfonso
 */
@Entity
public class Servicio implements Serializable {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    @Basic
    private String nombre;
    private String descripcion;
    private String destino;
    private Double costo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Boolean disponible;
    @ManyToMany
    private List<Paquete_turistico> listaPaquetes;
    @OneToMany
    private List<Venta> listaVentas;
    
    // Constructor
    public Servicio() {
    }

    public Servicio(Integer codigo, String nombre, String descripcion, String destino, Double costo, Date fecha, Boolean disponible, List<Paquete_turistico> listaPaquetes, List<Venta> listaVentas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.costo = costo;
        this.fecha = fecha;
        this.disponible = disponible;
        this.listaPaquetes = listaPaquetes;
        this.listaVentas = listaVentas;
    }
    
        
    // Métodos
    @Override
    public String toString() {
        return "Servicio{" + "codigo_servicio=" + codigo + ", nombre=" + nombre + ", descripcion_breve=" + descripcion + ", destino_servicio=" + destino + ", fecha_servicio=" + fecha + ", costo_servicio=" + costo + '}';
    }
    
    // Getters y setters
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Double getCosto() {
        return costo;
    }
    public void setCosto(Double costo) {
        this.costo = costo;
    }    
    public List<Paquete_turistico> getListaPaquetes() {
        return listaPaquetes;
    }
    public void setListaPaquetes(List<Paquete_turistico> listaPaquetes) {
        this.listaPaquetes = listaPaquetes;
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