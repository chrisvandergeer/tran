package nl.cge.tran.web.ui.homepage;

import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TotalCalculator;
import nl.cge.tran.web.wicket.labels.Currencylabel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class TotalenPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private TotalCalculator totalen;

	public TotalenPanel(String id, IModel<List<? extends Transaktie>> transakties) {
		super(id);
		totalen = new TotalCalculator(transakties);
//		add(new MaandgrafiekPanel("maandgrafiek", transakties));
		add(new Currencylabel("totaal", new PropertyModel<TotalCalculator>(totalen, "totaal")));
		add(new Currencylabel("totaalPositief", new PropertyModel<TotalCalculator>(totalen, "totaalPositief")));
		add(new Currencylabel("totaalNegatief", new PropertyModel<TotalCalculator>(totalen, "totaalNegatief")));
	}
	
	@Override
	protected void onBeforeRender() {
		totalen.reCalculate();
		super.onBeforeRender();
	}

}
