package nl.cge.tran.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.cge.tran.domein.DatumaflopendComparator;
import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.persistence.TransaktieDao;
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

    public List<Transaktie> findAll(SearchCriteria crit) {
    	Collections.sort(cached, datumaflopendComparator);
    	if (!crit.hasText()) {
    		return new ArrayList<Transaktie>(cached);
    	}
        List<Transaktie> result = new ArrayList<Transaktie>();
        for (Transaktie t : cached) {
            if (t.isMatch(crit)) {
                result.add(t);
            }
        }        
        return result;
    }

	public void saveAll(List<Transaktie> transakties, String tag) {
		for (Transaktie transaktie : transakties) {
			transaktie.addTag(tag);
			dao.save(transaktie);
		}
		dao.commit();
	}
}
