package nl.cge.tran.helper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.domein.helper.RaboTransaktieFile;
import nl.cge.tran.domein.helper.RaboTransaktieFileloader;
import nl.cge.tran.persistence.TransaktieDao;

import org.junit.Test;

public class InsertCVSIntoDb {
	
	private static String TRANSACTIONS = "C:\\Users\\NOHi\\ws\\kepler\\tran\\src\\main\\resources\\transactions.txt";

	@Test
	public void testInsert() {
		File f = new File(TRANSACTIONS);
		RaboTransaktieFile file = new RaboTransaktieFileloader().load(f);
		List<Transaktie> transakties = file.parse();
		TransaktieDao dao = TransaktieDao.Instance;
		dao.init();
		dao.save(transakties);
		dao.commit();		
	}
	
	@Test
	public void testReadFromDb() {
		File f = new File(TRANSACTIONS);
		RaboTransaktieFile file = new RaboTransaktieFileloader().load(f);
		List<Transaktie> transaktiesCVS = file.parse();
		TransaktieDao dao = TransaktieDao.Instance;
		dao.init();
		List<Transaktie> transaktiesDB = dao.findAll();
		assertEquals(transaktiesCVS.size(), transaktiesDB.size());
		System.out.println(transaktiesDB.size());
	}

}
