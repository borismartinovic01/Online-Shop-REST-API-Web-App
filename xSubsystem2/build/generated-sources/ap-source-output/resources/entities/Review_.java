package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Article;
import resources.entities.ReviewPK;
import resources.entities.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:35:35")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Integer> grade;
    public static volatile SingularAttribute<Review, String> description;
    public static volatile SingularAttribute<Review, ReviewPK> reviewPK;
    public static volatile SingularAttribute<Review, User> user;
    public static volatile SingularAttribute<Review, Article> article;

}