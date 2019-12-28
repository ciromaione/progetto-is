/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingrediente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ciro
 */
@Stateless
public class IngredienteFacade {

    @PersistenceContext(unitName = "MENU_MAXI_SERVERPU")
    private EntityManager em;

    public void addIngrediente(Ingrediente ingrediente) {
        em.persist(ingrediente);
    }
    
    public void removeIngrediente(Ingrediente ingrediente) {
        em.remove(em.merge(ingrediente));
    }
    
    
}
