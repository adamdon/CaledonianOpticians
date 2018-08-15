/**
 * @author adamdon <mail@adamdon.co.uk>
 */

package caledonianopticians;

public class User implements java.io.Serializable
{
    private String srtFirstName;
    private String srtLastName;
    private int intReference;
    private String srtAddress;
    
    User()
    {
        this.srtFirstName = "John";
        this.srtLastName = "Doe";
        this.intReference = 100000;
        this.srtAddress = "1 Street Name, City, G51 1SW";
    }
    
    User(String psrtFirstName, String psrtLastName, int pintReference, String psrtAddress)
    {
        this.srtFirstName = psrtFirstName;
        this.srtLastName = psrtLastName;
        this.intReference = pintReference;
        this.srtAddress = psrtAddress;
    }

    public String getSrtFirstName()
    {
        return srtFirstName;
    }

    public void setSrtFirstName(String srtFirstName)
    {
        this.srtFirstName = srtFirstName;
    }

    public String getSrtLastName()
    {
        return srtLastName;
    }

    public void setSrtLastName(String srtLastName)
    {
        this.srtLastName = srtLastName;
    }

    public int getIntReference()
    {
        return intReference;
    }

    public void setIntReference(int intReference)
    {
        this.intReference = intReference;
    }

    public String getSrtAddress()
    {
        return srtAddress;
    }

    public void setSrtAddress(String srtAddress)
    {
        this.srtAddress = srtAddress;
    }
    
    

}
