package rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Constructor  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private String nationality;

    private String name;

    private String constructorId;

    private String url;

    public String getNationality ()
    {
        return nationality;
    }

    public void setNationality (String nationality)
    {
        this.nationality = nationality;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getConstructorId ()
    {
        return constructorId;
    }

    public void setConstructorId (String constructorId)
    {
        this.constructorId = constructorId;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "[nationality = "+nationality+", name = "+name+", constructorId = "+constructorId+", url = "+url+"]";
    }
}
