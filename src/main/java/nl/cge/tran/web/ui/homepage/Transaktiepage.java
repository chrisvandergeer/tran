package nl.cge.tran.web.ui.homepage;

import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Transaktiepage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	private TransaktielijstPanel transaktielijstPanel;
	private SearchformPanel searchformPanel;
	private TotalenPanel totalenPanel;
	
	public Transaktiepage(PageParameters parameters) {
		super(parameters);
		searchformPanel = new SearchformPanel("search");
		add(searchformPanel);
//		add(tagForm("tagForm"));
		transaktielijstPanel = new TransaktielijstPanel("transactiepanel");
		add(transaktielijstPanel);		
		add(new TagformPanel("tagformpanel", transaktielijstPanel));
		totalenPanel = new TotalenPanel("totalen");
		add(totalenPanel);
	}

//	private Form<Void> tagForm(String id) {
//		Form<Void> form = new Form<Void>(id);
//		final TextField<String> tag = new TextField<String>("tag", Model.of(""));
//		form.add(tag);
//		form.add(new Button("opslaanBtn") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onSubmit() {
//				TransaktieService.Instance.saveAll(transaktielijstPanel.getTransaktielijst().getModelObject(), tag.getModelObject());
//			}
//		});
//		return form;
//	}
//	
	@Override
    protected void onBeforeRender() {
		SearchCriteria crit = searchformPanel.getSearchCriteriaModel().getObject();
        List<Transaktie> transakties = TransaktieService.Instance.findTransakties(crit);
        transaktielijstPanel.getTransaktielijst().setDefaultModelObject(transakties);
        totalenPanel.reCalculate(transakties);
        super.onBeforeRender();
    }
	
}
