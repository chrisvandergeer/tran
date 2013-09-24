package nl.cge.tran.service;

import java.io.Serializable;
import java.util.List;

import nl.cge.tran.domein.Money;
import nl.cge.tran.domein.Transaktie;

public class TotalCalculator implements Serializable {
	
	private double totaal;
	private double totaalPositief;
	private double totaalNegatief;

	public TotalCalculator() {
		super();
	}
	
	public double getTotaal() {
		return totaal;
	}

	public double getTotaalPositief() {
		return totaalPositief;
	}

	public double getTotaalNegatief() {
		return totaalNegatief;
	}
	

	public void reCalculate(List<Transaktie> transakties) {
		Money totaal = Money.create();
		Money totaalPos = Money.create();
		Money totaalNeg = Money.create();
		for (Transaktie t : transakties) {
			double bedrag = t.getBedrag();
			totaal.addAmount(bedrag);
			if (bedrag > 0) {
				totaalPos.addAmount(bedrag);
			} else {
				totaalNeg.addAmount(bedrag);
			}
		}
		this.totaal = totaal.doubleValue();
		this.totaalPositief = totaalPos.doubleValue();
		this.totaalNegatief = totaalNeg.doubleValue();		
	}

	public static TotalCalculator create(List<Transaktie> transakties) {
		TotalCalculator instance = new TotalCalculator();
		instance.reCalculate(transakties);
		return instance;
	}

	

}
