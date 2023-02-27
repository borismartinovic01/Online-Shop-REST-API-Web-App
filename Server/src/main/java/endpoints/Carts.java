
package endpoints;

import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import requests.ArticleRequest;
import requests.ContainsListRequest;
import requests.ContainsRequest;
import requests.StringRequest;
import resources.TestClass;

@Path("carts")
@Stateless
public class Carts {
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
    @Path("add")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response addToCart(ContainsRequest cr){
        System.out.println("Usao9");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(cr);
            om.setIntProperty("idR", 9);
            producer.send(queue2, om);
            System.out.println("Poslao arikal u korpu");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            StringRequest sr = (StringRequest)odg.getObject();
            if(sr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal ne postoji").build();
            }
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Artikal dodat u korpu").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal nije dodat u korpu").build();
    }
    
    @POST
    @Path("remove")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response removeFromCart(ContainsRequest cr){
        System.out.println("Usao10");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(cr);
            om.setIntProperty("idR", 10);
            producer.send(queue2, om);
            System.out.println("Poslao da se ukloni artikal");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            StringRequest sr = (StringRequest)odg.getObject();
            if(sr.getVal().equals("error1")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal ne postoji").build();
            }
            if(sr.getVal().equals("error2")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal nije u korpi").build();
            }
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Artikal uklonjen iz korpe").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Artikal nije uklonjen iz korpe").build();
    }
    
    @GET
    @Path("{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getMyCart(@PathParam("username") String username){
        System.out.println("Usao16");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ArticleRequest ar = new ArticleRequest();
            ar.setName(username);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 16);
            producer.send(queue2, om);
            System.out.println("Poslao zahtev za sadrzaj korpe");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            ContainsListRequest cr = (ContainsListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti sadrzaj korpe").build();
    }
}
