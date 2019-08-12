package rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MRData {
	
	@JsonProperty("RaceTable")
	private RaceTable raceTable;
	
	@JsonProperty("DriverTable")
	private DriverTable driverTable;
	
	@JsonProperty("CircuitTable")
	private CircuitTable circuitTable;
	
    public RaceTable getRaceTable ()
    {
        return raceTable;
    }

    public void setRaceTable (RaceTable raceTable)
    {
        this.raceTable = raceTable;
    }
    
    public DriverTable getDriverTable() {
		return driverTable;
	}

	public void setDriverTable(DriverTable driverTable) {
		this.driverTable = driverTable;
	}
	
	public CircuitTable getCircuitTable ()
    {
        return circuitTable;
    }

    public void setCircuitTable (CircuitTable circuitTable)
    {
        this.circuitTable = circuitTable;
    }

}
