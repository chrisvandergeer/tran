package nl.cge.tran.service.matchers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import nl.cge.tran.web.ui.homepage.SearchCriteria;

public class MatcherFactory {
	
	private static Logger LOGGER = Logger.getLogger(MatcherFactory.class);
	
	public static Matcher[] MATCHERS = new Matcher[] {
		new LabelMatcher(), new BedragGroterDanMatcher(), new BedragKleinerDanMatcher(), 
		new MaandMatcher(),	new JaarMatcher(), new FieldMatcher() 
	};
	
	public static List<Matcher> create(SearchCriteria criteria) {
		List<Matcher> matcherlist = new ArrayList<Matcher>();
		for (String search : criteria.getText().split(" ")) {
			for (Matcher matcher : MATCHERS) {
				if (matcher.isMatcher(search)) {
					matcherlist.add(matcher.create(search));
					break;
				}
			}
		}
		LOGGER.info(matcherlist);
		return matcherlist;
	}
}
