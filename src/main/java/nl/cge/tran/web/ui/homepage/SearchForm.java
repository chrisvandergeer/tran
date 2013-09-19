package nl.cge.tran.web.ui.homepage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Created with IntelliJ IDEA.
 * User: NOHi
 * Date: 4-6-13
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public class SearchForm extends org.apache.wicket.markup.html.form.Form<SearchCriteria> {
	private static final long serialVersionUID = 1L;

	public SearchForm(String id) {
        super(id, new CompoundPropertyModel<SearchCriteria>(new SearchCriteria()));
        add(new TextField<String>("text"));
        add(new Button("searchBtn"));
    }
}
