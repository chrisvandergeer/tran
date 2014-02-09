package nl.cge.tran.domein.helper;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

import nl.cge.tran.domein.Money;
import nl.cge.tran.domein.Transaktie;

public class TransactieLijst extends AbstractList<Transaktie> {
	
	List<? extends Transaktie> list;
	
	public TransactieLijst(List<? extends Transaktie> list) {
		this.list = list;
	}

	@Override
	public Transaktie get(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}
	
	public Transaktie getOudsteTransactie() {
		return Collections.min(this, new TransaktieComparator());
	}
	
	public Transaktie getMeestrecenteTransactie() {
		return Collections.max(this, new TransaktieComparator());
	}
	
	public Money getTotaal() {
		Money totaal = Money.create();
		for (Transaktie t : this) {
			totaal.addAmount(t.getBedrag());
		}
		return totaal;
	}
	
	public Money getTotaalPositief() {
		Money totaal = Money.create();
		for (Transaktie t : this) {
			if (t.getBedrag() > 0) {
				totaal.addAmount(t.getBedrag());
			}
		}
		return totaal;
	}
	
	public Money getTotaalNegatief() {
		Money totaal = Money.create();
		for (Transaktie t : this) {
			if (t.getBedrag() < 0) {
				totaal.addAmount(t.getBedrag() * -1);
			}
		}
		return totaal;
	}
	
	public TransactieLijst getTransactiesInMaand(LocalDate date) {
		List<Transaktie> list = new ArrayList<Transaktie>();
		for (Transaktie t : this) {
			LocalDate transactieDatum = LocalDate.fromDateFields(t.getDatum());
			if (date.getYear() == transactieDatum.getYear() && date.getMonthOfYear() == transactieDatum.getMonthOfYear()) {
				list.add(t);
			}
		}
		return new TransactieLijst(list);
	}

}
