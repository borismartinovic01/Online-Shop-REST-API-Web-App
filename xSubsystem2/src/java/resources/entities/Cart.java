/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByIdcart", query = "SELECT c FROM Cart c WHERE c.idcart = :idcart"),
    @NamedQuery(name = "Cart.findByPrice", query = "SELECT c FROM Cart c WHERE c.price = :price")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcart")
    private Integer idcart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Contains> containsList;
    @JoinColumn(name = "idcart", referencedColumnName = "iduser", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Cart() {
    }

    public Cart(Integer idcart) {
        this.idcart = idcart;
    }

    public Cart(Integer idcart, double price) {
        this.idcart = idcart;
        this.price = price;
    }

    public Integer getIdcart() {
        return idcart;
    }

    public void setIdcart(Integer idcart) {
        this.idcart = idcart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public List<Contains> getContainsList() {
        return containsList;
    }

    public void setContainsList(List<Contains> containsList) {
        this.containsList = containsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcart != null ? idcart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.idcart == null && other.idcart != null) || (this.idcart != null && !this.idcart.equals(other.idcart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Cart[ idcart=" + idcart + " ]";
    }
    
}
