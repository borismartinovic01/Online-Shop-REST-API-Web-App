
package endpoints;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import requests.ArticleRequest;
import requests.ArticlesListRequest;
import requests.CityRequest;
import requests.StringRequest;
import resources.TestClass;

@Path("articles")
@Stateless
public class Articles {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;
    
    @Resource(lookup = "xxQueueR")
    private Queue myQueueR;
    
    @Resource(lookup = "xxQueue1")
    private Queue queue1;
    
    @Resource(lookup = "xxQueue2")
    private Queue queue2;
    
    @Resource(lookup = "xxQueue3")
    private Queue queue3;
    
    @POST
    @Path("category")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response createCategory(ArticleRequest ar){
        System.out.println("Usao5");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 5);
            producer.send(queue2, om);
            System.out.println("Poslao kategoriju");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            StringRequest cr = (StringRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Kategorija vec postoji").build();
            }
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Kategorija uspesno kreirana").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Kategorija nije kreirana").build();
    }
    
    @GET
    @Path("category")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getAllCategories(){
        System.out.println("Usao14");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(new ArticleRequest());
            om.setIntProperty("idR", 14);
            producer.send(queue2, om);
            System.out.println("Poslao zahtev za sve kategorije");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            ArticlesListRequest cr = (ArticlesListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti sve kategoije").build();
    }
    
    @GET
    @Path("selling/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getMyProducts(@PathParam("username") String username){
        System.out.println("Usao15");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ArticleRequest ar = new ArticleRequest();
            ar.setName(username);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 15);
            producer.send(queue2, om);
            System.out.println("Poslao zahtev za sve proizvode koje prodaje korisnik");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            ArticlesListRequest cr = (ArticlesListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti sve proizvode korisnika").build();
    }
    
    @POST
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response createArticle(ArticleRequest ar){
        System.out.println("Usao6");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 6);
            producer.send(queue2, om);
            System.out.println("Poslao artikal");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            StringRequest cr = (StringRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Kategorija ne postoji").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(ar);
            om2.setIntProperty("idR", 6);
            producer.send(queue3, om2);
            System.out.println("Poslao artikal2");
            Message msg2 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Artikal uspesno kreiran").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal nije kreiran").build();
    }
    
    @PUT
    @Path("price")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response setPrice(ArticleRequest ar){
        System.out.println("Usao7");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 7);
            producer.send(queue2, om);
            System.out.println("Poslao novu cenu");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            StringRequest cr = (StringRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal ne postoji").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(ar);
            om2.setIntProperty("idR", 7);
            producer.send(queue3, om2);
            System.out.println("Poslao novu cenu2");
            Message msg2 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Uspesno promenjena cena").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cena nije promenjena").build();
    }
    
    @PUT
    @Path("discount")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response setDiscount(ArticleRequest ar){
        System.out.println("Usao8");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 8);
            producer.send(queue2, om);
            System.out.println("Poslao novi popust");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            StringRequest cr = (StringRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal ne postoji").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(ar);
            om2.setIntProperty("idR", 8);
            producer.send(queue3, om2);
            System.out.println("Poslao novi popust2");
            Message msg2 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Uspesno promenjen popust").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Popust nije promenjen").build();
    }
}
