package nl.cge.tran.service;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.model.IModel;

import nl.cge.tran.domein.Money;
import nl.cge.tran.domein.Transaktie;

public class TotalCalculator implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double totaal;
	private double totaalPositief;
	private double totaalNegatief;

	private IModel<List<? extends Transaktie>> transakties;

	public TotalCalculator(IModel<List<? extends Transaktie>> transakties) {
		this.transakties = transakties;
		reCalculate();
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
	

	public void reCalculate() {
		Money totaal = Money.create();
		Money totaalPos = Money.create();
		Money totaalNeg = Money.create();
		for (Transaktie t : transakties.getObject()) {
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

//	public static TotalCalculator create(List<Transaktie> transakties) {
//		TotalCalculator instance = new TotalCalculator();
//		instance.reCalculate(transakties);
//		return instance;
//	}

	

}
