
package com.mycompany.xclient;

import java.util.Base64;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import requests.*;
import resources.TestClass;

public class XClient{
        
        private String currUsername;
        private String currPassword;
        private Scanner inScanner = new Scanner(System.in);
        
        private Scanner getScanner(){
            return inScanner;
        }
        
        public void setClient(){
            System.out.println("Enter username: ");
            currUsername = inScanner.nextLine();
            System.out.println("Enter password: ");
            currPassword = inScanner.nextLine();
        }
        
        public void createCity(){
            System.out.println("Enter name: ");
            String name = inScanner.nextLine();
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/cities")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(new CityRequest(name), MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
//        public void createCity(){
//            System.out.println("Enter name: ");
//            String name = inScanner.nextLine();
//            
//            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
//            Client client = ClientBuilder.newClient();
//            Response res = client.target("http://localhost:8080/Server/api/cities")
//                                .request().header("Authorization", auth)
//                                .post(Entity.entity(new TestClass(), MediaType.APPLICATION_JSON), Response.class);
//            System.out.println(res);
//        }
        
        public void createUser(){
            System.out.println("Enter username: ");
            String username = inScanner.nextLine();
            System.out.println("Enter password: ");
            String password = inScanner.nextLine();
            System.out.println("Enter name: ");
            String name = inScanner.nextLine();
            System.out.println("Enter surname: ");
            String surname = inScanner.nextLine();
            System.out.println("Enter address: ");
            String address = inScanner.nextLine();
            System.out.println("Enter city: ");
            String city = inScanner.nextLine();
            System.out.println("Enter balance: ");
            double balance = Double.parseDouble(inScanner.nextLine());
            
            UserRequest ur = new UserRequest();
            ur.setAddress(address);
            ur.setCity(city);
            ur.setName(name);
            ur.setPassword(password);
            ur.setSurname(surname);
            ur.setUsername(username);
            ur.setBalance(balance);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(ur, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void addMoney(){
            System.out.println("Enter amount: ");
            double amount = Double.parseDouble(inScanner.nextLine());
            
            UserRequest ur = new UserRequest();
            ur.setUsername(currUsername);
            ur.setBalance(amount);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users/addamount")
                                .request().header("Authorization", auth)
                                .put(Entity.entity(ur, MediaType.APPLICATION_JSON), Response.class);
            
            System.out.println(res.readEntity(String.class));
        }
        public void setAddressAndCity(){
            System.out.println("Enter address: ");
            String address = inScanner.nextLine();
            System.out.println("Enter city: ");
            String city = inScanner.nextLine();
            
            UserRequest ur = new UserRequest();
            ur.setUsername(currUsername);
            ur.setAddress(address);
            ur.setCity(city);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users/setaddress")
                                .request().header("Authorization", auth)
                                .put(Entity.entity(ur, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void createCategory(){
            System.out.println("Enter name: ");
            String name = inScanner.nextLine();
            
            ArticleRequest ar = new ArticleRequest();
            ar.setCategoryName(name);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/articles/category")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(ar, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void createArticle(){
            System.out.println("Enter name: ");
            String name = inScanner.nextLine();
            System.out.println("Enter price: ");
            double price = Double.parseDouble(inScanner.nextLine());
            System.out.println("Enter description: ");
            String description = inScanner.nextLine();
            System.out.println("Enter discount: ");
            double discount = Double.parseDouble(inScanner.nextLine());
            System.out.println("Enter category: ");
            String categoryName = inScanner.nextLine();
            
            ArticleRequest ar = new ArticleRequest();
            ar.setCategoryName(categoryName);
            ar.setDescription(description);
            ar.setDiscount(discount);
            ar.setName(name);
            ar.setPrice(price);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/articles")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(ar, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void setPrice(){
            System.out.println("Enter article: ");
            String article = inScanner.nextLine();
            System.out.println("Enter price: ");
            double price = Double.parseDouble(inScanner.nextLine());
            
            ArticleRequest ar = new ArticleRequest();
            ar.setName(article);
            ar.setPrice(price);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/articles/price")
                                .request().header("Authorization", auth)
                                .put(Entity.entity(ar, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void setDiscount(){
            System.out.println("Enter article: ");
            String article = inScanner.nextLine();
            System.out.println("Enter discount: ");
            double discount = Double.parseDouble(inScanner.nextLine());
            
            ArticleRequest ar = new ArticleRequest();
            ar.setName(article);
            ar.setDiscount(discount);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/articles/discount")
                                .request().header("Authorization", auth)
                                .put(Entity.entity(ar, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void addToCart(){
            System.out.println("Enter article: ");
            String article = inScanner.nextLine();
            System.out.println("Enter amount: ");
            int amount = Integer.parseInt(inScanner.nextLine());
            
            ContainsRequest cr = new ContainsRequest();
            cr.setAmount(amount);
            cr.setArticle(article);
            cr.setUsername(currUsername);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/carts/add")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(cr, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void removeFromCart(){
            System.out.println("Enter article: ");
            String article = inScanner.nextLine();
            System.out.println("Enter amount: ");
            int amount = Integer.parseInt(inScanner.nextLine());
            
            ContainsRequest cr = new ContainsRequest();
            cr.setAmount(amount);
            cr.setArticle(article);
            cr.setUsername(currUsername);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/carts/remove")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(cr, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void pay(){
            UserRequest ur = new UserRequest();
            ur.setUsername(currUsername);
            
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users/pay")
                                .request().header("Authorization", auth)
                                .post(Entity.entity(ur, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(res.readEntity(String.class));
        }
        public void viewAllCities(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/cities")
                                .request().header("Authorization", auth)
                                .get();
            CitiesListRequest clr = res.readEntity(CitiesListRequest.class);
            for (CityRequest city : clr.getCities()) {
                System.out.println(city.getVal());
            }
        }
        public void viewAllUsers(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users")
                                .request().header("Authorization", auth)
                                .get();
            UsersListRequest clr = res.readEntity(UsersListRequest.class);
            for (UserRequest user: clr.getUsers()) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Name: " + user.getName());
                System.out.println("Surname: " + user.getSurname());
                System.out.println("Address: " + user.getAddress());
                System.out.println("City: " + user.getCity());
                System.out.println("Balance: " + user.getBalance());
                System.out.println("------------------");
            }
        }
        public void viewAllCategories(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/articles/category")
                                .request().header("Authorization", auth)
                                .get();
            ArticlesListRequest alr = res.readEntity(ArticlesListRequest.class);
            for (ArticleRequest ar: alr.getArticles()) {
                System.out.println(ar.getCategoryName());
            }
        }
        public void viewMyProducts(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            String targetp = "http://localhost:8080/Server/api/articles/selling/" + currUsername;
            Response res = client.target(targetp)
                                .request().header("Authorization", auth)
                                .get();
            ArticlesListRequest alr = res.readEntity(ArticlesListRequest.class);
            if(alr == null) return;
            if(alr.getArticles() == null) return;
            for (ArticleRequest ar: alr.getArticles()) {
                System.out.println("Name: " + ar.getName());
                System.out.println("Price: " + ar.getPrice());
                System.out.println("Description: " + ar.getDescription());
                System.out.println("Category: " + ar.getCategoryName());
                System.out.println("Discount: " + ar.getDiscount());
                System.out.println("------------------");
            }
        }
        public void viewMyCart(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            String targetp = "http://localhost:8080/Server/api/carts/" + currUsername;
            Response res = client.target(targetp)
                                .request().header("Authorization", auth)
                                .get();
            ContainsListRequest clr = res.readEntity(ContainsListRequest.class);
            if(clr == null) return;
            if(clr.getContainsRequests() == null) return;
            for(ContainsRequest cr: clr.getContainsRequests()){
                System.out.println("Article: " + cr.getArticle());
                System.out.println("Amount: " + cr.getAmount());
                System.out.println("------------------");
            }
            System.out.println("Ukupna cena: " + clr.getPrice());
        }
        public void viewMyOrders(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            String targetp = "http://localhost:8080/Server/api/users/myorders/" + currUsername;
            Response res = client.target(targetp)
                                .request().header("Authorization", auth)
                                .get();
            OrdersListRequest clr = res.readEntity(OrdersListRequest.class);
            if(clr == null) return;
            if(clr.getOrders() == null) return;
            for(OrderRequest cr: clr.getOrders()){
                System.out.println("ID: " + cr.getId());
                System.out.println("Price: " + cr.getPrice());
                System.out.println("Time: " + cr.getTime());
                System.out.println("City: " + cr.getCity());
                System.out.println("Address: " + cr.getAddress());
                System.out.println("------------------");
            }
        }
        public void viewAllOrders(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users/orders")
                                .request().header("Authorization", auth)
                                .get();
            OrdersListRequest clr = res.readEntity(OrdersListRequest.class);
            for(OrderRequest cr: clr.getOrders()){
                System.out.println("ID: " + cr.getId());
                System.out.println("Price: " + cr.getPrice());
                System.out.println("Time: " + cr.getTime());
                System.out.println("City: " + cr.getCity());
                System.out.println("Address: " + cr.getAddress());
                System.out.println("------------------");
            }
        }
        public void viewAllTransactions(){
            String auth = "Basic " + Base64.getEncoder().encodeToString((currUsername + ":" + currPassword).getBytes());
            Client client = ClientBuilder.newClient();
            Response res = client.target("http://localhost:8080/Server/api/users/transactions")
                                .request().header("Authorization", auth)
                                .get();
            OrdersListRequest clr = res.readEntity(OrdersListRequest.class);
            for(OrderRequest cr: clr.getOrders()){
                System.out.println("ID: " + cr.getId());
                System.out.println("Price: " + cr.getPrice());
                System.out.println("Time: " + cr.getTime());
                System.out.println("------------------");
            }
        }
        
	public static void main(String[] args){
            
            String option;
            boolean loop = true;
            XClient client = new XClient();
            client.setClient();
            
            while(loop){
                    
                System.out.println("Choose an option: ");
                System.out.println("0. Change user");
                System.out.println("1. Create city");
                System.out.println("2. Create user");
                System.out.println("3. Add money");
                System.out.println("4. Set address and city");
                System.out.println("5. Create category");
                System.out.println("6. Create article");
                System.out.println("7. Set price");
                System.out.println("8. Set discount");
                System.out.println("9. Add to cart");
                System.out.println("10. Remove from cart");
                System.out.println("11. Pay");
                System.out.println("12. View all cities");
                System.out.println("13. View all users");
                System.out.println("14. View all categories");
                System.out.println("15. View my products");
                System.out.println("16. View my cart");
                System.out.println("17. View my orders");
                System.out.println("18. View all orders");
                System.out.println("19. View all transactions");
                System.out.println("20. Quit");
                
                option = client.getScanner().nextLine();
                
                switch(option){
                    case "0":
                        client.setClient();
                        break;
                    case "1":
                        client.createCity();
                        break;
                    case "2":
                        client.createUser();
                        break;
                    case "3":
                        client.addMoney();
                        break;
                    case "4":
                        client.setAddressAndCity();
                        break;
                    case "5":
                        client.createCategory();
                        break;
                    case "6":
                        client.createArticle();
                        break;
                    case "7":
                        client.setPrice();
                        break;
                    case "8":
                        client.setDiscount();
                        break;
                    case "9":
                        client.addToCart();
                        break;
                    case "10":
                        client.removeFromCart();
                        break;
                    case "11":
                        client.pay();
                        break;
                    case "12":
                        client.viewAllCities();
                        break;
                    case "13":
                        client.viewAllUsers();
                        break;
                    case "14":
                        client.viewAllCategories();
                        break;
                    case "15":
                        client.viewMyProducts();
                        break;
                    case "16":
                        client.viewMyCart();
                        break;
                    case "17":
                        client.viewMyOrders();
                        break;
                    case "18":
                        client.viewAllOrders();
                        break;
                    case "19":
                        client.viewAllTransactions();
                        break;
                    case "20":
                        loop = false;
                        break;
                    default:
                        break;
                }
            }
	}
    
}
