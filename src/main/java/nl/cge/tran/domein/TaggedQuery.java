package nl.cge.tran.domein;

import java.util.Date;

public class TaggedQuery extends Entity {
	private static final long serialVersionUID = -6413807231771389920L;

	private String query;
	private String tag;
	private Date creatiedatum;
	private Date laatstGebruiktdatum;
	
	public static TaggedQuery create(String query, String tag) {
		TaggedQuery taggedQuery = new TaggedQuery();
		taggedQuery.setQuery(query);
		taggedQuery.setTag(tag);
		return taggedQuery;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getCreatiedatum() {
		return creatiedatum;
	}

	public void setCreatiedatum(Date creatiedatum) {
		this.creatiedatum = creatiedatum;
		this.setLaatstGebruiktdatum(creatiedatum);
	}

	public Date getLaatstGebruiktdatum() {
		return laatstGebruiktdatum;
	}

	public void setLaatstGebruiktdatum(Date laatstGebruiktdatum) {
		this.laatstGebruiktdatum = laatstGebruiktdatum;
	}


	
}
