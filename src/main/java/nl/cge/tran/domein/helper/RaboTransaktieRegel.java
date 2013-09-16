package nl.cge.tran.domein.helper;


import java.util.Calendar;
import java.util.Date;

public class RaboTransaktieRegel {
	
	private String[] raboregel;
	
	public static RaboTransaktieRegel newInstance() {
		return new RaboTransaktieRegel();
	}
	
	public void setRegel(String regel) {
		raboregel = regel.split(",");
	}
	
	public String getRekeningnummer() {
		return stripQuotes(raboregel[0]);
	}

	private String stripQuotes(String waarde) {
		return waarde.replaceAll("\"", "").trim();
	}

	public Date getDatum() {
		String str = stripQuotes(raboregel[2]);
		int jaar = Integer.parseInt(str.substring(0, 4));
		int maand = Integer.parseInt(str.substring(4, 6));
		int dag = Integer.parseInt(str.substring(6, 8));	
		Calendar cal = Calendar.getInstance();
		cal.set(jaar, maand-1, dag);
		return cal.getTime();
	}

	public Double getBedrag() {
		String bedragString = stripQuotes(raboregel[4]);
		Double bedrag = Double.parseDouble(bedragString);
		if ("\"D\"".equals(raboregel[3])) {
			bedrag *= -1;
		}
		return bedrag;
	}

	public String getTegenrekeningnaam() {
		return stripQuotes(raboregel[6]);
	}

	public String getTegenrekening() {
		return stripQuotes(raboregel[5]);
	}

	public String getOmschrijving1() {
		String omschrijving = raboregel[10];
		return stripQuotes(omschrijving);
	}
	
	public String getOmschrijving2() {
		String omschrijving = raboregel[11];
		return stripQuotes(omschrijving);
	}
	
	public String getOmschrijving3() {
		String omschrijving = raboregel[12];
		return stripQuotes(omschrijving);
	}
	
	public String getOmschrijving4() {
		String omschrijving = raboregel[13];
		return stripQuotes(omschrijving);
	}
	
	

}
