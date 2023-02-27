package resources.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Narudzbina;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:34:16")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Integer> idtransaction;
    public static volatile SingularAttribute<Transaction, Narudzbina> narudzbina;
    public static volatile SingularAttribute<Transaction, Double> price;
    public static volatile SingularAttribute<Transaction, Date> time;

}