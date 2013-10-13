package nl.cge.tran.web.ui.homepage;

import java.util.ArrayList;
import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Transaktiepage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Panels.
	 */
	private TransaktielijstPanel transaktielijstPanel;
	private SearchformPanel searchformPanel;
	private TagformPanel tagformPanel;
	private TotalenPanel totalenPanel;
	
	/**
	 * Model of List<Transaktie>.
	 */
	private IModel<List<? extends Transaktie>> transakties;

	
	/**
	 * Constructor.
	 * 
	 * @param parameters
	 */
	public Transaktiepage(PageParameters parameters) {
		super(parameters);
		transakties = Model.ofList(new ArrayList<Transaktie>());
		add(newSearchformPanel("search"));
		add(newTransaktielijstPanel("transactiepanel"));
		add(newTagformPanel("tagformpanel"));
//		add(totalenPanel("totalen"));
		add(new MaandgrafiekPanel("maandChart", transakties));
	}

	private TagformPanel newTagformPanel(String id) {
		tagformPanel = new TagformPanel(id, transakties);
		return tagformPanel;
	}

//	private TotalenPanel totalenPanel(String id) {
//		totalenPanel = new TotalenPanel(id, transakties);
//		return totalenPanel;
//	}

	private TransaktielijstPanel newTransaktielijstPanel(String id) {
		transaktielijstPanel = new TransaktielijstPanel(id, transakties);
		return transaktielijstPanel;
	}

	private SearchformPanel newSearchformPanel(String id) {
		searchformPanel = new SearchformPanel(id, transakties);
		return searchformPanel;
	}
	
}
