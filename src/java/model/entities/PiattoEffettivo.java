/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class PiattoEffettivo implements Serializable {
    
    private Integer idPiatto;
    private String nomePiatto;
    private Integer quantita;
    private List<String> aggiunte;
    private List<String> rimozioni;

    public PiattoEffettivo(Integer idPiatto, String nomePiatto, Integer quantita, List<String> aggiunte, List<String> rimozioni) {
        this.idPiatto = idPiatto;
        this.nomePiatto = nomePiatto;
        this.quantita = quantita;
        this.aggiunte = aggiunte;
        this.rimozioni = rimozioni;
    }

    public PiattoEffettivo() {
    }

    public Integer getIdPiatto() {
        return idPiatto;
    }

    public void setIdPiatto(Integer idPiatto) {
        this.idPiatto = idPiatto;
    }

    public String getNomePiatto() {
        return nomePiatto;
    }

    public void setNomePiatto(String nomePiatto) {
        this.nomePiatto = nomePiatto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public List<String> getAggiunte() {
        return aggiunte;
    }

    public void setAggiunte(List<String> aggiunte) {
        this.aggiunte = aggiunte;
    }

    public List<String> getRimozioni() {
        return rimozioni;
    }

    public void setRimozioni(List<String> rimozioni) {
        this.rimozioni = rimozioni;
    }

    @Override
    public String toString() {
        return "PiattoEffettivo{" + "idPiatto=" + idPiatto + ", nomePiatto=" + nomePiatto + ", quantita=" + quantita + ", aggiunte=" + aggiunte + ", rimozioni=" + rimozioni + '}';
    }
    
}
