package nl.cge.tran.service.matchers;

import lombok.Data;

import org.joda.time.LocalDate;

import nl.cge.tran.domein.Transaktie;

@Data
public class JaarMatcher implements Matcher {
	
	private Periode periode;

	@Override
	public Matcher create(String search) {
		JaarMatcher matcher = new JaarMatcher();
		matcher.periode = createPeriode(search);
		return matcher;
	}

	@Override
	public boolean isMatcher(String search) {
		try {
			Periode periode = createPeriode(search);
			return periode.isValid();
		} catch (NumberFormatException e) { 
			return false;
		}
	}

	private Periode createPeriode(String search) {
		Periode periode = new Periode();
		if (search.startsWith("j:")) {
			String str = search.replace("j:", "");
			if (!str.contains("-")) {
				periode.setVan(Integer.parseInt(str));
				periode.setTot(periode.getVan());
			} else {
				String[] split = str.split("-");
				periode.setVan(Integer.parseInt(split[0]));
				periode.setTot(Integer.parseInt(split[1]));
			}
		}
		return periode;
	}
	
	@Override
	public boolean isMatch(Transaktie transaktie) {
		int jaar = new LocalDate(transaktie.getDatum()).getYear();
		return periode.valtBinnen(jaar);
	}

}
