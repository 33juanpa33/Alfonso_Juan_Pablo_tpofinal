/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete_turistico;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alfonso
 */
public class Paquete_turisticoJpaController implements Serializable {

    public Paquete_turisticoJpaController() {
        emf = Persistence.createEntityManagerFactory("Alfonso_Juan_Pablo_tpofinalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete_turistico paquete_turistico) {
        if (paquete_turistico.getListaServicios() == null) {
            paquete_turistico.setListaServicios(new ArrayList<Servicio>());
        }
        if (paquete_turistico.getListaVentas() == null) {
            paquete_turistico.setListaVentas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedListaServicios = new ArrayList<Servicio>();
            for (Servicio listaServiciosServicioToAttach : paquete_turistico.getListaServicios()) {
                listaServiciosServicioToAttach = em.getReference(listaServiciosServicioToAttach.getClass(), listaServiciosServicioToAttach.getCodigo());
                attachedListaServicios.add(listaServiciosServicioToAttach);
            }
            paquete_turistico.setListaServicios(attachedListaServicios);
            List<Venta> attachedListaVentas = new ArrayList<Venta>();
            for (Venta listaVentasVentaToAttach : paquete_turistico.getListaVentas()) {
                listaVentasVentaToAttach = em.getReference(listaVentasVentaToAttach.getClass(), listaVentasVentaToAttach.getNum_venta());
                attachedListaVentas.add(listaVentasVentaToAttach);
            }
            paquete_turistico.setListaVentas(attachedListaVentas);
            em.persist(paquete_turistico);
            for (Servicio listaServiciosServicio : paquete_turistico.getListaServicios()) {
                listaServiciosServicio.getListaPaquetes().add(paquete_turistico);
                listaServiciosServicio = em.merge(listaServiciosServicio);
            }
            for (Venta listaVentasVenta : paquete_turistico.getListaVentas()) {
                Paquete_turistico oldUnPaqueteOfListaVentasVenta = listaVentasVenta.getUnPaquete();
                listaVentasVenta.setUnPaquete(paquete_turistico);
                listaVentasVenta = em.merge(listaVentasVenta);
                if (oldUnPaqueteOfListaVentasVenta != null) {
                    oldUnPaqueteOfListaVentasVenta.getListaVentas().remove(listaVentasVenta);
                    oldUnPaqueteOfListaVentasVenta = em.merge(oldUnPaqueteOfListaVentasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete_turistico paquete_turistico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete_turistico persistentPaquete_turistico = em.find(Paquete_turistico.class, paquete_turistico.getCodigo());
            List<Servicio> listaServiciosOld = persistentPaquete_turistico.getListaServicios();
            List<Servicio> listaServiciosNew = paquete_turistico.getListaServicios();
            List<Venta> listaVentasOld = persistentPaquete_turistico.getListaVentas();
            List<Venta> listaVentasNew = paquete_turistico.getListaVentas();
            List<Servicio> attachedListaServiciosNew = new ArrayList<Servicio>();
            for (Servicio listaServiciosNewServicioToAttach : listaServiciosNew) {
                listaServiciosNewServicioToAttach = em.getReference(listaServiciosNewServicioToAttach.getClass(), listaServiciosNewServicioToAttach.getCodigo());
                attachedListaServiciosNew.add(listaServiciosNewServicioToAttach);
            }
            listaServiciosNew = attachedListaServiciosNew;
            paquete_turistico.setListaServicios(listaServiciosNew);
            List<Venta> attachedListaVentasNew = new ArrayList<Venta>();
            for (Venta listaVentasNewVentaToAttach : listaVentasNew) {
                listaVentasNewVentaToAttach = em.getReference(listaVentasNewVentaToAttach.getClass(), listaVentasNewVentaToAttach.getNum_venta());
                attachedListaVentasNew.add(listaVentasNewVentaToAttach);
            }
            listaVentasNew = attachedListaVentasNew;
            paquete_turistico.setListaVentas(listaVentasNew);
            paquete_turistico = em.merge(paquete_turistico);
            for (Servicio listaServiciosOldServicio : listaServiciosOld) {
                if (!listaServiciosNew.contains(listaServiciosOldServicio)) {
                    listaServiciosOldServicio.getListaPaquetes().remove(paquete_turistico);
                    listaServiciosOldServicio = em.merge(listaServiciosOldServicio);
                }
            }
            for (Servicio listaServiciosNewServicio : listaServiciosNew) {
                if (!listaServiciosOld.contains(listaServiciosNewServicio)) {
                    listaServiciosNewServicio.getListaPaquetes().add(paquete_turistico);
                    listaServiciosNewServicio = em.merge(listaServiciosNewServicio);
                }
            }
            for (Venta listaVentasOldVenta : listaVentasOld) {
                if (!listaVentasNew.contains(listaVentasOldVenta)) {
                    listaVentasOldVenta.setUnPaquete(null);
                    listaVentasOldVenta = em.merge(listaVentasOldVenta);
                }
            }
            for (Venta listaVentasNewVenta : listaVentasNew) {
                if (!listaVentasOld.contains(listaVentasNewVenta)) {
                    Paquete_turistico oldUnPaqueteOfListaVentasNewVenta = listaVentasNewVenta.getUnPaquete();
                    listaVentasNewVenta.setUnPaquete(paquete_turistico);
                    listaVentasNewVenta = em.merge(listaVentasNewVenta);
                    if (oldUnPaqueteOfListaVentasNewVenta != null && !oldUnPaqueteOfListaVentasNewVenta.equals(paquete_turistico)) {
                        oldUnPaqueteOfListaVentasNewVenta.getListaVentas().remove(listaVentasNewVenta);
                        oldUnPaqueteOfListaVentasNewVenta = em.merge(oldUnPaqueteOfListaVentasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paquete_turistico.getCodigo();
                if (findPaquete_turistico(id) == null) {
                    throw new NonexistentEntityException("The paquete_turistico with id " + id + " no longer exists.");
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
            Paquete_turistico paquete_turistico;
            try {
                paquete_turistico = em.getReference(Paquete_turistico.class, id);
                paquete_turistico.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete_turistico with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> listaServicios = paquete_turistico.getListaServicios();
            for (Servicio listaServiciosServicio : listaServicios) {
                listaServiciosServicio.getListaPaquetes().remove(paquete_turistico);
                listaServiciosServicio = em.merge(listaServiciosServicio);
            }
            List<Venta> listaVentas = paquete_turistico.getListaVentas();
            for (Venta listaVentasVenta : listaVentas) {
                listaVentasVenta.setUnPaquete(null);
                listaVentasVenta = em.merge(listaVentasVenta);
            }
            em.remove(paquete_turistico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete_turistico> findPaquete_turisticoEntities() {
        return findPaquete_turisticoEntities(true, -1, -1);
    }

    public List<Paquete_turistico> findPaquete_turisticoEntities(int maxResults, int firstResult) {
        return findPaquete_turisticoEntities(false, maxResults, firstResult);
    }

    private List<Paquete_turistico> findPaquete_turisticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete_turistico.class));
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

    public Paquete_turistico findPaquete_turistico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete_turistico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaquete_turisticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete_turistico> rt = cq.from(Paquete_turistico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
