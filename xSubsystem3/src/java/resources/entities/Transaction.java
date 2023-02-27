/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByIdtransaction", query = "SELECT t FROM Transaction t WHERE t.idtransaction = :idtransaction"),
    @NamedQuery(name = "Transaction.findByPrice", query = "SELECT t FROM Transaction t WHERE t.price = :price"),
    @NamedQuery(name = "Transaction.findByTime", query = "SELECT t FROM Transaction t WHERE t.time = :time")})
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtransaction")
    private Integer idtransaction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "idtransaction", referencedColumnName = "idnarudzbina", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Narudzbina narudzbina;

    public Transaction() {
    }

    public Transaction(Integer idtransaction) {
        this.idtransaction = idtransaction;
    }

    public Transaction(Integer idtransaction, double price) {
        this.idtransaction = idtransaction;
        this.price = price;
    }

    public Integer getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(Integer idtransaction) {
        this.idtransaction = idtransaction;
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

    public Narudzbina getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransaction != null ? idtransaction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.idtransaction == null && other.idtransaction != null) || (this.idtransaction != null && !this.idtransaction.equals(other.idtransaction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Transaction[ idtransaction=" + idtransaction + " ]";
    }
    
}
