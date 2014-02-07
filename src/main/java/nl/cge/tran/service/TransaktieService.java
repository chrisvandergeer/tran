package nl.cge.tran.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.cge.tran.domein.DatumaflopendComparator;
import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.persistence.TransaktieDao;
import nl.cge.tran.persistence.TransaktieDaoMapdbImpl;
import nl.cge.tran.service.matchers.Matcher;
import nl.cge.tran.service.matchers.MatcherFactory;
import nl.cge.tran.web.ui.homepage.SearchCriteria;

public enum TransaktieService {
	
	Instance;
	
	private DatumaflopendComparator datumaflopendComparator = new DatumaflopendComparator();
	
	private TransaktieDao dao = TransaktieDaoMapdbImpl.instance();
	
	private List<Transaktie> cached;
	{
		dao.init();
		cached = dao.findAll();		
	}
	
	private List<Transaktie> getCached() {
		if (cached == null) {
			List<Transaktie> findAll = dao.findAll();
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

	public void saveAll(List<? extends Transaktie> list, String tag) {
		if (tag == null) return;
		
		if (tag.startsWith("-")) {
			for (Transaktie transaktie : list) {
				transaktie.removeTag(tag.replace("-", ""));
				dao.save(transaktie);
			}
		} else {
			for (Transaktie transaktie : list) {
				transaktie.addTag(tag);
				dao.save(transaktie);
			}
		}
		dao.commit();
	}

	public Set<Transaktie> createHashTable() {
		return new HashSet<Transaktie>(cached);		
	}

	public void insert(List<Transaktie> transaktiesToInsert) {
		dao.save(transaktiesToInsert);
		dao.commit();
		cached = null;
	}
	
	public int deleteAll() {
		dao.deleteAll();
		cached = findAll();
		return cached.size();
	}

	public void clearCache() {
		cached = findAll();
	}
}
