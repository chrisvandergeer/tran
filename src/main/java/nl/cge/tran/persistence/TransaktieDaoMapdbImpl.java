package nl.cge.tran.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import nl.cge.tran.domein.Transaktie;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class TransaktieDaoMapdbImpl implements TransaktieDao {
	
	private static final String TRANSACTIES_MAP = "transacties";
	private DB db;
	private ConcurrentNavigableMap<Integer,Transaktie> map;

	public static TransaktieDao instance() {
		return new TransaktieDaoMapdbImpl();
	}

	@Override
	public void init() {
		db = DBMaker.newFileDB(new File("transakties.mapdb")).make();
		map = db.getTreeMap(TRANSACTIES_MAP);
	}

	@Override
	public void close() {
		db.close();

	}

	@Override
	public void save(Transaktie transaktie) {
		if (transaktie.getId() == null) {
			Integer id = map.size() == 0 ? 1 : map.lastKey() + 1;
			transaktie.setId(id);
		}			
		map.put(transaktie.getId(), transaktie);	

	}

	@Override
	public void commit() {		
		db.commit();
	}

	@Override
	public Transaktie read(Integer id) {
		return map.get(id);
	}

	@Override
	public List<Transaktie> findAll() {
		return new ArrayList<Transaktie>(map.values());
	}

	@Override
	public void save(List<Transaktie> transakties) {
		for (Transaktie t : transakties) {
			save(t);
		}
	}

	@Override
	public void deleteAll() {
		map.clear();
		commit();
	}

}
