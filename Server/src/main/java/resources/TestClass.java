
package resources;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestClass implements Serializable{
    public int a = 21;
    public int b = 28;
    
}
