package nl.cge.tran.service.matchers;

import static org.junit.Assert.*;

import javax.validation.constraints.AssertFalse;

import nl.cge.tran.domein.Transaktie;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class MaandMatcherTest {
	
	private MaandMatcher matcher;
	
	@Before
	public void before() {
		matcher = new MaandMatcher();
	}

	@Test
	public void testMe() {
		assertTrue(matcher.isMatcher("m:aug"));
		assertTrue(matcher.isMatcher("m:aug-dec"));
		assertFalse(matcher.isMatcher("m:qwe"));
		assertFalse(matcher.isMatcher("m:aug,dec"));
	}
	
	@Test
	public void testIsMatch() {
		Transaktie transaktie = new Transaktie();
		transaktie.setDatum(new LocalDate(2013, 5, 2).toDate());
		assertTrue(new MaandMatcher().create("m:mei").isMatch(transaktie));
		assertTrue(new MaandMatcher().create("m:apr-jun").isMatch(transaktie));
		assertTrue(new MaandMatcher().create("m:mei-jun").isMatch(transaktie));
		assertTrue(new MaandMatcher().create("m:mrt-mei").isMatch(transaktie));
		assertFalse(new MaandMatcher().create("m:jun").isMatch(transaktie));
		assertFalse(new MaandMatcher().create("m:jun-dec").isMatch(transaktie));
	}

}
