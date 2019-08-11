package rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private String code;

    private String driverId;

    private String permanentNumber;

    private String nationality;

    private String givenName;

    private String familyName;

    private String dateOfBirth;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getDriverId ()
    {
        return driverId;
    }

    public void setDriverId (String driverId)
    {
        this.driverId = driverId;
    }

    public String getPermanentNumber ()
    {
        return permanentNumber;
    }

    public void setPermanentNumber (String permanentNumber)
    {
        this.permanentNumber = permanentNumber;
    }

    public String getNationality ()
    {
        return nationality;
    }

    public void setNationality (String nationality)
    {
        this.nationality = nationality;
    }

    public String getGivenName ()
    {
        return givenName;
    }

    public void setGivenName (String givenName)
    {
        this.givenName = givenName;
    }

    public String getFamilyName ()
    {
        return familyName;
    }

    public void setFamilyName (String familyName)
    {
        this.familyName = familyName;
    }

    public String getDateOfBirth ()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth (String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString()
    {
        return "[code = "+code+", driverId = "+driverId+", permanentNumber = "+permanentNumber+", nationality = "+nationality+", givenName = "+givenName+", familyName = "+familyName+", dateOfBirth = "+dateOfBirth+"]";
    }
}
