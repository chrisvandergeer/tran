package nl.cge.tran.service.matchers;

import lombok.Data;
import nl.cge.tran.domein.Transaktie;

@Data
public class FieldMatcher implements Matcher {
	
	private String search;

	@Override
	public boolean isMatcher(String search) {
		return true;
	}

	@Override
	public boolean isMatch(Transaktie transaktie) {
        return transaktie.getTextfields().toUpperCase().contains(search.toUpperCase());
	}

	@Override
	public Matcher create(String search) {
		FieldMatcher matcher = new FieldMatcher();
		matcher.search = search;
		return matcher;
	}

}
