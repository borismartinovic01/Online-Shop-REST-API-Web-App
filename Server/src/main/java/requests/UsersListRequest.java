
package requests;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsersListRequest implements Serializable{
    
    ArrayList<UserRequest> users; 

    public ArrayList<UserRequest> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserRequest> users) {
        this.users = users;
    }
    
}
