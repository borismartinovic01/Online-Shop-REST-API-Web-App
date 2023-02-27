package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Contains;
import resources.entities.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:35:35")
@StaticMetamodel(Cart.class)
public class Cart_ { 

    public static volatile SingularAttribute<Cart, Double> price;
    public static volatile ListAttribute<Cart, Contains> containsList;
    public static volatile SingularAttribute<Cart, Integer> idcart;
    public static volatile SingularAttribute<Cart, User> user;

}