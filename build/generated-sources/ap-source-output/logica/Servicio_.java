package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Paquete_turistico;
import logica.Venta;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-12-19T23:21:22")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> descripcion;
    public static volatile SingularAttribute<Servicio, Date> fecha;
    public static volatile SingularAttribute<Servicio, Integer> codigo;
    public static volatile SingularAttribute<Servicio, Double> costo;
    public static volatile ListAttribute<Servicio, Paquete_turistico> listaPaquetes;
    public static volatile SingularAttribute<Servicio, String> destino;
    public static volatile ListAttribute<Servicio, Venta> listaVentas;
    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile SingularAttribute<Servicio, Boolean> disponible;

}