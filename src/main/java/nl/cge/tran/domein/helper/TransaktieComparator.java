package nl.cge.tran.domein.helper;

import java.util.Comparator;

import nl.cge.tran.domein.Transaktie;

public class TransaktieComparator implements Comparator<Transaktie> {

	@Override
	public int compare(Transaktie t1, Transaktie t2) {
		return t1.getDatum().compareTo(t2.getDatum());
	}

}
