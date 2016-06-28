package nl.cge.tran.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.cge.tran.domein.DatumaflopendComparator;
import nl.cge.tran.domein.TaggedQuery;
import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.persistence.EntityDao;
import nl.cge.tran.persistence.TaggedQueryDao;
import nl.cge.tran.persistence.TransaktieDao;
import nl.cge.tran.service.matchers.Matcher;
import nl.cge.tran.service.matchers.MatcherFactory;
import nl.cge.tran.web.ui.homepage.SearchCriteria;

public enum TransaktieService {
	
	Instance;
	
	private EntityDao<Transaktie> transaktieDao = TransaktieDao.instance();
	private EntityDao<TaggedQuery> taggedQueryDao = TaggedQueryDao.instance();	
	
	private List<Transaktie> cached;
	{
		transaktieDao.init();
		taggedQueryDao.init();
		cached = transaktieDao.findAll();		
	}
	
	private List<Transaktie> getCached() {
		if (cached == null) {
			List<Transaktie> findAll = transaktieDao.findAll();
			cached = findAll;
			return findAll;
		}
		return cached;
	}
	
	public List<Transaktie> findAll() {
		return getCached();
	}
	
	public List<Transaktie> findTransakties(SearchCriteria criteria) {
		Collections.sort(cached, new DatumaflopendComparator());
		List<Transaktie> result = new ArrayList<Transaktie>();
		if (!criteria.hasText()) {
			result.addAll(cached);
			return result;
		}
		List<Matcher> matchers = MatcherFactory.create(criteria);
		for (Transaktie transaktie : cached) {
			if (isMatch(matchers, transaktie)) {
				result.add(transaktie);
			}
		}
		return result;
	}

	private boolean isMatch(List<Matcher> matchers, Transaktie transaktie) {
		for (Matcher matcher : matchers) {
			if (!matcher.isMatch(transaktie)) {
				return false;
			}
		}
		return true;
	}
	
	public void addTag(List<? extends Transaktie> transakties, TaggedQuery taggedQuery) {
		String tag = taggedQuery.getTag();
		if (tag == null) return;
		
		if (tag.startsWith("-")) {
			for (Transaktie transaktie : transakties) {
				transaktie.removeTag(tag.replace("-", ""));
				transaktieDao.save(transaktie);
			}
		} else {
			for (Transaktie transaktie : transakties) {
				transaktie.addTag(tag);
				transaktieDao.save(transaktie);
			}
		}
		transaktieDao.commit();
		saveTaggedQuery(taggedQuery);
		
	}
	
	public void saveTaggedQuery(TaggedQuery taggedQuery) {
		TaggedQuery taggedQuery2Persist = taggedQuery;
		TaggedQuery findQuery = findQuery(taggedQuery);
		if (findQuery == null) { 	// nieuw
			taggedQuery2Persist.setCreatiedatum(new Date());
		} else {					// bestaand
			taggedQuery2Persist = findQuery;
			taggedQuery2Persist.setLaatstGebruiktdatum(new Date());
			taggedQuery2Persist.setTag(taggedQuery.getTag());
		}
		taggedQueryDao.save(taggedQuery2Persist);
		taggedQueryDao.commit();
	}
	
	public TaggedQuery findQuery(TaggedQuery taggedQuery) {
		for (TaggedQuery tq : taggedQueryDao.findAll()) {
			if (taggedQuery.getQuery().equals(tq)) {
				return tq;
			}
		}
		return null;
	}

	public Set<Transaktie> createHashTable() {
		return new HashSet<Transaktie>(cached);		
	}

	public void insert(List<Transaktie> transaktiesToInsert) {
		transaktieDao.save(transaktiesToInsert);
		transaktieDao.commit();
		cached = null;
	}
	
	public int deleteAll() {
		transaktieDao.deleteAll();
		cached = findAll();
		return cached.size();
	}

	public void clearCache() {
		cached = findAll();
	}
	
	public List<TaggedQuery> findAllTaggedQueries() {
		return taggedQueryDao.findAll();
	}
}
