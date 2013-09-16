package nl.cge.tran.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.domein.helper.RaboTransaktieFile;
import nl.cge.tran.domein.helper.RaboTransaktieFileloader;
import nl.cge.tran.web.ui.oldhomepage.SearchCriteria;

public enum TransaktieService {
	
	Instance;
	
	private List<Transaktie> transakties = new ArrayList<Transaktie>();
	{
		File f = new File("C:\\Users\\NOHi\\IdeaProjects\\Transakties\\src\\main\\resources\\transactions.txt");
		RaboTransaktieFile file = new RaboTransaktieFileloader().load(f);
		transakties = file.parse();
	}
	
	public List<Transaktie> findAll() {
		return transakties;
	}

    public List<Transaktie> findAll(SearchCriteria crit) {
        List<Transaktie> result = new ArrayList<Transaktie>();
        for (Transaktie t : transakties) {
            if (t.isMatch(crit)) {
                result.add(t);
            }
        }
        return result;
    }
}
