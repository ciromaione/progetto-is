/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Piatto;
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
public class PiattoFacade {

    @PersistenceContext(unitName = "MENU_MAXI_SERVERPU")
    private EntityManager em;

    public PiattoFacade() {
    }

    public void addIngrediente(Piatto piatto) {
        em.persist(piatto);
    }
    
    public void removeIngrediente(Piatto piatto) {
        em.remove(em.merge(piatto));
    }
    
    public List<Piatto> findAll() {
        TypedQuery<Piatto> query = em.createNamedQuery("Piatto.findAll", Piatto.class);
        return query.getResultList();
    }
    
    public Piatto findById(Integer id) {
        TypedQuery<Piatto> query = em.createNamedQuery("Piatto.findById", Piatto.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public List<String> findCategorie() {
        TypedQuery<String> query = em.createNamedQuery("Piatto.findCategorie", String.class);
        return query.getResultList();
    }
    
}
