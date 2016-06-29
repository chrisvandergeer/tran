package nl.cge.tran.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import org.apache.log4j.Logger;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import nl.cge.tran.domein.Entity;
import nl.cge.tran.domein.TaggedQuery;

public abstract class EntityDao<E extends Entity>  {
	
	private DB db;
	private ConcurrentNavigableMap<Integer,E> map;
	
	private Logger logger = Logger.getLogger(getClass());

	public void init() {
		String databaseFilename = getDatabaseFilename();
		File dbFile = new File(databaseFilename + ".mapdb");
		logger.info(String.format("dbFile = %s", dbFile.getAbsolutePath()));
		db = DBMaker.newFileDB(dbFile).make();
		map = db.getTreeMap(databaseFilename);
	}
	
	public void close() {
		db.close();		
	}

	public void save(E entity) {
		if (entity.getId() == null) {
			Integer id = map.size() == 0 ? 1 : map.lastKey() + 1;
			entity.setId(id);
		}			
		map.put(entity.getId(), entity);	
	}

	public void commit() {
		db.commit();		
	}

	public E read(Integer id) {
		return map.get(id);
	}

	public List<E> findAll() {
		return new ArrayList<E>(map.values());
	}

	public void save(List<E> entities) {
		for (E t : entities) {
			save(t);
		}		
	}

	public void deleteAll() {
		map.clear();
		commit();
	}
	
	public ConcurrentNavigableMap<Integer, E> getMap() {
		return map;
	}
	
	protected abstract String getDatabaseFilename();


}
