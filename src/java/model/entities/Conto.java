/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 *
 * @author ciro
 */
public class Conto {
    
    private String tavolo;
    private String metodo;
    private String totale;

    
    public Conto() {
    }

    public Conto(String tavolo, String metodo) {
        this.tavolo = tavolo;
        this.metodo = metodo;
        this.totale = totale;
    }

    public String getTavolo() {
        return tavolo;
    }

    public void setTavolo(String tavolo) {
        this.tavolo = tavolo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getTotale() {
        return totale;
    }

    public void setTotale(String totale) {
        this.totale = totale;
    }
    
    
}
