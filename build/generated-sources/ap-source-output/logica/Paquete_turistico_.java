package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Servicio;
import logica.Venta;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-12-19T23:21:22")
@StaticMetamodel(Paquete_turistico.class)
public class Paquete_turistico_ { 

    public static volatile SingularAttribute<Paquete_turistico, Integer> codigo;
    public static volatile SingularAttribute<Paquete_turistico, Double> costo_paquete;
    public static volatile ListAttribute<Paquete_turistico, Servicio> listaServicios;
    public static volatile ListAttribute<Paquete_turistico, Venta> listaVentas;
    public static volatile SingularAttribute<Paquete_turistico, Boolean> disponible;

}