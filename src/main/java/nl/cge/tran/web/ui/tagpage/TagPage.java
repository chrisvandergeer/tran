package nl.cge.tran.web.ui.tagpage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import nl.cge.tran.domein.TaggedQuery;
import nl.cge.tran.service.TransaktieService;
import nl.cge.tran.web.wicket.ui.BootstrapPage;

public class TagPage extends BootstrapPage {
	private static final long serialVersionUID = 1L;
	
	private TransaktieService transaktieService = TransaktieService.Instance;

	public TagPage(PageParameters parameters) {
		super(parameters);
		add(new ListView<TaggedQuery>("taggedQueries", transaktieService.findAllTaggedQueries()) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<TaggedQuery> item) {
				item.setModel(new CompoundPropertyModel<TaggedQuery>(item.getModel()));
				item.add(new Label("query"));
				item.add(new Label("tag"));
				item.add(new Label("creatiedatum"));
				item.add(new Label("laatstGebruiktdatum"));
			}
		});
	}

}
