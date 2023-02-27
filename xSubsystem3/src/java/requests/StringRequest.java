
package requests;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringRequest implements Serializable{
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    
}
