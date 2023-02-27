/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "narudzbina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbina.findAll", query = "SELECT n FROM Narudzbina n"),
    @NamedQuery(name = "Narudzbina.findByIdnarudzbina", query = "SELECT n FROM Narudzbina n WHERE n.idnarudzbina = :idnarudzbina"),
    @NamedQuery(name = "Narudzbina.findByAddress", query = "SELECT n FROM Narudzbina n WHERE n.address = :address"),
    @NamedQuery(name = "Narudzbina.findByPrice", query = "SELECT n FROM Narudzbina n WHERE n.price = :price"),
    @NamedQuery(name = "Narudzbina.findByTime", query = "SELECT n FROM Narudzbina n WHERE n.time = :time")})
public class Narudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnarudzbina")
    private Integer idnarudzbina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "narudzbina")
    private List<Item> itemList;
    @JoinColumn(name = "city", referencedColumnName = "idcity")
    @ManyToOne(optional = false)
    private City city;
    @JoinColumn(name = "buyer", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User buyer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "narudzbina")
    private Transaction transaction;

    public Narudzbina() {
    }

    public Narudzbina(Integer idnarudzbina) {
        this.idnarudzbina = idnarudzbina;
    }

    public Narudzbina(Integer idnarudzbina, String address, double price) {
        this.idnarudzbina = idnarudzbina;
        this.address = address;
        this.price = price;
    }

    public Integer getIdnarudzbina() {
        return idnarudzbina;
    }

    public void setIdnarudzbina(Integer idnarudzbina) {
        this.idnarudzbina = idnarudzbina;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnarudzbina != null ? idnarudzbina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbina)) {
            return false;
        }
        Narudzbina other = (Narudzbina) object;
        if ((this.idnarudzbina == null && other.idnarudzbina != null) || (this.idnarudzbina != null && !this.idnarudzbina.equals(other.idnarudzbina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Narudzbina[ idnarudzbina=" + idnarudzbina + " ]";
    }
    
}
