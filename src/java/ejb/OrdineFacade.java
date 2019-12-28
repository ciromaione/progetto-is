/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ordine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
