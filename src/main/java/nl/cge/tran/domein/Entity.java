package nl.cge.tran.domein;

import java.io.Serializable;

public class Entity implements Serializable {
	private static final long serialVersionUID = 2359145215134231088L;

	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

}
