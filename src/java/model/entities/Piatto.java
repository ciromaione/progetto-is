/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class Piatto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private String foto;
    private Integer prezzoCent;
    private String categoria;
    private Collection<Ingrediente> ingredientiRimovibili;
    private Collection<Ingrediente> ingredientiAggiungibili;
    private Collection<Ingrediente> ingredienti;

    public Piatto() {
    }

    public Piatto(String nome, String foto, Integer prezzo, String categoria, Collection<Ingrediente> ingredientiRimovibili, Collection<Ingrediente> ingredientiAggiungibili, Collection<Ingrediente> ingredienti) {
        this.nome = nome;
        this.foto = foto;
        this.prezzoCent = prezzo;
        this.categoria = categoria;
        this.ingredientiRimovibili = ingredientiRimovibili;
        this.ingredientiAggiungibili = ingredientiAggiungibili;
        this.ingredienti = ingredienti;
    }

    public Piatto(Integer id, String nome, String foto, Integer prezzoCent, String categoria, Collection<Ingrediente> ingredientiRimovibili, Collection<Ingrediente> ingredientiAggiungibili, Collection<Ingrediente> ingredienti) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.prezzoCent = prezzoCent;
        this.categoria = categoria;
        this.ingredientiRimovibili = ingredientiRimovibili;
        this.ingredientiAggiungibili = ingredientiAggiungibili;
        this.ingredienti = ingredienti;
    }

    public Piatto(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getPrezzoCent() {
        return prezzoCent;
    }

    public void setPrezzoCent(Integer prezzoCent) {
        this.prezzoCent = prezzoCent;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Collection<Ingrediente> getIngredientiRimovibili() {
        return ingredientiRimovibili;
    }

    public void setIngredientiRimovibili(Collection<Ingrediente> ingredientiRimovibili) {
        this.ingredientiRimovibili = ingredientiRimovibili;
    }

    public Collection<Ingrediente> getIngredientiAggiungibili() {
        return ingredientiAggiungibili;
    }

    public void setIngredientiAggiungibili(Collection<Ingrediente> ingredientiAggiungibili) {
        this.ingredientiAggiungibili = ingredientiAggiungibili;
    }

    public Collection<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(Collection<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getPrezzoString() {
        String price = Integer.toString(this.prezzoCent);
        int size = price.length();
        switch (size) {
            case 1:
                return "0,0" + price;
            case 2:
                return "0," + price;
            default:
                return price.substring(0, size-2)+","+price.substring(size-2);
        }
    }

    @Override
    public String toString() {
        return "Piatto{" + "id=" + id + ", nome=" + nome + ", foto=" + foto + ", prezzoCent=" + prezzoCent + ", categoria=" + categoria + ", ingredientiRimovibili=" + ingredientiRimovibili + ", ingredientiAggiungibili=" + ingredientiAggiungibili + ", ingredienti=" + ingredienti + '}';
    }
    
    
    
}
