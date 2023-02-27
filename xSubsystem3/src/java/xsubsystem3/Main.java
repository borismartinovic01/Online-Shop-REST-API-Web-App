
package xsubsystem3;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import requests.CityRequest;
import requests.ContainsListRequest;
import requests.ContainsRequest;
import requests.OrderRequest;
import requests.OrdersListRequest;
import requests.StringRequest;
import requests.UserRequest;
import resources.entities.Article;
import resources.entities.City;
import resources.entities.Item;
import resources.entities.Narudzbina;
import resources.entities.Transaction;
import resources.entities.User;

public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory cf;
    
    @Resource(lookup = "xxQueueR")
    private static Queue myQueueR;
    
    @Resource(lookup = "xxQueue3")
    private static Queue queue3;
    
    public static void main(String[] args) {
        JMSContext context = cf.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue3);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xSubsystem3PU");
        EntityManager em = emf.createEntityManager();
        
        while(true){
            System.out.println("Pokrenut subsystem3");
            Message msg = consumer.receive();
            System.out.println("Primljena poruka");
            if(msg instanceof ObjectMessage){
                try {
                    ObjectMessage om = (ObjectMessage)msg;
                    int code = om.getIntProperty("idR");
                    if(code == 1){
                        City city = new City();
                        CityRequest cr = (CityRequest)om.getObject();
                        city.setName(cr.getVal());
                        
                        em.getTransaction().begin();
                        em.persist(city);
                        em.getTransaction().commit();
                        
                        System.out.println(city.getName());
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
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
                        
                        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", ur.getCity()).getResultList();
                        
                        if(cities.isEmpty()){
                            cr.setVal("error2");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        City city = cities.get(0);
                        
                        User user = new User();
                        user.setAddress(ur.getAddress());
                        user.setBalance(ur.getBalance());
                        user.setCity(city);
                        user.setName(ur.getName());
                        user.setPassword(ur.getPassword());
                        user.setSurname(ur.getSurname());
                        user.setUsername(ur.getUsername());
                        
                        em.getTransaction().begin();
                        em.persist(user);
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
                        
                        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", ur.getCity()).getResultList();
                        
                        if(cities.isEmpty()){
                            cr.setVal("error1");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        City city = cities.get(0);
                        User user = list.get(0);
                        
                        em.getTransaction().begin();
                        user.setCity(city);
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
                        
                        Article article = new Article();
                        article.setDescription(ar.getDescription());
                        article.setDiscount(ar.getDiscount());
                        article.setName(ar.getName());
                        article.setPrice(ar.getPrice());
                        
                        em.getTransaction().begin();
                        em.persist(article);
                        em.getTransaction().commit();
                        
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
                    else if(code == 11){
                        
                        ContainsListRequest clr = (ContainsListRequest)om.getObject();
                        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", clr.getUsername()).getResultList();
                        if(users.isEmpty()){
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor");
                            continue;
                        }
                        
                        User user = users.get(0);
                        
                        ArrayList<Item> items = new ArrayList<>();
                        
                        Narudzbina n = new Narudzbina();
                        n.setAddress(user.getAddress());
                        n.setBuyer(user);
                        n.setCity(user.getCity());
                        n.setPrice(clr.getPrice());
                        n.setTime(new Date());
                        
                        System.out.println(user.getAddress());
                        System.out.print(user.getUsername());
                        System.out.println(user.getCity().getName());
                        System.out.println(clr.getPrice());
                        
                        Transaction transaction = new Transaction();
                        transaction.setPrice(clr.getPrice());
                        transaction.setTime(new Date());
                        
                        for(ContainsRequest cr: clr.getContainsRequests()){
                            Item item = new Item();
                            item.setAmount(cr.getAmount());
                            List<Article> articles = em.createNamedQuery("Article.findByName", Article.class).setParameter("name", cr.getArticle()).getResultList();
                            item.setPrice(articles.get(0).getPrice() * cr.getAmount() * (1 - articles.get(0).getDiscount()/100));
                            item.setArticle(articles.get(0));
                            items.add(item);
                        }
                        
                        try {
                        em.getTransaction().begin();
                        em.persist(n);
                        em.flush();
                        em.getTransaction().commit();
                        } catch (Exception e) {
                            StringRequest sr = new StringRequest();
                            sr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(sr);
                            producer.send(myQueueR, objOdg);
                            System.out.println("Poslat odgovor usao u catch novi");
                            continue;
                        }
                        
                        em.getTransaction().begin();
                        transaction.setNarudzbina(n);
                        transaction.setIdtransaction(n.getIdnarudzbina());
                        em.persist(transaction);
                        em.getTransaction().commit();
                        
                        
                        em.getTransaction().begin();
                        for(Item item: items){
                            item.setNarudzbina(n);
                            em.persist(item);
                        }
                        em.getTransaction().commit();
                        
                        System.out.println("Uneto sve u bazu");
                        StringRequest sr = new StringRequest();
                        sr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(sr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 17){
                        ArticleRequest ar = (ArticleRequest)om.getObject();
                        List<User> users = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ar.getName()).getResultList();
                        
                        User user = users.get(0);
                        
                        List<Narudzbina> narudzbine = user.getNarudzbinaList();
                        ArrayList<OrderRequest> requests = new ArrayList<>();
                        
                        for(Narudzbina narudzbina: narudzbine){
                            OrderRequest or = new OrderRequest();
                            or.setAddress(narudzbina.getAddress());
                            or.setCity(narudzbina.getCity().getName());
                            or.setId(narudzbina.getIdnarudzbina());
                            or.setPrice(narudzbina.getPrice());
                            or.setTime(narudzbina.getTime());
                            requests.add(or);
                        }
                        
                        System.out.println("dohvacene narudzbine iz baze");
                        OrdersListRequest clr = new OrdersListRequest();
                        clr.setOrders(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 18){
                        List<Narudzbina> narudzbine = em.createNamedQuery("Narudzbina.findAll", Narudzbina.class).getResultList();
                        ArrayList<OrderRequest> requests = new ArrayList<>();
                        
                        for(Narudzbina narudzbina: narudzbine){
                            OrderRequest or = new OrderRequest();
                            or.setAddress(narudzbina.getAddress());
                            or.setCity(narudzbina.getCity().getName());
                            or.setId(narudzbina.getIdnarudzbina());
                            or.setPrice(narudzbina.getPrice());
                            or.setTime(narudzbina.getTime());
                            requests.add(or);
                        }
                        
                        System.out.println("dohvacene sve narudzbine iz baze");
                        OrdersListRequest clr = new OrdersListRequest();
                        clr.setOrders(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    
                    else if(code == 19){
                        List<Transaction> transactions = em.createNamedQuery("Transaction.findAll", Transaction.class).getResultList();
                        ArrayList<OrderRequest> requests = new ArrayList<>();
                        
                        for(Transaction transaction: transactions){
                            OrderRequest or = new OrderRequest();
                            or.setId(transaction.getIdtransaction());
                            or.setPrice(transaction.getPrice());
                            or.setTime(transaction.getTime());
                            requests.add(or);
                        }
                        
                        System.out.println("dohvacene sve transakcije iz baze");
                        OrdersListRequest clr = new OrdersListRequest();
                        clr.setOrders(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                } 
                catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
