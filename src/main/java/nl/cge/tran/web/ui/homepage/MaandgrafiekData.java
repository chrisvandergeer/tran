package nl.cge.tran.web.ui.homepage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nl.cge.tran.domein.Money;
import nl.cge.tran.domein.Transaktie;

import org.apache.wicket.model.IModel;
import org.joda.time.LocalDate;

public class MaandgrafiekData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Money> negatiefTotaal;
	private Map<String, Money> positiefTotaal;

	private IModel<List<? extends Transaktie>> transakties;
	
	public MaandgrafiekData(IModel<List<? extends Transaktie>> transakties) {
		this.transakties = transakties;
		calculateChartdata();
	}

	public void calculateChartdata() {
		negatiefTotaal = new TreeMap<String, Money>();
		positiefTotaal = new TreeMap<String, Money>();
		for (Transaktie t : transakties.getObject()) {
			LocalDate date = new LocalDate(t.getDatum());
			String key = date.getYear() + "-" + date.getMonthOfYear();
			if (!negatiefTotaal.containsKey(key)) {
				negatiefTotaal.put(key, Money.create());
				positiefTotaal.put(key, Money.create());
			}
			if (t.getBedrag() > 0) {
				positiefTotaal.get(key).addAmount(t.getBedrag());
			} else {
				negatiefTotaal.get(key).addAmount(t.getBedrag() * -1);
			}
		}
		if (positiefTotaal.size() > 12) {
			int aantalTeVerwijderen = positiefTotaal.size() - 12;
			String[] keys = positiefTotaal.keySet().toArray(new String[0]);
			for (int i = 0; i < aantalTeVerwijderen; i++) {
				positiefTotaal.remove(keys[i]);
				negatiefTotaal.remove(keys[i]);
			}
		}
	}

	public Map<String, Money> getNegatiefTotaal() {
		return negatiefTotaal;
	}
	
	public Map<String, Money> getPositiefTotaal() {
		return positiefTotaal;
	}

	public boolean hasValuesPositief() {
		int totaal = 0;
		for (Money bedrag : positiefTotaal.values()) {
			totaal += bedrag.integerValue();
		}
		return totaal > 0;
	}

	public boolean hasValuesNegatief() {
		int totaal = 0;
		for (Money bedrag : negatiefTotaal.values()) {
			totaal += bedrag.integerValue();
		}
		return totaal > 0;
	}

}
