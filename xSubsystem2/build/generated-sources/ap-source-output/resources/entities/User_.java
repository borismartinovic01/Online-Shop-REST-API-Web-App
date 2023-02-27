package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Article;
import resources.entities.Cart;
import resources.entities.Review;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:35:35")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Review> reviewList;
    public static volatile SingularAttribute<User, Integer> iduser;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, Double> balance;
    public static volatile ListAttribute<User, Article> articleList;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Cart> cart;
    public static volatile SingularAttribute<User, String> username;

}