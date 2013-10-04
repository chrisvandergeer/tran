package nl.cge.tran.service.matchers;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import nl.cge.tran.domein.Transaktie;

public class MaandMatcher implements Matcher {
	
	private final static Map<String, Integer> MAANDEN = new HashMap<String, Integer>();
	static {
		MAANDEN.put("jan", 1); MAANDEN.put("feb", 2); MAANDEN.put("mrt", 3);
		MAANDEN.put("apr", 4); MAANDEN.put("mei", 5); MAANDEN.put("jun", 6);
		MAANDEN.put("jul", 7); MAANDEN.put("aug", 8); MAANDEN.put("sep", 9);
		MAANDEN.put("okt", 10); MAANDEN.put("nov", 11); MAANDEN.put("dec", 12);
	}

	private Periode periode;	

	@Override
	public Matcher create(String search) {
		MaandMatcher matcher = new MaandMatcher();
		matcher.periode = createPeriode(search);
		return matcher;
	}

	@Override
	public boolean isMatcher(String search) {
		return createPeriode(search).isValid();
	}
	
	@Override
	public boolean isMatch(Transaktie transaktie) {
		LocalDate datum = new LocalDate(transaktie.getDatum());
		int maand = datum.getMonthOfYear();
		return periode.valtBinnen(maand);
	}

	private Periode createPeriode(String search) {
		Periode periode = new Periode();
		if (search.startsWith("m:")) {			
			String str = search.replace("m:", "");
			if (!str.contains("-")) {
				periode.setVan(MAANDEN.get(str.trim()));
				periode.setTot(periode.getVan());
			} else {
				String[] split = str.split("-");
				periode.setVan(MAANDEN.get(split[0]));
				periode.setTot(MAANDEN.get(split[1]));
			}
		}
		return periode;
	}

}
