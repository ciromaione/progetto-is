/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.Objects;

/**
 *
 * @author ciro
 */
public class Conto {
    
    public enum Metodo {
        CONTANTI,
        CARTA
    }
    
    private String tavolo;
    private Metodo metodo;

    public Conto(String tavolo, Metodo metodo) {
        this.tavolo = tavolo;
        this.metodo = metodo;
    }

    public String getTavolo() {
        return tavolo;
    }

    public void setTavolo(String tavolo) {
        this.tavolo = tavolo;
    }

    public Metodo getMetodo() {
        return metodo;
    }

    public void setMetodo(Metodo metodo) {
        this.metodo = metodo;
    }
    
}
