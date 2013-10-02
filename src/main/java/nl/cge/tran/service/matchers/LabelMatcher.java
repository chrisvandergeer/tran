package nl.cge.tran.service.matchers;

import lombok.Data;
import nl.cge.tran.domein.Transaktie;

@Data
public class LabelMatcher implements Matcher {
	
	private static final String PREFIX = "l:";
	
	private String label;
	
	@Override
	public boolean isMatch(Transaktie transaktie) {
		return transaktie.getTags().contains(label);
	}

	@Override
	public boolean isMatcher(String search) {
		return search.startsWith(PREFIX);
	}

	@Override
	public Matcher create(String search) {
		LabelMatcher matcher = new LabelMatcher();
		matcher.label = search.replace(PREFIX, "");		
		return matcher;
	}

}
