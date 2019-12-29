/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ciro
 */
@Entity
@Table(name = "piatto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piatto.findAll", query = "SELECT p FROM Piatto p"),
    @NamedQuery(name = "Piatto.findById", query = "SELECT p FROM Piatto p WHERE p.id = :id"),
    @NamedQuery(name = "Piatto.findByCategoria", query = "SELECT p FROM Piatto p WHERE p.categoria = :categoria"),
    @NamedQuery(name = "Piatto.findCategorie", query = "SELECT DISTINCT p.categoria FROM Piatto p"),
    @NamedQuery(name = "Piatto.findByIds", query = "SELECT p FROM Piatto p WHERE p.id IN :ids")})
public class Piatto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nome")
    private String nome;
    
    @Size(max = 50)
    @Column(name = "foto")
    private String foto;
    
    @Column(name = "prezzo_cent")
    private Integer prezzoCent;
    
    @Size(max = 30)
    @Column(name = "categoria")
    private String categoria;
    
    @JoinTable(name = "piattoXing_rem", joinColumns = {
        @JoinColumn(name = "id_piatto", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ingrediente", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Ingrediente> ingredientiRimovibili;
    
    @JoinTable(name = "piattoXing_ins", joinColumns = {
        @JoinColumn(name = "id_piatto", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ingrediente", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Ingrediente> ingredientiAggiungibili;
    
    @JoinTable(name = "piattoXing", joinColumns = {
        @JoinColumn(name = "id_piatto", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ingrediente", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Ingrediente> ingredienti;

    public Piatto() {
    }

    public Piatto(Integer id) {
        this.id = id;
    }

    public Piatto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public void setPrezzo(Integer prezzoCent) {
        this.prezzoCent = prezzoCent;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @XmlTransient
    public Collection<Ingrediente> getIngredientiRimovibili() {
        return ingredientiRimovibili;
    }

    public void setIngredientiRimovibili(Collection<Ingrediente> ingredientiRimovibili) {
        this.ingredientiRimovibili = ingredientiRimovibili;
    }

    @XmlTransient
    public Collection<Ingrediente> getIngredientiAggiungibili() {
        return ingredientiAggiungibili;
    }

    public void setIngredientiAggiungibili(Collection<Ingrediente> ingredientiAggiungibili) {
        this.ingredientiAggiungibili = ingredientiAggiungibili;
    }

    @XmlTransient
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
