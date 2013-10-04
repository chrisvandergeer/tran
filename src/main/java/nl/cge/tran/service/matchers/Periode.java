package nl.cge.tran.service.matchers;

public class Periode {
	
	private Integer van;
	private Integer tot;
	
	public Integer getVan() {
		return van;
	}
	public void setVan(Integer van) {
		this.van = van;
	}
	public Integer getTot() {
		return tot;
	}
	public void setTot(Integer tot) {
		this.tot = tot;
	}
	public boolean isValid() {
		return van != null && tot != null && van <= tot;
	}
	public boolean valtBinnen(int moment) {
		return moment >= van && moment <= tot;
	}
	

}
