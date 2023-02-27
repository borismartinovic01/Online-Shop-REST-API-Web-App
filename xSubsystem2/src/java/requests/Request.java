
package requests;

import java.io.Serializable;

public class Request implements Serializable{
    
    private int a;
    private int b;
    
    public Request(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
    
}
