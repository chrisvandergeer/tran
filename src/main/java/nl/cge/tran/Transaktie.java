package nl.cge.tran;


import java.io.Serializable;
import java.util.Date;

public class Transaktie implements Serializable {
	
	private String rekeningnummer;
	private Date datum;
	private double bedrag;
	private String tegenrekeningnaam;
	private String tegenrekening;
	private String omschrijving1;
	private String omschrijving2;
	private String omschrijving3;
	private String omschrijving4;
	public String getRekeningnummer() {
		return rekeningnummer;
	}
	public void setRekeningnummer(String rekeningnummer) {
		this.rekeningnummer = rekeningnummer;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getBedrag() {
		return bedrag;
	}
	public void setBedrag(double bedrag) {
		this.bedrag = bedrag;
	}
	public String getTegenrekeningnaam() {
		return tegenrekeningnaam;
	}
	public void setTegenrekeningnaam(String tegenrekeningnaam) {
		this.tegenrekeningnaam = tegenrekeningnaam;
	}
	public String getTegenrekening() {
		return tegenrekening;
	}
	public void setTegenrekening(String tegenrekening) {
		this.tegenrekening = tegenrekening;
	}
	public String getOmschrijving1() {
		return omschrijving1;
	}
	public void setOmschrijving1(String omschrijving1) {
		this.omschrijving1 = omschrijving1;
	}
	public String getOmschrijving2() {
		return omschrijving2;
	}
	public void setOmschrijving2(String omschrijving2) {
		this.omschrijving2 = omschrijving2;
	}
	public String getOmschrijving3() {
		return omschrijving3;
	}
	public void setOmschrijving3(String omschrijving3) {
		this.omschrijving3 = omschrijving3;
	}
	public String getOmschrijving4() {
		return omschrijving4;
	}
	public void setOmschrijving4(String omschrijving4) {
		this.omschrijving4 = omschrijving4;
	}

    public boolean isMatch(SearchCriteria crit) {
        if (crit.hasText()) {
            StringBuilder builder = new StringBuilder(getTegenrekeningnaam()).append(getOmschrijving1())
                    .append(getOmschrijving2()).append(getOmschrijving3()).append(getOmschrijving4());
            return builder.toString().toUpperCase().contains(crit.getText().toUpperCase().trim());
        }
        return true;
    }
}
