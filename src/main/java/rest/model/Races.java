package rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Races implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private String date;

    private String round;
    
    @JsonProperty("Results")
    private Results[] results;

    private String season;

    private String raceName;
    
    @JsonProperty("Circuit")
    private Circuit circuit;

    private String time;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getRound ()
    {
        return round;
    }

    public void setRound (String round)
    {
        this.round = round;
    }

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    public String getSeason ()
    {
        return season;
    }

    public void setSeason (String season)
    {
        this.season = season;
    }

    public String getRaceName ()
    {
        return raceName;
    }

    public void setRaceName (String raceName)
    {
        this.raceName = raceName;
    }

    public Circuit getCircuit ()
    {
        return circuit;
    }

    public void setCircuit (Circuit circuit)
    {
        this.circuit = circuit;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "[date = "+date+", round = "+round+", Results = "+results+", season = "+season+", raceName = "+raceName+", Circuit = "+circuit+", time = "+time+"]";
    }
}
