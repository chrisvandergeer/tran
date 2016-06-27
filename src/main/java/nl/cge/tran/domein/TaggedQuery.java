package nl.cge.tran.domein;

import java.io.Serializable;

public class TaggedQuery implements Serializable {
	private static final long serialVersionUID = 3060366247491942222L;

	private String query;
	private String tag;
	
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
	

}
