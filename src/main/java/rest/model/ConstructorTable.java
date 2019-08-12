package rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorTable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String round;

    private String season;
    
    @JsonProperty("Constructors")
    private Constructor[] constructors;

    public String getRound ()
    {
        return round;
    }

    public void setRound (String round)
    {
        this.round = round;
    }

    public String getSeason ()
    {
        return season;
    }

    public void setSeason (String season)
    {
        this.season = season;
    }

    public Constructor[] getConstructors ()
    {
        return constructors;
    }

    public void setConstructors (Constructor[] constructors)
    {
        this.constructors = constructors;
    }

    @Override
    public String toString()
    {
        return "[round = "+round+", season = "+season+", Constructors = "+constructors+"]";
    }

}
