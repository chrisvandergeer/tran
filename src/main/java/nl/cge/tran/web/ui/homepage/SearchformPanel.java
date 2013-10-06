package nl.cge.tran.web.ui.homepage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class SearchformPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private CompoundPropertyModel<SearchCriteria> model;
	
	/**
	 * @param id
	 */
	public SearchformPanel(String id) {
		super(id);
		model = new CompoundPropertyModel<SearchCriteria>(new SearchCriteria());
		Form<SearchCriteria> form = new Form<SearchCriteria>("searchForm", model);
		form.add(new TextField<String>("text"));
		form.add(new Button("searchBtn"));
		add(form);
	}

	public IModel<SearchCriteria> getSearchCriteriaModel() {
		return model;
	}
}
