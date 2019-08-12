package rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CircuitTable {
	
	private String round;
	
	@JsonProperty("Circuits")
    private Circuit[] circuits;

    private String season;

    public String getRound ()
    {
        return round;
    }

    public void setRound (String round)
    {
        this.round = round;
    }

    public Circuit[] getCircuits ()
    {
        return circuits;
    }

    public void setCircuits (Circuit[] circuits)
    {
        this.circuits = circuits;
    }

    public String getSeason ()
    {
        return season;
    }

    public void setSeason (String season)
    {
        this.season = season;
    }

    @Override
    public String toString()
    {
        return "[round = "+round+", Circuits = "+circuits+", season = "+season+"]";
    }
}
