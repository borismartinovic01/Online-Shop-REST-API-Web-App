package resources.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.City;
import resources.entities.Item;
import resources.entities.Transaction;
import resources.entities.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:34:16")
@StaticMetamodel(Narudzbina.class)
public class Narudzbina_ { 

    public static volatile SingularAttribute<Narudzbina, String> address;
    public static volatile SingularAttribute<Narudzbina, City> city;
    public static volatile SingularAttribute<Narudzbina, Double> price;
    public static volatile SingularAttribute<Narudzbina, Integer> idnarudzbina;
    public static volatile ListAttribute<Narudzbina, Item> itemList;
    public static volatile SingularAttribute<Narudzbina, Date> time;
    public static volatile SingularAttribute<Narudzbina, Transaction> transaction;
    public static volatile SingularAttribute<Narudzbina, User> buyer;

}