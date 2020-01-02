/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.entities.Ingrediente;
import model.entities.Ordine;
import model.entities.Piatto;

/**
 *
 * @author ciro
 */
@Stateless
public class TitolareManager {

    @Inject
    Connection conn;

    public TitolareManager() {
    }
    
    public void aggiungiPiatto(Piatto piatto) {
        
    }
    
    public void rimuoviPiatto(Integer id) {
        
    }
    
    public void aggiungiIngrediente(Ingrediente ingrediente) {
        
    }
    
    public List<Ordine> popolaritaPiattiMensile(int mese) {
        return null;
        
    }
    
    public List<Piatto> guadagnoGiornaliero(Date data) {
        return null;
    }
}
