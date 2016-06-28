package nl.cge.tran.persistence;

import nl.cge.tran.domein.Transaktie;

public class TransaktieDao extends EntityDao<Transaktie> {

	public static EntityDao<Transaktie> instance() {
		return new TransaktieDao();
	}
	
	@Override
	protected String getDatabaseFilename() {
		return "transacties";
	}
	
	

}
