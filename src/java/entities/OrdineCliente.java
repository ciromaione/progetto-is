/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author ciro
 */
public class OrdineCliente {
    
   private String tavolo;
   private ArrayList<PiattoFinale> piatti;
   private Integer TotaleCent;

    public OrdineCliente(String tavolo, ArrayList<PiattoFinale> piatti, Integer TotaleCent) {
        this.tavolo = tavolo;
        this.piatti = piatti;
        this.TotaleCent = TotaleCent;
    }

    public String getTavolo() {
        return tavolo;
    }

    public void setTavolo(String tavolo) {
        this.tavolo = tavolo;
    }

    public ArrayList<PiattoFinale> getPiatti() {
        return piatti;
    }

    public void setPiatti(ArrayList<PiattoFinale> piatti) {
        this.piatti = piatti;
    }

    public Integer getTotaleCent() {
        return TotaleCent;
    }

    public void setTotaleCent(Integer TotaleCent) {
        this.TotaleCent = TotaleCent;
    }
   
   
}
