package rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverTable {
	
	private String round;
	
	@JsonProperty("Drivers")
    private Driver[] drivers;

    private String season;

    public String getRound ()
    {
        return round;
    }

    public void setRound (String round)
    {
        this.round = round;
    }

    public Driver[] getDrivers ()
    {
        return drivers;
    }

    public void setDrivers (Driver[] drivers)
    {
        this.drivers = drivers;
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
        return "[round = "+round+", Drivers = "+drivers+", season = "+season+"]";
    }
}
