package nl.cge.tran.service;

import org.junit.Test;

public class TransaktieServiceTest {

	@Test
	public void testDeleteAll() {
		int aantal = TransaktieService.Instance.deleteAll();
		System.out.println("deleted: " + aantal);
	}

}
