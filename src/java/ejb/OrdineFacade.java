/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ordine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ciro
 */
@Stateless
public class OrdineFacade {

    @PersistenceContext(unitName = "MENU_MAXI_SERVERPU")
    private EntityManager em;

    public void addIngrediente(Ordine ordine) {
        em.persist(ordine);
    }
    
    public void removeIngrediente(Ordine ordine) {
        em.remove(em.merge(ordine));
    }
    
    public List<Ordine> findAll() {
        TypedQuery<Ordine> query = em.createNamedQuery("Ordine.findAll", Ordine.class);
        return query.getResultList();
    }
    
    public Ordine findById(Integer id) {
        TypedQuery<Ordine> query = em.createNamedQuery("Ordine.findById", Ordine.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
