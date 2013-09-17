package nl.cge.tran.service;

import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.persistence.TransaktieDao;
import nl.cge.tran.web.ui.homepage.SearchCriteria;

public enum TransaktieService {
	
	Instance;
	
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
}
