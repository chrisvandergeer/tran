package nl.cge.tran.service.matchers;

import lombok.Data;
import nl.cge.tran.domein.Transaktie;

@Data
public class BedragKleinerDanMatcher implements Matcher {
	
	private static final String PREFIX = "<";
	
	private Integer bedrag;

	@Override
	public boolean isMatcher(String search) {
		try {
			if (search.startsWith(PREFIX)) {
				parseToInt(search.replace(PREFIX, ""));
				return true;
			}
		} catch (NumberFormatException e) {	}
		return false;
	}

	@Override
	public boolean isMatch(Transaktie transaktie) {
		return transaktie.getBedrag() < bedrag;
	}
	
	private int parseToInt(String search) {
		return Integer.parseInt(search);
	}

	@Override
	public Matcher create(String search) {
		BedragKleinerDanMatcher matcher = new BedragKleinerDanMatcher();
		matcher.bedrag = parseToInt(search.replace(PREFIX, ""));
		return matcher;
	}

}
