/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class PiattoFinale implements Serializable {
    
    private Integer idPiatto;
    private String nomePiatto;
    private List<String> ingredientiPiatto;

    public PiattoFinale(Integer idPiatto, String nomePiatto, List<String> ingredientiPiatto) {
        this.idPiatto = idPiatto;
        this.nomePiatto = nomePiatto;
        this.ingredientiPiatto = ingredientiPiatto;
    }

    public PiattoFinale() {
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

    public List<String> getIngredientiPiatto() {
        return ingredientiPiatto;
    }

    public void setIngredientiPiatto(List<String> ingredientiPiatto) {
        this.ingredientiPiatto = ingredientiPiatto;
    }
    
}
