package rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	@JsonProperty("MRData")
	private MRData mrData;

	public MRData getMrData() {
		return mrData;
	}

	public void setMrData(MRData mrData) {
		this.mrData = mrData;
	}
}
