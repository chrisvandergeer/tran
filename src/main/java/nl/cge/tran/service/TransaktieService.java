package nl.cge.tran.service;

import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.domein.DatumaflopendComparator;
import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.persistence.TransaktieDao;
import nl.cge.tran.service.matchers.Matcher;
import nl.cge.tran.service.matchers.MatcherFactory;
import nl.cge.tran.web.ui.homepage.SearchCriteria;

public enum TransaktieService {
	
	Instance;
	
	private DatumaflopendComparator datumaflopendComparator = new DatumaflopendComparator();
	
	private TransaktieDao dao = TransaktieDao.Instance;
	
	private List<Transaktie> cached;
	{
		dao.init();
		cached = dao.findAll();		
	}
	
	public List<Transaktie> findAll() {
		return cached;
	}
	
	public List<Transaktie> findTransakties(SearchCriteria criteria) {
		List<Transaktie> result = new ArrayList<Transaktie>();
		if (!criteria.hasText()) {
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

	public void saveAll(List<Transaktie> transakties, String tag) {
		for (Transaktie transaktie : transakties) {
			transaktie.addTag(tag);
			dao.save(transaktie);
		}
		dao.commit();
	}
}
