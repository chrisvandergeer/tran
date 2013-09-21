package nl.cge.tran.domein;

import java.util.Comparator;

public class DatumaflopendComparator implements Comparator<Transaktie> {

	@Override
	public int compare(Transaktie t1, Transaktie t2) {
		return t2.getDatum().compareTo(t1.getDatum());
	}

}
