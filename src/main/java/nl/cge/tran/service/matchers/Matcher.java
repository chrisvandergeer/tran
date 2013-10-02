package nl.cge.tran.service.matchers;

import nl.cge.tran.domein.Transaktie;

public interface Matcher {
	
	Matcher create(String search);

	boolean isMatcher(String search);
	
	boolean isMatch(Transaktie transaktie);

}