/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Empleado;
import logica.Cliente;
import logica.Servicio;
import logica.Paquete_turistico;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alfonso
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController() {
        emf = Persistence.createEntityManagerFactory("Alfonso_Juan_Pablo_tpofinalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado unEmpleado = venta.getUnEmpleado();
            if (unEmpleado != null) {
                unEmpleado = em.getReference(unEmpleado.getClass(), unEmpleado.getId_empleado());
                venta.setUnEmpleado(unEmpleado);
            }
            Cliente unCliente = venta.getUnCliente();
            if (unCliente != null) {
                unCliente = em.getReference(unCliente.getClass(), unCliente.getId_cliente());
                venta.setUnCliente(unCliente);
            }
            Servicio unServicio = venta.getUnServicio();
            if (unServicio != null) {
                unServicio = em.getReference(unServicio.getClass(), unServicio.getCodigo());
                venta.setUnServicio(unServicio);
            }
            Paquete_turistico unPaquete = venta.getUnPaquete();
            if (unPaquete != null) {
                unPaquete = em.getReference(unPaquete.getClass(), unPaquete.getCodigo());
                venta.setUnPaquete(unPaquete);
            }
            em.persist(venta);
            if (unEmpleado != null) {
                unEmpleado.getListaVentas().add(venta);
                unEmpleado = em.merge(unEmpleado);
            }
            if (unCliente != null) {
                unCliente.getListaVentas().add(venta);
                unCliente = em.merge(unCliente);
            }
            if (unServicio != null) {
                unServicio.getListaVentas().add(venta);
                unServicio = em.merge(unServicio);
            }
            if (unPaquete != null) {
                unPaquete.getListaVentas().add(venta);
                unPaquete = em.merge(unPaquete);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getNum_venta());
            Empleado unEmpleadoOld = persistentVenta.getUnEmpleado();
            Empleado unEmpleadoNew = venta.getUnEmpleado();
            Cliente unClienteOld = persistentVenta.getUnCliente();
            Cliente unClienteNew = venta.getUnCliente();
            Servicio unServicioOld = persistentVenta.getUnServicio();
            Servicio unServicioNew = venta.getUnServicio();
            Paquete_turistico unPaqueteOld = persistentVenta.getUnPaquete();
            Paquete_turistico unPaqueteNew = venta.getUnPaquete();
            if (unEmpleadoNew != null) {
                unEmpleadoNew = em.getReference(unEmpleadoNew.getClass(), unEmpleadoNew.getId_empleado());
                venta.setUnEmpleado(unEmpleadoNew);
            }
            if (unClienteNew != null) {
                unClienteNew = em.getReference(unClienteNew.getClass(), unClienteNew.getId_cliente());
                venta.setUnCliente(unClienteNew);
            }
            if (unServicioNew != null) {
                unServicioNew = em.getReference(unServicioNew.getClass(), unServicioNew.getCodigo());
                venta.setUnServicio(unServicioNew);
            }
            if (unPaqueteNew != null) {
                unPaqueteNew = em.getReference(unPaqueteNew.getClass(), unPaqueteNew.getCodigo());
                venta.setUnPaquete(unPaqueteNew);
            }
            venta = em.merge(venta);
            if (unEmpleadoOld != null && !unEmpleadoOld.equals(unEmpleadoNew)) {
                unEmpleadoOld.getListaVentas().remove(venta);
                unEmpleadoOld = em.merge(unEmpleadoOld);
            }
            if (unEmpleadoNew != null && !unEmpleadoNew.equals(unEmpleadoOld)) {
                unEmpleadoNew.getListaVentas().add(venta);
                unEmpleadoNew = em.merge(unEmpleadoNew);
            }
            if (unClienteOld != null && !unClienteOld.equals(unClienteNew)) {
                unClienteOld.getListaVentas().remove(venta);
                unClienteOld = em.merge(unClienteOld);
            }
            if (unClienteNew != null && !unClienteNew.equals(unClienteOld)) {
                unClienteNew.getListaVentas().add(venta);
                unClienteNew = em.merge(unClienteNew);
            }
            if (unServicioOld != null && !unServicioOld.equals(unServicioNew)) {
                unServicioOld.getListaVentas().remove(venta);
                unServicioOld = em.merge(unServicioOld);
            }
            if (unServicioNew != null && !unServicioNew.equals(unServicioOld)) {
                unServicioNew.getListaVentas().add(venta);
                unServicioNew = em.merge(unServicioNew);
            }
            if (unPaqueteOld != null && !unPaqueteOld.equals(unPaqueteNew)) {
                unPaqueteOld.getListaVentas().remove(venta);
                unPaqueteOld = em.merge(unPaqueteOld);
            }
            if (unPaqueteNew != null && !unPaqueteNew.equals(unPaqueteOld)) {
                unPaqueteNew.getListaVentas().add(venta);
                unPaqueteNew = em.merge(unPaqueteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getNum_venta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNum_venta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Empleado unEmpleado = venta.getUnEmpleado();
            if (unEmpleado != null) {
                unEmpleado.getListaVentas().remove(venta);
                unEmpleado = em.merge(unEmpleado);
            }
            Cliente unCliente = venta.getUnCliente();
            if (unCliente != null) {
                unCliente.getListaVentas().remove(venta);
                unCliente = em.merge(unCliente);
            }
            Servicio unServicio = venta.getUnServicio();
            if (unServicio != null) {
                unServicio.getListaVentas().remove(venta);
                unServicio = em.merge(unServicio);
            }
            Paquete_turistico unPaquete = venta.getUnPaquete();
            if (unPaquete != null) {
                unPaquete.getListaVentas().remove(venta);
                unPaquete = em.merge(unPaquete);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
