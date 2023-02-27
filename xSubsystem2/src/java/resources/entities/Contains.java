/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "contains")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contains.findAll", query = "SELECT c FROM Contains c"),
    @NamedQuery(name = "Contains.findByIdcart", query = "SELECT c FROM Contains c WHERE c.containsPK.idcart = :idcart"),
    @NamedQuery(name = "Contains.findByIdarticle", query = "SELECT c FROM Contains c WHERE c.containsPK.idarticle = :idarticle"),
    @NamedQuery(name = "Contains.findByAmount", query = "SELECT c FROM Contains c WHERE c.amount = :amount")})
public class Contains implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContainsPK containsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "idarticle", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idcart", referencedColumnName = "idcart", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;

    public Contains() {
    }

    public Contains(ContainsPK containsPK) {
        this.containsPK = containsPK;
    }

    public Contains(ContainsPK containsPK, int amount) {
        this.containsPK = containsPK;
        this.amount = amount;
    }

    public Contains(int idcart, int idarticle) {
        this.containsPK = new ContainsPK(idcart, idarticle);
    }

    public ContainsPK getContainsPK() {
        return containsPK;
    }

    public void setContainsPK(ContainsPK containsPK) {
        this.containsPK = containsPK;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (containsPK != null ? containsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contains)) {
            return false;
        }
        Contains other = (Contains) object;
        if ((this.containsPK == null && other.containsPK != null) || (this.containsPK != null && !this.containsPK.equals(other.containsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Contains[ containsPK=" + containsPK + " ]";
    }
    
}
