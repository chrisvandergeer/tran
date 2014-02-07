package nl.cge.tran.web.panels;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nl.cge.tran.domein.Money;
import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.domein.helper.TransactieLijst;
import nl.cge.tran.domein.helper.TransaktieComparator;

import org.apache.wicket.model.IModel;
import org.joda.time.LocalDate;

public class MaandTotaalData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Money> negatiefTotaal;
	private Map<String, Money> positiefTotaal;

	private IModel<List<? extends Transaktie>> transakties;
	
	public MaandTotaalData(IModel<List<? extends Transaktie>> transakties) {
		this.transakties = transakties;
		negatiefTotaal = new TreeMap<String, Money>();
		positiefTotaal = new TreeMap<String, Money>();
		calculate();
	}
	
	public void calculate() {
		if (transakties.getObject().size() > 0) {
			TransactieLijst transactieLijst = new TransactieLijst(transakties.getObject());
			LocalDate begindatum = LocalDate.fromDateFields(Collections.min(transakties.getObject(), new TransaktieComparator()).getDatum());
			LocalDate einddatum = LocalDate.fromDateFields(Collections.max(transakties.getObject(), new TransaktieComparator()).getDatum());
			LocalDate datum = new LocalDate(begindatum.getYear(), begindatum.getMonthOfYear(), 1);
			datum = datum.isBefore(einddatum.minusMonths(12)) ? einddatum.minusMonths(11) : datum;
			einddatum = new LocalDate(einddatum.getYear(), einddatum.getMonthOfYear(), 1).plusMonths(1);
			while (datum.isBefore(einddatum)) {
				String key = datum.getYear() + "-" + String.format("%02d", datum.getMonthOfYear());
				TransactieLijst transactiesInMaand = transactieLijst.getTransactiesInMaand(datum);
				negatiefTotaal.put(key, transactiesInMaand.getTotaalNegatief());
				positiefTotaal.put(key, transactiesInMaand.getTotaalPositief());
				datum = datum.plusMonths(1);
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

	public boolean hasData() {
		return transakties.getObject().size() > 0;
	}

}
