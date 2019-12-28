/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingrediente;
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
public class IngredienteFacade {

    @PersistenceContext(unitName = "MENU_MAXI_SERVERPU")
    private EntityManager em;

    public IngredienteFacade() {
    }

    public void addIngrediente(Ingrediente ingrediente) {
        em.persist(ingrediente);
    }
    
    public void removeIngrediente(Ingrediente ingrediente) {
        em.remove(em.merge(ingrediente));
    }
    
    public List<Ingrediente> findAll() {
        TypedQuery<Ingrediente> query = em.createNamedQuery("Ingrediente.findAll", Ingrediente.class);
        return query.getResultList();
    }
    
    public Ingrediente findById(Integer id) {
        TypedQuery<Ingrediente> query = em.createNamedQuery("Ingrediente.findById", Ingrediente.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
}
