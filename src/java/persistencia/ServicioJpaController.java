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
import logica.Paquete_turistico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Servicio;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alfonso
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("Alfonso_Juan_Pablo_tpofinalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getListaPaquetes() == null) {
            servicio.setListaPaquetes(new ArrayList<Paquete_turistico>());
        }
        if (servicio.getListaVentas() == null) {
            servicio.setListaVentas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Paquete_turistico> attachedListaPaquetes = new ArrayList<Paquete_turistico>();
            for (Paquete_turistico listaPaquetesPaquete_turisticoToAttach : servicio.getListaPaquetes()) {
                listaPaquetesPaquete_turisticoToAttach = em.getReference(listaPaquetesPaquete_turisticoToAttach.getClass(), listaPaquetesPaquete_turisticoToAttach.getCodigo());
                attachedListaPaquetes.add(listaPaquetesPaquete_turisticoToAttach);
            }
            servicio.setListaPaquetes(attachedListaPaquetes);
            List<Venta> attachedListaVentas = new ArrayList<Venta>();
            for (Venta listaVentasVentaToAttach : servicio.getListaVentas()) {
                listaVentasVentaToAttach = em.getReference(listaVentasVentaToAttach.getClass(), listaVentasVentaToAttach.getNum_venta());
                attachedListaVentas.add(listaVentasVentaToAttach);
            }
            servicio.setListaVentas(attachedListaVentas);
            em.persist(servicio);
            for (Paquete_turistico listaPaquetesPaquete_turistico : servicio.getListaPaquetes()) {
                listaPaquetesPaquete_turistico.getListaServicios().add(servicio);
                listaPaquetesPaquete_turistico = em.merge(listaPaquetesPaquete_turistico);
            }
            for (Venta listaVentasVenta : servicio.getListaVentas()) {
                Servicio oldUnServicioOfListaVentasVenta = listaVentasVenta.getUnServicio();
                listaVentasVenta.setUnServicio(servicio);
                listaVentasVenta = em.merge(listaVentasVenta);
                if (oldUnServicioOfListaVentasVenta != null) {
                    oldUnServicioOfListaVentasVenta.getListaVentas().remove(listaVentasVenta);
                    oldUnServicioOfListaVentasVenta = em.merge(oldUnServicioOfListaVentasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCodigo());
            List<Paquete_turistico> listaPaquetesOld = persistentServicio.getListaPaquetes();
            List<Paquete_turistico> listaPaquetesNew = servicio.getListaPaquetes();
            List<Venta> listaVentasOld = persistentServicio.getListaVentas();
            List<Venta> listaVentasNew = servicio.getListaVentas();
            List<Paquete_turistico> attachedListaPaquetesNew = new ArrayList<Paquete_turistico>();
            for (Paquete_turistico listaPaquetesNewPaquete_turisticoToAttach : listaPaquetesNew) {
                listaPaquetesNewPaquete_turisticoToAttach = em.getReference(listaPaquetesNewPaquete_turisticoToAttach.getClass(), listaPaquetesNewPaquete_turisticoToAttach.getCodigo());
                attachedListaPaquetesNew.add(listaPaquetesNewPaquete_turisticoToAttach);
            }
            listaPaquetesNew = attachedListaPaquetesNew;
            servicio.setListaPaquetes(listaPaquetesNew);
            List<Venta> attachedListaVentasNew = new ArrayList<Venta>();
            for (Venta listaVentasNewVentaToAttach : listaVentasNew) {
                listaVentasNewVentaToAttach = em.getReference(listaVentasNewVentaToAttach.getClass(), listaVentasNewVentaToAttach.getNum_venta());
                attachedListaVentasNew.add(listaVentasNewVentaToAttach);
            }
            listaVentasNew = attachedListaVentasNew;
            servicio.setListaVentas(listaVentasNew);
            servicio = em.merge(servicio);
            for (Paquete_turistico listaPaquetesOldPaquete_turistico : listaPaquetesOld) {
                if (!listaPaquetesNew.contains(listaPaquetesOldPaquete_turistico)) {
                    listaPaquetesOldPaquete_turistico.getListaServicios().remove(servicio);
                    listaPaquetesOldPaquete_turistico = em.merge(listaPaquetesOldPaquete_turistico);
                }
            }
            for (Paquete_turistico listaPaquetesNewPaquete_turistico : listaPaquetesNew) {
                if (!listaPaquetesOld.contains(listaPaquetesNewPaquete_turistico)) {
                    listaPaquetesNewPaquete_turistico.getListaServicios().add(servicio);
                    listaPaquetesNewPaquete_turistico = em.merge(listaPaquetesNewPaquete_turistico);
                }
            }
            for (Venta listaVentasOldVenta : listaVentasOld) {
                if (!listaVentasNew.contains(listaVentasOldVenta)) {
                    listaVentasOldVenta.setUnServicio(null);
                    listaVentasOldVenta = em.merge(listaVentasOldVenta);
                }
            }
            for (Venta listaVentasNewVenta : listaVentasNew) {
                if (!listaVentasOld.contains(listaVentasNewVenta)) {
                    Servicio oldUnServicioOfListaVentasNewVenta = listaVentasNewVenta.getUnServicio();
                    listaVentasNewVenta.setUnServicio(servicio);
                    listaVentasNewVenta = em.merge(listaVentasNewVenta);
                    if (oldUnServicioOfListaVentasNewVenta != null && !oldUnServicioOfListaVentasNewVenta.equals(servicio)) {
                        oldUnServicioOfListaVentasNewVenta.getListaVentas().remove(listaVentasNewVenta);
                        oldUnServicioOfListaVentasNewVenta = em.merge(oldUnServicioOfListaVentasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getCodigo();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Paquete_turistico> listaPaquetes = servicio.getListaPaquetes();
            for (Paquete_turistico listaPaquetesPaquete_turistico : listaPaquetes) {
                listaPaquetesPaquete_turistico.getListaServicios().remove(servicio);
                listaPaquetesPaquete_turistico = em.merge(listaPaquetesPaquete_turistico);
            }
            List<Venta> listaVentas = servicio.getListaVentas();
            for (Venta listaVentasVenta : listaVentas) {
                listaVentasVenta.setUnServicio(null);
                listaVentasVenta = em.merge(listaVentasVenta);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
