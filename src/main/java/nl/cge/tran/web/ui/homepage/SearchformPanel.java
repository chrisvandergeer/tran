package nl.cge.tran.web.ui.homepage;

import java.util.List;

import nl.cge.tran.domein.Transaktie;
import nl.cge.tran.service.TransaktieService;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class SearchformPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private CompoundPropertyModel<SearchCriteria> model;

	private IModel<List<? extends Transaktie>> transakties;
	
	/**
	 * @param id
	 * @param transakties 
	 */
	public SearchformPanel(String id, IModel<List<? extends Transaktie>> transakties) {
		super(id);
		this.transakties = transakties;
		model = new CompoundPropertyModel<SearchCriteria>(new SearchCriteria());
		Form<SearchCriteria> form = newSearchCriteriaForm("searchForm");
		form.add(new TextField<String>("text"));
		form.add(new Button("searchBtn"));
		add(form);
	}
	
	private Form<SearchCriteria> newSearchCriteriaForm(String id) {
		Form<SearchCriteria> form = new Form<SearchCriteria>("searchForm", model) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit() {
				SearchCriteria searchCriteria = getModelObject();
				transakties.setObject(TransaktieService.Instance.findTransakties(searchCriteria));
			}
		};
		return form;
	}
//
//	public IModel<SearchCriteria> getSearchCriteriaModel() {
//		return model;
//	}
}
