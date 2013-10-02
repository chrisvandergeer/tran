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
		StringBuilder builder = new StringBuilder(transaktie.getTegenrekeningnaam())
			.append(transaktie.getOmschrijving1()).append(transaktie.getOmschrijving2())
			.append(transaktie.getOmschrijving3()).append(transaktie.getOmschrijving4());
        String concat = builder.toString().toUpperCase();
        return concat.contains(search.toUpperCase());
	}

	@Override
	public Matcher create(String search) {
		FieldMatcher matcher = new FieldMatcher();
		matcher.search = search;
		return matcher;
	}

}
