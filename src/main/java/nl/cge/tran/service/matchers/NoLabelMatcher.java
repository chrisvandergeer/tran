package nl.cge.tran.service.matchers;

import nl.cge.tran.domein.Transaktie;

public class NoLabelMatcher implements Matcher {

	private static final String NOLABEL = ":nolabel";

	@Override
	public Matcher create(String search) {
		return new NoLabelMatcher();
	}

	@Override
	public boolean isMatcher(String search) {
		return NOLABEL.equals(search);
	}

	@Override
	public boolean isMatch(Transaktie transaktie) {
		System.out.println(transaktie.getTags().size());
		return transaktie.getTags().size() == 0;
	}

}
