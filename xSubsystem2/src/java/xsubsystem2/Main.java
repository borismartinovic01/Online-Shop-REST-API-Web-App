
package xsubsystem2;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import requests.ArticleRequest;
import requests.ArticlesListRequest;
import requests.CityRequest;
import requests.ContainsListRequest;
import requests.ContainsRequest;
import requests.StringRequest;
import requests.UserRequest;
import resources.entities.Article;
import resources.entities.Cart;
import resources.entities.Category;
import resources.entities.Contains;
import resources.entities.ContainsPK;
import resources.entities.User;

public class Main {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory cf;
    
    @Resource(lookup = "xxQueueR")
    private static Queue myQueueR;
    
    @Resource(lookup = "xxQueue2")
    private static Queue queue2;
    
    public static void main(String[] args) {
        JMSContext context = cf.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue2);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xSubsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        while(true){
            System.out.println("Pokrenut subsystem2");
            Message msg = consumer.receive();
            System.out.println("Primljena poruka");
            if(msg instanceof ObjectMessage){
                try {
                    ObjectMessage om = (ObjectMessage)msg;
                    int code = om.getIntProperty("idR");
                    if(code == 5){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<Category> categories = em.createNamedQuery("Category.findByName", Category.class).setParameter("name", ar.getCategoryName()).getResultList();
                        if(!categories.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        Category category = new Category();
                        category.setName(ar.getCategoryName());
                        
                        em.getTransaction().begin();
                        em.persist(category);
                        em.getTransaction().commit();
                        
                        System.out.println(category.getName());
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");

                    }
                    else if(code == 2){
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(!list.isEmpty()){
                            cr.setVal("error1");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        User user = new User();
                        user.setAddress(ur.getAddress());
                        user.setBalance(ur.getBalance());
                        user.setName(ur.getName());
                        user.setPassword(ur.getPassword());
                        user.setSurname(ur.getSurname());
                        user.setUsername(ur.getUsername());
                        
                        Cart cart = new Cart();
                        
                        
                        em.getTransaction().begin();
                        em.persist(user);
                        em.flush();
                        em.getTransaction().commit();
                        
                        em.getTransaction().begin();
                        cart.setPrice(0);
                        cart.setIdcart(user.getIduser());
                        em.persist(cart);
                        em.getTransaction().commit();
                        
                        System.out.println(user.getName() + " unet u bazu");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 3){
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(list.isEmpty()){
                            cr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        User user = list.get(0);
                        
                        em.getTransaction().begin();
                        user.setBalance(user.getBalance() + ur.getBalance());
                        em.getTransaction().commit();
                        
                        System.out.println(ur.getBalance() + " dodato ovoliko novca u bazu");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 4){
                        
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(list.isEmpty()){
                            cr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        User user = list.get(0);
                        
                        em.getTransaction().begin();
                        user.setAddress(ur.getAddress());
                        em.getTransaction().commit();
                        
                        System.out.println("dodata adresa i grad u bazu");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                        
                    }
                    else if(code == 6){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<Category> categories = em.createNamedQuery("Category.findByName", Category.class).setParameter("name", ar.getCategoryName()).getResultList();
                        if(categories.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        Category category = categories.get(0);
                        
                        Article article = new Article();
                        article.setCategory(category);
                        article.setDescription(ar.getDescription());
                        article.setDiscount(ar.getDiscount());
                        article.setName(ar.getName());
                        article.setPrice(ar.getPrice());
                        
                        em.getTransaction().begin();
                        em.persist(article);
                        em.getTransaction().commit();
                        
                        System.out.println(category.getName());
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 7){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<Article> articles = em.createNamedQuery("Article.findByName", Article.class).setParameter("name", ar.getName()).getResultList();
                        if(articles.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        Article article = articles.get(0);
                        
                        em.getTransaction().begin();
                        article.setPrice(ar.getPrice());
                        em.getTransaction().commit();
                        
                        System.out.println(ar.getPrice());
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 8){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<Article> articles = em.createNamedQuery("Article.findByName", Article.class).setParameter("name", ar.getName()).getResultList();
                        if(articles.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        Article article = articles.get(0);
                        
                        em.getTransaction().begin();
                        article.setDiscount(ar.getDiscount());
                        em.getTransaction().commit();
                        
                        System.out.println(ar.getPrice());
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 9){
                        ContainsRequest cr = (ContainsRequest)om.getObject();
                        List<Article> articles = em.createNamedQuery("Article.findByName", Article.class).setParameter("name", cr.getArticle()).getResultList();
                        if(articles.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", cr.getUsername()).getResultList();
                        
                        User user = users.get(0);
                        List<Cart> carts = em.createNamedQuery("Cart.findByIdcart", Cart.class).setParameter("idcart", user.getIduser()).getResultList();
                        Cart cart = carts.get(0);
                        
                        Article article = articles.get(0);
                        
                        Contains contains = em.find(Contains.class, new ContainsPK(user.getIduser(), article.getIdarticle()));
                        
                        if(contains == null){
                            contains = new Contains();
                            contains.setAmount(cr.getAmount());
                            contains.setContainsPK(new ContainsPK(user.getIduser(), article.getIdarticle()));
                            contains.setCart(cart);
                            contains.setArticle(article);
                            em.getTransaction().begin();
                            cart.setPrice(cart.getPrice() + cr.getAmount() * article.getPrice()*(1 - article.getDiscount()/100));
                            em.persist(contains);
                            em.getTransaction().commit();
                        }
                        else{
                            em.getTransaction().begin();
                            contains.setAmount(contains.getAmount() + cr.getAmount());
                            cart.setPrice(cart.getPrice() + cr.getAmount() * article.getPrice()*(1 - article.getDiscount()/100));
                            em.getTransaction().commit();
                        }
                        
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 10){
                        ContainsRequest cr = (ContainsRequest)om.getObject();
                        List<Article> articles = em.createNamedQuery("Article.findByName", Article.class).setParameter("name", cr.getArticle()).getResultList();
                        if(articles.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error1");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", cr.getUsername()).getResultList();
                        
                        User user = users.get(0);
                        List<Cart> carts = em.createNamedQuery("Cart.findByIdcart", Cart.class).setParameter("idcart", user.getIduser()).getResultList();
                        Cart cart = carts.get(0);
                        
                        Article article = articles.get(0);
                        
                        Contains contains = em.find(Contains.class, new ContainsPK(user.getIduser(), article.getIdarticle()));
                        
                        if(contains == null){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error2");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        int correctAmount = contains.getAmount();
                        em.getTransaction().begin();
                        contains.setAmount(contains.getAmount() - cr.getAmount());
                        if(correctAmount < cr.getAmount()){
                            cart.setPrice(cart.getPrice() - correctAmount * article.getPrice()*(1 - article.getDiscount()/100));
                        }
                        else{
                            cart.setPrice(cart.getPrice() - cr.getAmount() * article.getPrice()*(1 - article.getDiscount()/100));
                        }
                        em.getTransaction().commit();
                        
                        if(contains.getAmount() <= 0){
                            em.getTransaction().begin();
                            em.remove(contains);
                            em.getTransaction().commit();
                        }
                        
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 14){
                        List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
                        ArrayList<ArticleRequest> requests = new ArrayList<>();
                        for (Category category: categories) {
                            ArticleRequest articleRequest = new ArticleRequest();
                            articleRequest.setCategoryName(category.getName());
                            requests.add(articleRequest);
                        }
                        
                        System.out.println("dohvacene kategorije iz baze");
                        ArticlesListRequest clr = new ArticlesListRequest();
                        clr.setArticles(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 15){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ar.getName()).getResultList();
                        
                        User user = users.get(0);
                        
                        List<Article> articles = user.getArticleList();
                        ArrayList<ArticleRequest> requests = new ArrayList<>();
                        
                        for(Article article: articles){
                            ArticleRequest arr = new ArticleRequest();
                            arr.setCategoryName(article.getCategory().getName());
                            arr.setDescription(article.getDescription());
                            arr.setDiscount(article.getDiscount());
                            arr.setName(article.getName());
                            arr.setPrice(article.getPrice());
                            requests.add(arr);
                        }
                        
                        System.out.println("dohvaceni proizvodi iz baze");
                        ArticlesListRequest clr = new ArticlesListRequest();
                        clr.setArticles(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 16){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ar.getName()).getResultList();
                        
                        User user = users.get(0);
                        
                        List<Cart> carts = em.createNamedQuery("Cart.findByIdcart", Cart.class).setParameter("idcart", user.getIduser()).getResultList();
                        Cart cart = carts.get(0);
                        
                        ContainsListRequest clr = new ContainsListRequest();
                        ArrayList<ContainsRequest> requests = new ArrayList<>();
                        
                        List<Contains> containsList = em.createNamedQuery("Contains.findByIdcart", Contains.class).setParameter("idcart", user.getIduser()).getResultList();
                        for (Contains contains : containsList) {
                            ContainsRequest cr = new ContainsRequest();
                            cr.setAmount(contains.getAmount());
                            cr.setArticle(contains.getArticle().getName());
                            requests.add(cr);
                        }
                        
                        System.out.println("dohvacen sadrzaj korpe iz baze");
                        
                        clr.setContainsRequests(requests);
                        clr.setPrice(cart.getPrice());
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 20){
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(list.isEmpty()){
                            cr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        User user = list.get(0);
                        
                        List<Cart> carts = em.createNamedQuery("Cart.findByIdcart", Cart.class).setParameter("idcart", user.getIduser()).getResultList();
                        Cart cart = carts.get(0);
                        
                        List<Contains> containsList = em.createNamedQuery("Contains.findByIdcart", Contains.class).setParameter("idcart", user.getIduser()).getResultList();
                        
                        em.getTransaction().begin();
                        for (Contains contains : containsList){
                            em.remove(contains);
                        }
                        cart.setPrice(0);
                        em.getTransaction().commit();
                        
                        System.out.println("Resetovana korpa u bazi");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                        
                    }
//                    else if(code == 2){
//                        UserRequest ur = (UserRequest)om.getObject();
//                        CityRequest cr = new CityRequest();
//                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
//                        if(!list.isEmpty()){
//                            cr.setVal("error1");
//                            ObjectMessage objOdg = context.createObjectMessage(cr);
//                            producer.send(myQueueR, objOdg);
//                            continue;
//                        }
//                        
//                        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", ur.getCity()).getResultList();
//                        
//                        if(cities.isEmpty()){
//                            cr.setVal("error2");
//                            ObjectMessage objOdg = context.createObjectMessage(cr);
//                            producer.send(myQueueR, objOdg);
//                            continue;
//                        }
//                        
//                        City city = cities.get(0);
//                        
//                        User user = new User();
//                        user.setAddress(ur.getAddress());
//                        user.setBalance(ur.getBalance());
//                        user.setCity(city);
//                        user.setName(ur.getName());
//                        user.setPassword(ur.getPassword());
//                        user.setSurname(ur.getSurname());
//                        user.setUsername(ur.getUsername());
//                        
//                        em.getTransaction().begin();
//                        em.persist(user);
//                        em.getTransaction().commit();
//                        
//                        System.out.println(user.getName() + " unet u bazu");
//                        cr.setVal("OK");
//                        ObjectMessage objOdg = context.createObjectMessage(cr);
//                        producer.send(myQueueR, objOdg);
//                        System.out.println("Poslat odgovor");
//                    }
//                    else if(code == 3){
//                        UserRequest ur = (UserRequest)om.getObject();
//                        CityRequest cr = new CityRequest();
//                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
//                        if(list.isEmpty()){
//                            cr.setVal("error");
//                            ObjectMessage objOdg = context.createObjectMessage(cr);
//                            producer.send(myQueueR, objOdg);
//                            continue;
//                        }
//                        
//                        User user = list.get(0);
//                        
//                        em.getTransaction().begin();
//                        user.setBalance(user.getBalance() + ur.getBalance());
//                        em.getTransaction().commit();
//                        
//                        System.out.println(ur.getBalance() + " dodato ovoliko novca u bazu");
//                        cr.setVal("OK");
//                        ObjectMessage objOdg = context.createObjectMessage(cr);
//                        producer.send(myQueueR, objOdg);
//                        System.out.println("Poslat odgovor");
//                    }
//                    else if(code == 4){
//                        
//                        UserRequest ur = (UserRequest)om.getObject();
//                        CityRequest cr = new CityRequest();
//                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
//                        if(list.isEmpty()){
//                            cr.setVal("error");
//                            ObjectMessage objOdg = context.createObjectMessage(cr);
//                            producer.send(myQueueR, objOdg);
//                            continue;
//                        }
//                        
//                        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", ur.getCity()).getResultList();
//                        
//                        if(cities.isEmpty()){
//                            cr.setVal("error1");
//                            ObjectMessage objOdg = context.createObjectMessage(cr);
//                            producer.send(myQueueR, objOdg);
//                            continue;
//                        }
//                        
//                        City city = cities.get(0);
//                        User user = list.get(0);
//                        
//                        em.getTransaction().begin();
//                        user.setCity(city);
//                        user.setAddress(ur.getAddress());
//                        em.getTransaction().commit();
//                        
//                        System.out.println("dodata adresa i grad u bazu");
//                        cr.setVal("OK");
//                        ObjectMessage objOdg = context.createObjectMessage(cr);
//                        producer.send(myQueueR, objOdg);
//                        System.out.println("Poslat odgovor");
//                        
//                    }
//                    else if(code == 12){
//                        List<City> cities = em.createNamedQuery("City.findAll", City.class).getResultList();
//                        ArrayList<CityRequest> requests = new ArrayList<>();
//                        for (City city : cities) {
//                            CityRequest cityRequest = new CityRequest();
//                            cityRequest.setVal(city.getName());
//                            requests.add(cityRequest);
//                        }
//                        
//                        System.out.println("dohvaceni gradovi iz baze");
//                        CitiesListRequest clr = new CitiesListRequest();
//                        clr.setCities(requests);
//                        ObjectMessage objOdg = context.createObjectMessage(clr);
//                        producer.send(myQueueR, objOdg);
//                        System.out.println("Poslat odgovor");
//                    }
//                    else if(code == 13){
//                        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
//                        ArrayList<UserRequest> requests = new ArrayList<>();
//                        for (User user: users) {
//                            UserRequest userRequest = new UserRequest();
//                            userRequest.setAddress(user.getAddress());
//                            userRequest.setBalance(user.getBalance());
//                            userRequest.setCity(user.getCity().getName());
//                            userRequest.setName(user.getName());
//                            userRequest.setPassword(user.getPassword());
//                            userRequest.setSurname(user.getSurname());
//                            userRequest.setUsername(user.getUsername());
//                            requests.add(userRequest);
//                        }
//                        
//                        System.out.println("dohvaceni korisnici iz baze");
//                        UsersListRequest clr = new UsersListRequest();
//                        clr.setUsers(requests);
//                        ObjectMessage objOdg = context.createObjectMessage(clr);
//                        producer.send(myQueueR, objOdg);
//                        System.out.println("Poslat odgovor");
//                    }
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
