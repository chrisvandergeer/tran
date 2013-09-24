package nl.cge.tran.domein;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import nl.cge.tran.web.ui.homepage.SearchCriteria;

public class Transaktie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String rekeningnummer;
	private Date datum;
	private double bedrag;
	private String tegenrekeningnaam;
	private String tegenrekening;
	private String omschrijving1;
	private String omschrijving2;
	private String omschrijving3;
	private String omschrijving4;
	private Set<String> tags;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
    public Set<String> getTags() {
    	if (tags == null) {
    		tags = new HashSet<String>();
    	}
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	public String getTagsStringPresentation() {
		StringBuilder builder = new StringBuilder();
		for (String tag : getTags()) {
			builder.append(tag).append(", ");
		}
		String result = builder.toString();
		return result.endsWith(", ") ? result.substring(0, result.length() - 2) : "";
	}
	
	public boolean isMatch(SearchCriteria crit) {
        if (crit.hasText()) {
            StringBuilder builder = new StringBuilder(getTegenrekeningnaam()).append(getOmschrijving1());
            builder.append(getOmschrijving2()).append(getOmschrijving3()).append(getOmschrijving4());
            String concat = builder.toString().toUpperCase();
            for (String str : crit.getText().split(" ")) {           	
            	if (str.startsWith("l:")) {
            		if (!getTags().contains(str.replace("l:", ""))) {
            			return false;
            		}
            	} else if (str.startsWith(">")) {
            		Integer meerDan = Integer.valueOf(str.replace(">", ""));
            		return bedrag > meerDan;
            	} else if (str.startsWith("<")) {
            		Integer minderDan = Integer.valueOf(str.replace("<", ""));
            		System.out.println(minderDan);
            		return bedrag < minderDan;
            	} else {
            		if (!concat.contains(str.toUpperCase())) {
            			return false;
            		}
            	}
            }
            return true;
        }
        return true;
    }
	
	public void addTag(String tag) {
		getTags().add(tag);
	}
}
