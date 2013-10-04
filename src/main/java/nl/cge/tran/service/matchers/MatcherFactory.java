package nl.cge.tran.service.matchers;

import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.web.ui.homepage.SearchCriteria;

public class MatcherFactory {
	
	public static Matcher[] MATCHERS = new Matcher[] {
		new LabelMatcher(), new BedragGroterDanMatcher(), new BedragKleinerDanMatcher(), 
		new MaandMatcher(),	new FieldMatcher() 
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
		System.out.println(matcherlist);
		return matcherlist;
	}
}
