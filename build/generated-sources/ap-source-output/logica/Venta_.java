package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Cliente;
import logica.Empleado;
import logica.Paquete_turistico;
import logica.Servicio;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-12-19T23:21:22")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> num_venta;
    public static volatile SingularAttribute<Venta, Empleado> unEmpleado;
    public static volatile SingularAttribute<Venta, String> medio_pago;
    public static volatile SingularAttribute<Venta, Paquete_turistico> unPaquete;
    public static volatile SingularAttribute<Venta, Servicio> unServicio;
    public static volatile SingularAttribute<Venta, Date> fecha_venta;
    public static volatile SingularAttribute<Venta, Cliente> unCliente;

}