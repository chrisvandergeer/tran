package nl.cge.tran.service.matchers;

import lombok.Data;
import nl.cge.tran.domein.Transaktie;

@Data
public class BedragGroterDanMatcher implements Matcher {
	
	private static final String PREFIX = ">";
	
	private Integer bedrag;

	public Matcher create(String search) {
		BedragGroterDanMatcher matcher = new BedragGroterDanMatcher();
		matcher.bedrag = parseToInt(search.replace(PREFIX, ""));
		return matcher;
	}

	@Override
	public boolean isMatch(Transaktie transaktie) {
		return transaktie.getBedrag() > bedrag;
	}

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
	
	private int parseToInt(String search) {
		return Integer.parseInt(search);
	}

}
