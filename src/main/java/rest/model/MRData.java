package rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MRData {
	
	@JsonProperty("RaceTable")
	private RaceTable raceTable;
 
    private String total;

    private String series;

    public RaceTable getRaceTable ()
    {
        return raceTable;
    }

    public void setRaceTable (RaceTable raceTable)
    {
        this.raceTable = raceTable;
    }

    public String getTotal () {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getSeries ()
    {
        return series;
    }

    public void setSeries (String series)
    {
        this.series = series;
    }

    @Override
    public String toString()
    {
        return "[RaceTable = "+raceTable.toString()+", series = "+series+", total = "+total+"]";
    }
}
