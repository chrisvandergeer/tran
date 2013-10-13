package nl.cge.tran.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jdbm.PrimaryTreeMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import nl.cge.tran.domein.ApplicationException;
import nl.cge.tran.domein.Transaktie;

public enum TransaktieDao {
	
	Instance;
	
	private PrimaryTreeMap<Integer, Transaktie> treeMap;
	private RecordManager recordManager;
	
	public void init() {
		try {
			recordManager = RecordManagerFactory.createRecordManager("transakties.dbm");
			treeMap = recordManager.treeMap("transaktie"); 
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
	}
	
	public void close() {
		try {
			recordManager.close();
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
	}
	
	public void save(Transaktie transaktie) {
			if (transaktie.getId() == null) {
				Integer id = treeMap.size() == 0 ? 1 : treeMap.lastKey() + 1;
				transaktie.setId(id);
			}			
			treeMap.put(transaktie.getId(), transaktie);			
	}
	
	public void commit() {
		try {			
			recordManager.commit();
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
	}

	public Transaktie read(Integer id) {
		try {
			return treeMap.find(id);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}		
	}
	
	public List<Transaktie> findAll() {
		return new ArrayList<Transaktie>(treeMap.values());
	}
	
	public void save(List<Transaktie> transakties) {
		for (Transaktie t : transakties) {
			save(t);
		}
	}
	
	public void deleteAll() {
		treeMap.clear();
		commit();
	}
	

}
