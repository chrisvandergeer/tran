package nl.cge.tran.web.ui.homepage;

import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Transaktiepage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	private TransaktielijstPanel transaktielijstPanel;
	private SearchformPanel searchformPanel;
	private TotalenPanel totalenPanel;
	
	public Transaktiepage(PageParameters parameters) {
		super(parameters);
		add(searchformPanel("search"));
		add(transaktielijstPanel("transactiepanel"));
		add(new TagformPanel("tagformpanel", transaktielijstPanel));
		add(totalenPanel("totalen"));
	}

	private TotalenPanel totalenPanel(String id) {
		totalenPanel = new TotalenPanel(id);
		return totalenPanel;
	}

	private TransaktielijstPanel transaktielijstPanel(String id) {
		transaktielijstPanel = new TransaktielijstPanel(id);
		return transaktielijstPanel;
	}

	private SearchformPanel searchformPanel(String id) {
		searchformPanel = new SearchformPanel(id);
		return searchformPanel;
	}

	@Override
    protected void onBeforeRender() {
		SearchCriteria crit = searchformPanel.getSearchCriteriaModel().getObject();
        List<Transaktie> transakties = TransaktieService.Instance.findTransakties(crit);
        transaktielijstPanel.getTransaktielijst().setDefaultModelObject(transakties);
        totalenPanel.reCalculate(transakties);
        super.onBeforeRender();
    }
	
}
